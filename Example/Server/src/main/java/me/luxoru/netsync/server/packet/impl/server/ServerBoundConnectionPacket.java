package me.luxoru.netsync.server.packet.impl.server;

import lombok.Getter;

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
