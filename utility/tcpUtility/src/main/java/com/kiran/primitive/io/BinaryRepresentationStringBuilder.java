package com.kiran.primitive.io;

/**
 * Created by Kiran Kolli on 17-10-2016.
 */
public class BinaryRepresentationStringBuilder {

    public static void printLong(long value) {
        //noinspection PointlessBitwiseExpression
        System.out.println(
                getBinaryRepresentation((byte) (value >>> 56))
                        + getBinaryRepresentation((byte) (value >>> 48))
                        + getBinaryRepresentation((byte) (value >>> 40))
                        + getBinaryRepresentation((byte) (value >>> 32))
                        + getBinaryRepresentation((byte) (value >>> 24))
                        + getBinaryRepresentation((byte) (value >>> 16))
                        + getBinaryRepresentation((byte) (value >>> 8))
                        + getBinaryRepresentation((byte) (value >>> 0)));
        System.out.println("--------------------------------");
    }

    public static void printInt(int value) {
        //noinspection PointlessBitwiseExpression
        System.out.println(
                getBinaryRepresentation((byte) (value >>> 24))
                        + getBinaryRepresentation((byte) (value >>> 16))
                        + getBinaryRepresentation((byte) (value >>> 8))
                        + getBinaryRepresentation((byte) (value >>> 0)));

        System.out.println("--------------------------------");
    }

    public static String getBinaryRepresentation(Byte value) {
        StringBuilder result = new StringBuilder();

        if (value < 0) {
            if (value == Byte.MIN_VALUE) {
                //to avoid stack overflow
                return "10000000"; //https://en.wikipedia.org/wiki/Two%27s_complement#Most_negative_number
            }

            String positiveRepresentation = getBinaryRepresentation((byte) -value);
            boolean isFirstOneReached = false;
            for (int index = positiveRepresentation.length(); index >= 0; index--) {
                if (!isFirstOneReached && positiveRepresentation.charAt(index) != '1') {
                    result.insert(0, 0);
                } else {
                    if (!isFirstOneReached) {
                        isFirstOneReached = true;
                        result.insert(0, 1);
                        continue;
                    }

                    if (positiveRepresentation.charAt(index) == '1') {
                        result.insert(0, 0);
                    } else {
                        result.insert(0, 1);
                    }
                }
            }
        } else {
            insertByMod2(value, result, false);
            for (int index = result.length(); index < 8; index++) {
                result.insert(0, 0);
            }
        }

        return result.toString();
    }

    private static void insertByMod2(Byte value, StringBuilder result, boolean wasPreviousEven) {
        if (value > 0) {
            if (value % 2 == 0) {
                insertByMod2((byte) (value / 2), result, true);
            } else {
                insertByMod2((byte) (value / 2), result, false);
            }

            if (wasPreviousEven) {
                result.insert(0, 0);
            } else {
                result.insert(0, 1);
            }
        }
    }
}
