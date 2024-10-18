package me.luxoru.netsync.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServerInfo {

    private final String host;
    private final int port;

}
