package me.luxoru.netsync.server;

import lombok.SneakyThrows;
import me.luxoru.netsync.commons.PacketManager;
import me.luxoru.netsync.server.packet.ServerPacketManager;
import me.luxoru.netsync.server.packet.handler.ServerBoundPacketHandler;

import java.net.InetAddress;

public class Server {

    private final ServerInfo serverInfo;

    @SneakyThrows
    public Server(int port){

        String host = InetAddress.getLocalHost().getHostAddress();
        this.serverInfo = new ServerInfo(host, port);

        new ServerPacketManager(port, 20, new ServerBoundPacketHandler(this)).start();

        System.out.printf("Server running on %s:%s%n", serverInfo.getHost(), serverInfo.getPort());
    }

    public static void main(String[] args) {
        if(args.length != 1)return;
        int port = Integer.parseInt(args[0]);

        new Server(port);
    }

}
