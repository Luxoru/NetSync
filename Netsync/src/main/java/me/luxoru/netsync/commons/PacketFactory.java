package me.luxoru.netsync.commons;



import com.google.common.base.Preconditions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PacketFactory {

    private static Map<Integer, Class<? extends Packet>> packets = new HashMap<>();
    private static boolean isInitialised;

    public static void init(Map<Integer, Class<? extends Packet>> packets){
        PacketFactory.packets = packets;
        isInitialised = true;
    }



    @SuppressWarnings("unchecked")
    public static <T extends Packet> T createPacket(int packetID, byte[] data){
        if(!isInitialised)throw new IllegalStateException("PacketFactory not initialsied");
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
