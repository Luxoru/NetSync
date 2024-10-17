package me.luxoru.netsync.commons.client;

import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.protocol.ProtocolVersion;

@NoArgsConstructor
public abstract class ClientBoundPacket extends Packet {

    private int clientID;

    public ClientBoundPacket(int clientID, int packetID){
        super(ProtocolVersion.LATEST().getProtocolID(), packetID);
        this.clientID = clientID;
    }

    @Override
    public byte[] toBytes() {
        byte[] bytes = super.toBytes();
        byte[] newBytes = new byte[bytes.length+2];
        System.arraycopy(bytes, 0, newBytes, 0, bytes.length);

        byte upperClientID = (byte) (clientID >> 8);
        byte lowerClientID = (byte) clientID;

        newBytes[bytes.length] = upperClientID;
        newBytes[bytes.length+1] = lowerClientID;

        return newBytes;
    }

    @Override
    public ClientBoundPacket fromBytes(byte[] bytes) {
        super.fromBytes(bytes);
        this.clientID = (bytes[4] << 8 )| (bytes[5] & 0xFF);
        return this;
    }
}
