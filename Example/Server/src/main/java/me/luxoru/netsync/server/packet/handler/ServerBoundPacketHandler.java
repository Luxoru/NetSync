package me.luxoru.netsync.server.packet.handler;

import com.google.common.base.Preconditions;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.PacketSendable;
import me.luxoru.netsync.server.packet.impl.client.ClientBoundConnectionPacket;
import me.luxoru.netsync.commons.handler.PacketHandler;
import me.luxoru.netsync.server.packet.impl.server.ServerBoundConnectionPacket;
import me.luxoru.netsync.server.packet.impl.server.ServerBoundPacket;
import me.luxoru.netsync.server.Server;

import java.net.InetSocketAddress;

public class ServerBoundPacketHandler implements PacketHandler {


    private final Server server;

    private static int CLIENT_NO = 1;

    public ServerBoundPacketHandler(Server server) {
        this.server = server;
    }

    @Override
    public <T extends Packet> void handle(T packet, InetSocketAddress socketAddress) {
        Preconditions.checkArgument(packet instanceof ServerBoundPacket, "Didnt get Serverbound packet when listening for serverbound packets");

        if(packet instanceof ServerBoundConnectionPacket connectionPacket){

            ClientBoundConnectionPacket clientBoundConnectionPacket = new ClientBoundConnectionPacket(CLIENT_NO++);
            PacketSendable sendable = new PacketSendable(clientBoundConnectionPacket, socketAddress);


            server.getServerPacketManager().sendPacket(sendable);

        }
    }
}
