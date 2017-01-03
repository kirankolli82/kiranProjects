package com.kiran.directoryViewer.model;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public abstract class AbstractDiskResident implements DiskResident {
    private final Path absolutePath;
    private final long size;
    private final FileTime createdTime;
    private final FileTime modifiedTime;

    protected AbstractDiskResident(Path absolutePath, long size, FileTime createdTime, FileTime modifiedTime) {
        this.absolutePath = absolutePath;
        this.size = size;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    @Override
    public void exportTo(Exporter exporter) {
        exporter.setAbsolutePath(absolutePath)
                .setSize(size)
                .setCreatedTime(createdTime)
                .setModifiedTime(modifiedTime);
    }


}
