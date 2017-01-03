package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
class FileStore extends Directory {
    private final long size;
    private final DiskResident diskResident;

    FileStore(String name, Path absolutePath, FileTime createdTime, FileTime modifiedTime, long size) {
        super(name, absolutePath, createdTime, modifiedTime);
        this.diskResident = new com.kiran.directoryViewer.model.FileStore(absolutePath, size, null, null);
        this.size = size;
    }

    @Override
    long getSize() {
        return this.size;
    }

    public DiskResident toDiskResident() {
        return this.diskResident;
    }
}
