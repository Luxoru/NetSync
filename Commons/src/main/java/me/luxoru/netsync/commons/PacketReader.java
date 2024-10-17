package me.luxoru.netsync.commons;

import me.luxoru.netsync.commons.handler.PacketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class PacketReader extends Thread{

    private final DatagramChannel channel;
    private final ByteBuffer buffer;
    private final long delay;
    private final PacketHandler handler;

    public PacketReader(int port, long sleepDelay, PacketHandler handler){
        this.handler = handler;
        this.delay = sleepDelay;
        try{
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(port));

            buffer = ByteBuffer.allocate(1024);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {

        while (!isInterrupted()){
            buffer.clear();
            try {
                InetSocketAddress socketAddress = (InetSocketAddress) channel.receive(buffer);

                if(socketAddress == null)continue;

                buffer.flip();

                byte[] bytes = new byte[buffer.remaining()];
;
                buffer.get(bytes);

                Packet packet = PacketBuilder.readBytes(bytes);
                handler.handle(packet);

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
}
