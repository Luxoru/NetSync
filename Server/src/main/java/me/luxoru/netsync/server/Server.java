package me.luxoru.netsync.server;

import lombok.SneakyThrows;
import me.luxoru.netsync.commons.PacketReader;
import me.luxoru.netsync.server.packet.handler.ServerBoundPacketHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {

    private final ServerInfo serverInfo;

    @SneakyThrows
    public Server(int port){

        String host = InetAddress.getLocalHost().getHostAddress();
        this.serverInfo = new ServerInfo(host, port);

        new PacketReader(serverInfo.getPort(), 100, new ServerBoundPacketHandler()).start();
        System.out.printf("Server running on %s:%s%n", serverInfo.getHost(), serverInfo.getPort());
    }

    public static void main(String[] args) {
        if(args.length != 1)return;
        int port = Integer.parseInt(args[0]);

        new Server(port);
    }

}
