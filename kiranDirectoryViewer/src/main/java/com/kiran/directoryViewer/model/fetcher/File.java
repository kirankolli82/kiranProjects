package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
class File implements DiskResidentConvertable {
    private final String name;
    private final Path absolutePath;
    private final FileTime createdTime;
    private final FileTime modifiedTime;
    private final long size;
    private final Directory parent;

    File(String name, Path absolutePath, FileTime createdTime, FileTime modifiedTime, long size, Directory parent) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.size = size;
        this.parent = parent;
    }

    Directory getParent() {
        return parent;
    }

    Path getAbsolutePath() {
        return absolutePath;
    }

    long getSize() {
        return size;
    }

    @Override
    public DiskResident toDiskResident() {
        return new com.kiran.directoryViewer.model.File(absolutePath, size, createdTime, modifiedTime);
    }
}
