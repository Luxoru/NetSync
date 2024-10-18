package me.luxoru.netsync.server;

import lombok.Getter;
import lombok.SneakyThrows;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.PacketFactory;
import me.luxoru.netsync.commons.PacketManager;
import me.luxoru.netsync.server.packet.ServerPacketManager;
import me.luxoru.netsync.server.packet.handler.ServerBoundPacketHandler;

import java.net.InetAddress;
import java.util.Map;

public class Server {

    private final ServerInfo serverInfo;
    @Getter
    private final ServerPacketManager serverPacketManager;

    @SneakyThrows
    public Server(int port){

        String host = InetAddress.getLocalHost().getHostAddress();
        this.serverInfo = new ServerInfo(host, port);

        serverPacketManager = new ServerPacketManager(port, 20, new ServerBoundPacketHandler(this));
        serverPacketManager.start();
        Map<Integer, Class<? extends Packet>> packets = ServerPacketManager.getPackets();
        PacketFactory.init(packets);

        System.out.printf("Server running on %s:%s%n", serverInfo.getHost(), serverInfo.getPort());
    }

    public static void main(String[] args) {
        if(args.length != 1)return;
        int port = Integer.parseInt(args[0]);

        new Server(port);
    }

}
