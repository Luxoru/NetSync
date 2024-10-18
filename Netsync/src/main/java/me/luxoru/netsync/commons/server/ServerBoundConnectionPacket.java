package me.luxoru.netsync.commons.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.utils.ArrayUtils;

import java.net.InetSocketAddress;

@Getter
public class ServerBoundConnectionPacket extends ServerBoundPacket{



    public ServerBoundConnectionPacket(){
        super(1);

    }


    @Override
    public byte[] toBytes() {
        return super.toBytes();
    }

    @Override
    public ServerBoundConnectionPacket fromBytes(byte[] bytes) {
        super.fromBytes(bytes);

        return this;
    }
}
