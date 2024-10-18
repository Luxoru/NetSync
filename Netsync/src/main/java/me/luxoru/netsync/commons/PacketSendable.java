package me.luxoru.netsync.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.InetSocketAddress;

@AllArgsConstructor
@Getter
public class PacketSendable {

    private final Packet packet;
    private final InetSocketAddress socketAddress;

}
