package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.AbstractDiskResident;
import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public class FileStoreNode extends DirectoryNode {

    private final long size;
    private final DiskResident diskResident;

    FileStoreNode(Path absolutePath, FileTime createdTime, FileTime modifiedTime, long size) {
        this(absolutePath, createdTime, modifiedTime, size, null);
    }

    @SuppressWarnings("WeakerAccess")
    FileStoreNode(Path absolutePath, FileTime createdTime, FileTime modifiedTime, long size, List<DirectoryStructureNode> children) {
        super(absolutePath, createdTime, modifiedTime, null, children);
        this.diskResident = new AbstractDiskResident(absolutePath, size, createdTime, modifiedTime) {
            @Override
            public Type getType() {
                return Type.FILESTORE;
            }
        };
        this.size = size;
    }

    @Override
    public DiskResident toDiskResident() {
        return this.diskResident;
    }

    @Override
    public long getSizeInBytes() {
        return size;
    }
}
