package me.luxoru.netsync.commons;

import me.luxoru.netsync.commons.handler.PacketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * Represents a base level packet handler
 * <p>A client and server handles packets in different ways.</p>
 * <p>Clients will always just be listening and responding to the server whereas a server needs to listen to every request
 * and respond to these requests</p>
 */

public abstract class PacketManager extends Thread{

    protected final String host;
    protected final DatagramChannel channel;
    private final ByteBuffer buffer;
    private final long delay;
    protected final PacketHandler handler;
    public final Queue<PacketSendable> packetsToSend;
    protected final int port;

    public PacketManager(int port, long sleepDelay, PacketHandler handler){
        this(null, port, sleepDelay, handler);
    }

    public PacketManager(String host, int port, long sleepDelay, PacketHandler handler){
        this.handler = handler;
        this.delay = sleepDelay;
        this.port = port;
        this.host = host;
        this.packetsToSend = new ArrayDeque<>();

        try{
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            connect();

            buffer = ByteBuffer.allocate(1024);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    protected abstract void connect() throws IOException;

    public void sendPacket(PacketSendable packetSendable){
        packetsToSend.offer(packetSendable);
    }

    @Override
    public void run() {

        while (!isInterrupted()){
            buffer.clear();
            try {

                InetSocketAddress remoteAddress = (InetSocketAddress) channel.receive(buffer);

                if(remoteAddress == null){
                    attemptToWritePackets();
                    continue;
                }
                boolean shouldRead = shouldRead(remoteAddress);//A client will need to detect whether this remote address is coming from the server.

                if(!shouldRead){
                    attemptToWritePackets();
                    continue;
                }

                buffer.flip();

                byte[] bytes = new byte[buffer.remaining()];
;
                buffer.get(bytes);
                Packet packet = PacketBuilder.readBytes(bytes);
                handler.handle(packet, remoteAddress);

                attemptToWritePackets();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    protected void attemptToWritePackets(){

        PacketSendable packetSendable = packetsToSend.poll();

        if(packetSendable == null)return;

        Packet packet = packetSendable.getPacket();

        try {
            channel.send(ByteBuffer.wrap(packet.toBytes()), packetSendable.getSocketAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected abstract boolean shouldRead(InetSocketAddress remoteAddress);
}
