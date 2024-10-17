package me.luxoru.netsync.commons;


import java.util.Arrays;

public class PacketBuilder {

    public static Packet readBytes(byte[] bytes){

        int packetId = ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);

        return PacketFactory.createPacket(packetId, bytes);
    }

}
