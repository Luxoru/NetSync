package me.luxoru.netsync.client.packet;

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

public class ClientPacketManager extends PacketManager {
    private InetSocketAddress remoteAddr;

    @Getter
    private final static Map<Integer, Class<? extends Packet>> packets = new HashMap<>();

    static {
        packets.put(1, ServerBoundConnectionPacket.class);
        packets.put(2, ClientBoundConnectionPacket.class);
    }

    public ClientPacketManager(String host, int port, long sleepDelay, PacketHandler handler) {
        super(host, port, sleepDelay, handler);
    }

    @Override
    protected void connect() throws IOException {
        this.remoteAddr = new InetSocketAddress(0);
        channel.bind(remoteAddr);
    }

    @Override
    protected boolean shouldRead(InetSocketAddress remoteAddress) {
        return !remoteAddress.equals(remoteAddr);
    }
}
