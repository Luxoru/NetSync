package me.luxoru.netsync.commons.client;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClientBoundMessageAckPacket extends ClientBoundPacket{


    public ClientBoundMessageAckPacket(int clientID) {
        super(clientID, 2);
    }
}
