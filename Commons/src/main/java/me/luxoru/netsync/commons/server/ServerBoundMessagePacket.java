package me.luxoru.netsync.commons.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.utils.ArrayUtils;

@Getter
@NoArgsConstructor
public class ServerBoundMessagePacket extends ServerBoundPacket {

    private String message;

    public ServerBoundMessagePacket(String message, int userID) {
        super(userID, 1);
        this.message = message;
    }


    @Override
    public byte[] toBytes() {
        byte[] bytes = super.toBytes();
        byte[] messageBytes = message.getBytes();
        byte[] newBytes = new byte[bytes.length+messageBytes.length];
        System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
        System.arraycopy(messageBytes, 0, newBytes, bytes.length, messageBytes.length);
        return newBytes;
    }


    @Override
    public ServerBoundMessagePacket fromBytes(byte[] bytes) {
        super.fromBytes(bytes);
        this.message = new String(ArrayUtils.getFrom(7, bytes));
        return this;

    }

}
