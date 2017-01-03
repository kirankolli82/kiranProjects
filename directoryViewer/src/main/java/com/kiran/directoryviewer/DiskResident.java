package com.kiran.directoryviewer;

import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public interface DiskResident {
    long UNKNOWN_SIZE = -1;

    enum DiskResidentType {FILE, DIRECTORY, FILESTORE}

    Iterator<DiskResident> getChildren();

    void exportTo(DiskResidentExporter exporter);

    DiskResidentType getType();

    interface DiskResidentExporter {
        void setName(String name);

        void setSize(String sizeDescriptor);

        void setAbsolutePath(String absolutePath);

        void setCreatedTime(FileTime createdTime);

        void setModifiedTime(FileTime modifiedTime);

        void setType(DiskResidentType diskResidentType);

        void addChildren(Collection<DiskResident> children);

        void addChild(DiskResident child);
    }
}


