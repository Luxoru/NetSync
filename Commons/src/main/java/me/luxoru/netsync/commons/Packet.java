package me.luxoru.netsync.commons;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 *  PACKETS WILL BE SEND AND RECIEVED IN THSI FORMAT
 *
 *  ---------------------------------------------------------------------------------------------------
 *
 *  [protcolID] [Packet id]  [Data]
 *     2 bytes    2 bytes    X bytes
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Packet {

    // Represented as hex e.g 0x01
    private int protocolID;

    private int packetID;



    public byte[] toBytes(){
        byte higherProtocol = (byte) (protocolID >> 8);
        byte lowerProtocol = (byte) protocolID;
        byte higherPacket = (byte) (packetID >> 8);
        byte lowerPacket = (byte) packetID;
        byte[] bytes = new byte[4];
        bytes[0] = higherProtocol;
        bytes[1] = lowerProtocol;
        bytes[2] = higherPacket;
        bytes[3] = lowerPacket;
        return bytes;
    }

    public Packet fromBytes(byte[] bytes){

        this.protocolID = (bytes[0] << 8) | (bytes[1] & 0xFF);
        this.packetID = (bytes[2] << 8) | (bytes[3] & 0xFF);

        return this;
    }
}
