package me.luxoru.netsync.commons.handler;

import me.luxoru.netsync.commons.Packet;

public interface PacketHandler {

     <T extends Packet> void handle(T packet);

}
