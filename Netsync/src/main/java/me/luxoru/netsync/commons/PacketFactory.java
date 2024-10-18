package me.luxoru.netsync.commons;



import com.google.common.base.Preconditions;
import me.luxoru.netsync.commons.server.ServerBoundConnectionPacket;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PacketFactory {

    private static final Map<Integer, Class<? extends Packet>> packets = new HashMap<>();


    static {

        packets.put(1, ServerBoundConnectionPacket.class);

    }

    @SuppressWarnings("unchecked")
    public static <T extends Packet> T createPacket(int packetID, byte[] data){
        Class<? extends Packet> clazz = packets.get(packetID);

        Preconditions.checkNotNull(clazz, "Packet doesnt exist with ID: "+packetID);

        try {
            Constructor<? extends Packet> constructor = clazz.getConstructor();
            Packet packet = constructor.newInstance();
            packet.fromBytes(data);
            return (T) packet;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


}
