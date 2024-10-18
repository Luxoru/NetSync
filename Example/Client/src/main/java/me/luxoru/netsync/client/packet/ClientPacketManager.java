package me.luxoru.netsync.client.packet;

import me.luxoru.netsync.commons.PacketManager;
import me.luxoru.netsync.commons.handler.PacketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClientPacketManager extends PacketManager {
    private InetSocketAddress remoteAddr;

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
