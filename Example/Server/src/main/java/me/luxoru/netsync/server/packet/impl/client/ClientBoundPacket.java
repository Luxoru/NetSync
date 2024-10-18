package me.luxoru.netsync.server.packet.impl.client;

import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.server.packet.protocol.ProtocolVersion;

@NoArgsConstructor
public abstract class ClientBoundPacket extends Packet {

    public ClientBoundPacket(int packetID){
        super(ProtocolVersion.LATEST().getProtocolID(), packetID);
    }
}
