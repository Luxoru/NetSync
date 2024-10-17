package me.luxoru.netsync.commons.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.protocol.ProtocolVersion;

@Getter
@NoArgsConstructor
public abstract class ServerBoundPacket extends Packet {

    private int userID;

    public ServerBoundPacket(int userID, int packetID) {
        super(ProtocolVersion.LATEST().getProtocolID(), packetID);
        this.userID = userID;
    }

    @Override
    public byte[] toBytes() {
        byte[] bytes = super.toBytes();
        byte upperest = (byte) (userID >> 16);
        byte upper = (byte) (userID >> 8);
        byte lower = (byte) userID;
        byte[] newBytes = new byte[bytes.length + 3];
        System.arraycopy(bytes, 0, newBytes, 0, bytes.length);


        newBytes[bytes.length] = upperest;
        newBytes[bytes.length + 1] = upper;
        newBytes[bytes.length + 2] = lower;
        return newBytes;
    }

    @Override
    public ServerBoundPacket fromBytes(byte[] bytes) {
        super.fromBytes(bytes);
        userID = (bytes[4] << 16) | (bytes[5] << 8) | (bytes[6] & 0xFF);
        return this;
    }
}
