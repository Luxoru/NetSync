package me.luxoru.netsync.commons.utils;

import java.lang.reflect.Array;

public class ArrayUtils {

    @SuppressWarnings("unchecked")
    public static  <T> T[] getFromRange(int start, int to, T[] array){

        T[] arr = (T[]) Array.newInstance(array.getClass().getComponentType(), to-start);

        if (to - start >= 0) System.arraycopy(array, start, arr, 0, to - start);

        return arr;
    }

    public static byte[] getFromRange(int start, int end, byte[] array) {

        byte[] result = new byte[end - start];

        if (end - start >= 0) System.arraycopy(array, start, result, 0, end - start);

        return result;
    }

    public static byte[] getFrom(int start, byte[] array){

        byte[] arr = new byte[array.length-start];

        for(int i = start; i < array.length; i++){
            arr[i-start] = array[i];
        }

        return arr;
    }

}
