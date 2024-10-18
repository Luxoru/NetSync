package me.luxoru.netsync.server.packet;

import lombok.Getter;
import me.luxoru.netsync.commons.Packet;
import me.luxoru.netsync.commons.PacketManager;
import me.luxoru.netsync.commons.handler.PacketHandler;
import me.luxoru.netsync.server.packet.impl.client.ClientBoundConnectionPacket;
import me.luxoru.netsync.server.packet.impl.server.ServerBoundConnectionPacket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerPacketManager extends PacketManager {

    @Getter
    private final static Map<Integer, Class<? extends Packet>> packets = new HashMap<>();

    static {
        packets.put(1, ServerBoundConnectionPacket.class);
        packets.put(2, ClientBoundConnectionPacket.class);
    }

    public ServerPacketManager(int port, long sleepDelay, PacketHandler handler) {
        super(port, sleepDelay, handler);

    }

    @Override
    protected void connect() throws IOException {
        channel.bind(new InetSocketAddress(port));
    }

    @Override
    protected boolean shouldRead(InetSocketAddress remoteAddress) {

        /**
         * Can add logic to check whether server overwhelmed with packets
         */

        return true;
    }
}
