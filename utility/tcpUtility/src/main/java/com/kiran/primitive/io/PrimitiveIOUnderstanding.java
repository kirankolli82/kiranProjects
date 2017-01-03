package com.kiran.primitive.io;

/**
 * Created by Kiran Kolli on 17-10-2016.
 */
public class PrimitiveIOUnderstanding {

    private static final byte buffer[] = new byte[8];

    /**
     * Little endian way of writing long values
     *
     * @param value Long value to write
     */
    public static void writeLongPrimitive(long value) {
        //noinspection PointlessBitwiseExpression
        buffer[0] = (byte) (value >>> 0);
        buffer[1] = (byte) (value >>> 8);
        buffer[2] = (byte) (value >>> 16);
        buffer[3] = (byte) (value >>> 24);
        buffer[4] = (byte) (value >>> 32);
        buffer[5] = (byte) (value >>> 40);
        buffer[6] = (byte) (value >>> 48);
        buffer[7] = (byte) (value >>> 56);
    }

    public static long readLongPrimitive() {
        //noinspection ShiftOutOfRange,PointlessBitwiseExpression
        return ((long) (buffer[7] << 56) +
                (long) ((buffer[6] & 255) << 48) + // & 255 done in order to read bytes in context since 11111111 is actually <somebits>11111111<somebits>
                (long) ((buffer[5] & 255) << 40) + // read in isoltaion it will read as -128 but in context will read properly. 255 is an int and reads as
                (long) ((buffer[4] & 255) << 32) +  // 00000000000000000000000011111111
                (long) ((buffer[3] & 255) << 24) +
                ((buffer[2] & 255) << 16) +
                ((buffer[1] & 255) << 8) +
                ((buffer[0] & 255) << 0));
    }
}
