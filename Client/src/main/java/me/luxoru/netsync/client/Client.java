package me.luxoru.netsync.client;

import me.luxoru.netsync.commons.PacketWriter;
import me.luxoru.netsync.commons.server.ServerBoundMessagePacket;

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

        for(int i =0; i<1000;i++){
            ServerBoundMessagePacket helloServer = new ServerBoundMessagePacket("HELLO SERVER", i);
            writer.sendPacket(helloServer);
        }

    }

    public static void main(String[] args) {
        new Client();
    }

}
