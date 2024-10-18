package me.luxoru.netsync.commons.client;

import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.protocol.ProtocolVersion;

@NoArgsConstructor
public abstract class ClientBoundPacket extends Packet {

    public ClientBoundPacket(int packetID){
        super(ProtocolVersion.LATEST().getProtocolID(), packetID);
    }
}
