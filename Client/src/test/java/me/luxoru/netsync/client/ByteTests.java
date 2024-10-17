package me.luxoru.netsync.client;

import org.junit.Test;


public class ByteTests {




    @Test
    public void sa(){
        int num = 65535;

        // Split the int into two bytes
        byte highByte = (byte) (num >> 8);  // Higher byte
        byte lowByte = (byte) num;           // Lower byte

        System.out.println("High byte: " + highByte);
        System.out.println("Low byte: " + lowByte);

        // Combine the two bytes back into an int
        int reconstructedNum = ((highByte & 0xFF) << 8) | (lowByte & 0xFF);

        System.out.println("Reconstructed int: " + reconstructedNum);

        byte[] bytes = new byte[]{72, 69, 76, 76, 79, 32, 83, 69, 82, 86, 69, 82};

        System.out.println(new String(bytes));
    }

    @Test
    public void sda(){

    }

}
