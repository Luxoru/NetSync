package me.luxoru.netsync.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.InetSocketAddress;

public record User(int userID, InetSocketAddress socketAddress) {

}
