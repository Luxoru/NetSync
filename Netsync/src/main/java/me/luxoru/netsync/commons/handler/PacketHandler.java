package me.luxoru.netsync.commons.handler;

import me.luxoru.netsync.commons.Packet;

import java.net.InetSocketAddress;

public interface PacketHandler {

     <T extends Packet> void handle(T packet, InetSocketAddress remoteAddress);

}
