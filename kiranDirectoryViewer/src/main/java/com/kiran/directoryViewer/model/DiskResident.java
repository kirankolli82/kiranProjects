package com.kiran.directoryViewer.model;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public interface DiskResident {
    long UNKNOWN_SIZE = -1;

    enum Type {FILE, DIRECTORY, FILESTORE}

    void exportTo(Exporter exporter);

    Type getType();

    interface Exporter {
        Exporter setSize(long size);

        Exporter setAbsolutePath(Path absolutePath);

        Exporter setCreatedTime(FileTime createdTime);

        Exporter setModifiedTime(FileTime modifiedTime);

        Exporter setType(Type type);
    }
}


