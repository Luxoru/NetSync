package me.luxoru.netsync.commons;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayDeque;
import java.util.Queue;

public class PacketWriter extends Thread{

    private final long delay;
    private final InetSocketAddress socketAddress;
    private DatagramChannel channel;

    public Queue<Packet> packets;

    public PacketWriter(String host, int port, long delay){
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            packets = new ArrayDeque<>();
            this.delay = delay;
            this.socketAddress = new InetSocketAddress(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendPacket(Packet packet){

        packets.add(packet);
    }


    @SneakyThrows
    @Override
    public void run() {
        while (!isInterrupted()){
            if(packets.isEmpty()) sleep(delay);

            Packet msg = packets.poll();

            if(msg == null)continue;

            channel.send(ByteBuffer.wrap(msg.toBytes()), socketAddress);

            sleep(delay);
        }
    }
}
