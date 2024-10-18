package me.luxoru.netsync.commons.utils;

import java.lang.reflect.Array;

public class ArrayUtils {

    @SuppressWarnings("unchecked")
    public static  <T> T[] getFromRange(int start, int to, T[] array){

        T[] arr = (T[]) Array.newInstance(array.getClass().getComponentType(), to-start);

        for(int i = start; i < to; i++){
            arr[start-i] = array[i];
        }

        return arr;
    }

    public static byte[] getFromRange(int start, int to, byte[] array){

        byte[] arr = new byte[to-start];

        for(int i = start; i < to; i++){
            arr[start-i] = array[i];
        }

        return arr;
    }

    public static byte[] getFrom(int start, byte[] array){

        byte[] arr = new byte[array.length-start];

        for(int i = start; i < array.length; i++){
            arr[i-start] = array[i];
        }

        return arr;
    }

}
