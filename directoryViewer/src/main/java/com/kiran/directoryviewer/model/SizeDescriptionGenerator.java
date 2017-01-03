package com.kiran.directoryviewer.model;

/**
 * Created by Kiran Kolli on 06-12-2016.
 */
class SizeDescriptionGenerator {

    static String getSizeDescription(long size) {
        return getSizeDescription(size, 0);
    }

    private static String getSizeDescription(double size, int depth) {

        if (size < 0) {
            return "UNKNOWN SIZE";
        }

        if ((size / 1000) >= 1) {
            return getSizeDescription(size / 1000D, depth + 1);
        }

        switch (depth) {
            case 0:
                return String.format("%3.2g Bytes", size / 1000D);
            case 1:
                return String.format("%3.2g KB", size / 1000D);
            case 2:
                return String.format("%3.2g MB", size / 1000D);
            case 3:
                return String.format("%3.2g GB", size / 1000D);
            case 4:
                return String.format("%3.2g TB", size / 1000D);
            default:
                return "Size calculation error! Cannot compute for depth - " + depth;
        }
    }
}
