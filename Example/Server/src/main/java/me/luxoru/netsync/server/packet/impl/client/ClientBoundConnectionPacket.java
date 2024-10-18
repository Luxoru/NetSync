package me.luxoru.netsync.server.packet.impl.client;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientBoundConnectionPacket extends ClientBoundPacket{
    private int clientID;

    public ClientBoundConnectionPacket(int clientID){
        super(2);
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
