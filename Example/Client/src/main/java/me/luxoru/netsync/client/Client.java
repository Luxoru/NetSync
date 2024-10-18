package me.luxoru.netsync.client;

import me.luxoru.netsync.client.packet.ClientBoundPacketHandler;
import me.luxoru.netsync.client.packet.ClientPacketManager;
import me.luxoru.netsync.commons.PacketSendable;
import me.luxoru.netsync.commons.server.ServerBoundConnectionPacket;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Client {

    private int clientID;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Send host to connect to: ");
        String host = scanner.nextLine();
        System.out.println("Send port of server: ");
        int port = scanner.nextInt();

        InetSocketAddress serverAddr = new InetSocketAddress(host, port);

        System.out.printf("Sending connection request packet to %s:%s%n", host, port);

        ClientPacketManager clientPacketManager = new ClientPacketManager("localhost", 8080,20, new ClientBoundPacketHandler(this));

        clientPacketManager.start();

        clientPacketManager.sendPacket(new PacketSendable(new ServerBoundConnectionPacket(), serverAddr));


    }

    public static void main(String[] args) {
        new Client();
    }

    public void setID(int clientID) {
        this.clientID = clientID;
    }
}
