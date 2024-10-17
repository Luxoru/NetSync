package me.luxoru.netsync.client;

import me.luxoru.netsync.commons.PacketWriter;
import me.luxoru.netsync.commons.server.connection.ServerBoundMessagePacket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client {

    public Client(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Send host to connect to: ");
        String host = scanner.nextLine();
        System.out.println("Send port of server: ");
        int port = scanner.nextInt();

        System.out.printf("Sending connection request packet to %s:%s%n", host, port);

        PacketWriter writer = new PacketWriter(host, port, 100);
        writer.start();
        ServerBoundMessagePacket helloServer = new ServerBoundMessagePacket("HELLO SERVER", 101);
        System.out.println("PACKET ID: "+helloServer.getPacketID());
        writer.sendPacket(helloServer);

    }

    public static void main(String[] args) {
        new Client();
    }

}
