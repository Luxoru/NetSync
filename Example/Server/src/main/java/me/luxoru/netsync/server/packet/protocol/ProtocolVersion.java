package me.luxoru.netsync.server.packet.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ProtocolVersion {

    V1(1);

    private static final ProtocolVersion[] PROTOCOL_VERSIONS = values();

    private final int protocolID;


    public static ProtocolVersion LATEST(){
        Stream<ProtocolVersion> sorted = Arrays.stream(PROTOCOL_VERSIONS).sorted();
        return sorted.toList().getLast();
    }


}
