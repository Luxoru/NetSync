package me.luxoru.netsync.client.packet;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.luxoru.netsync.client.Client;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.client.ClientBoundConnectionPacket;
import me.luxoru.netsync.commons.client.ClientBoundPacket;
import me.luxoru.netsync.commons.handler.PacketHandler;

@RequiredArgsConstructor
public class ClientBoundPacketHandler implements PacketHandler {

    private final Client client;

    @Override
    public <T extends Packet> void handle(T packet) {
        if(!(packet instanceof ClientBoundPacket))return;

        if(packet instanceof ClientBoundConnectionPacket connectionPacket){
            int clientID = connectionPacket.getClientID();
            client.setID(clientID);
            System.out.println("CLIENT ID IS NOW: "+clientID);
        }
    }
}
