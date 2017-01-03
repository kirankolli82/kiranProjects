package com.kiran.directoryviewer.diskresidents;

import com.kiran.directoryviewer.DiskResident;

import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public abstract class AbstractDiskResident implements DiskResident {
    private final String fileName;
    private final String absolutePath;
    private final long size;
    private final FileTime createdTime;
    private final FileTime modifiedTime;

    AbstractDiskResident(String fileName, String absolutePath, long size, FileTime createdTime, FileTime modifiedTime) {
        this.fileName = fileName;
        this.absolutePath = absolutePath;
        this.size = size;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    @Override
    public void exportTo(DiskResidentExporter exporter) {
        exporter.setName(fileName);
        exporter.setAbsolutePath(absolutePath);
        exporter.setSize(getSizeDescription(size));
        exporter.setCreatedTime(createdTime);
        exporter.setModifiedTime(modifiedTime);
    }

    private String getSizeDescription(long size) {
        try {
            return getSizeDescription(size, 0);
        } catch (RuntimeException rte) {
            throw rte;
        }
    }

    private String getSizeDescription(double size, int depth) {

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
