package me.luxoru.netsync.commons;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  <p>Class represents abstract packet to be sent by either the Client or Server</p>
 *  <p>All Packets must have a {@link Packet#protocolID} to maintain versioning between Client and Server. Packets must also have
 *  {@link Packet#packetID} for identifying the packet which has been been received</p>
 *  <br>
 *  <p>Both {@link Packet#protocolID} and {@link Packet#packetID} are 2 bytes long with the format of the packet as follows
 *  |PROCOTOL ID|PACKET ID|DATA|</p>
 *
 *  <br>
 *
 *  <p>For now there are no limits to the length of the data of the packet</p>
 *
 *  <p>A {@link NoArgsConstructor} is also used for initialising the object when reading bytes from the byteArray</p>
 *
 * @author Luxoru
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Packet {


    private int protocolID;

    private int packetID;


    /**
     * Converts the {@link Packet#protocolID} and {@link Packet#packetID} to bytes both having a combined length of 4 bytes
     * @return 4 byte array with first 2 bytes for {@link Packet#protocolID} and the last 2 bytes {@link Packet#packetID}
     */
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

    /**
     * Takes in byte array with first 2 bytes forming {@link Packet#protocolID} and last 2 bytes forming {@link Packet#packetID}
     * @param bytes - Byte array from packet
     * @return current packet object with updated params
     */
    public Packet fromBytes(byte[] bytes){

        this.protocolID = (bytes[0] << 8) | (bytes[1] & 0xFF);
        this.packetID = (bytes[2] << 8) | (bytes[3] & 0xFF);

        return this;
    }
}
