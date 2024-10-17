package me.luxoru.netsync.server.packet.handler;

import com.google.common.base.Preconditions;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.handler.PacketHandler;
import me.luxoru.netsync.commons.server.ServerBoundPacket;
import me.luxoru.netsync.commons.server.ServerBoundMessagePacket;

public class ServerBoundPacketHandler implements PacketHandler {



    public ServerBoundPacketHandler() {

    }

    @Override
    public <T extends Packet> void handle(T packet) {
        Preconditions.checkArgument(packet instanceof ServerBoundPacket, "Didnt get Serverbound packet when listening for serverbound packets");

        if(packet instanceof ServerBoundMessagePacket messagePacket){
            System.out.println(messagePacket.getUserID()+">"+messagePacket.getMessage());
        }
    }
}
