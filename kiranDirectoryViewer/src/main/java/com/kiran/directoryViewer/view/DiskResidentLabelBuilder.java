package com.kiran.directoryViewer.view;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class DiskResidentLabelBuilder implements DiskResident.Exporter {
    private DiskResident.Type type;
    private long size;
    private Path absolutePath;
    private FileTime createdTime;
    private FileTime modifiedTime;

    public DiskResidentLabelBuilder setType(DiskResident.Type type) {
        this.type = type;
        return this;
    }

    public DiskResidentLabelBuilder setSize(long size) {
        this.size = size;
        return this;
    }

    public DiskResidentLabelBuilder setAbsolutePath(Path absolutePath) {
        this.absolutePath = absolutePath;
        return this;
    }

    public DiskResidentLabelBuilder setCreatedTime(FileTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public DiskResidentLabelBuilder setModifiedTime(FileTime modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public DiskResidentLabel createDiskResidentLabel() {
        return new DiskResidentLabel(type, size, absolutePath, createdTime, modifiedTime);
    }
}