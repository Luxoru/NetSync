package me.luxoru.netsync.commons.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.protocol.ProtocolVersion;

@Getter
@NoArgsConstructor
public abstract class ServerBoundPacket extends Packet {



    public ServerBoundPacket(int packetID) {
        super(ProtocolVersion.LATEST().getProtocolID(), packetID);
    }


}
