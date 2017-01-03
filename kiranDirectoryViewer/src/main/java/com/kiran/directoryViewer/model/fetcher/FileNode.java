package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.AbstractDiskResident;
import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Collection;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public class FileNode implements DirectoryStructureNode {
    private final CommonDirectoryStructureNodeState commonNodeState;
    private final DirectoryStructureNode parent;
    private final Path absolutePath;
    private final FileTime createdTime;
    private final FileTime modifiedTime;
    private final long size;


    FileNode(DirectoryStructureNode parent, Path absolutePath, FileTime createdTime, FileTime modifiedTime, long size) {
        this.parent = parent;
        this.commonNodeState = new CommonDirectoryStructureNodeState(absolutePath, createdTime, modifiedTime);
        this.absolutePath = absolutePath;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.size = size;
    }

    @Override
    public DiskResident toDiskResident() {
        return new AbstractDiskResident(absolutePath, size, createdTime, modifiedTime) {
            @Override
            public Type getType() {
                return Type.FILE;
            }
        };
    }

    @Override
    public long getSizeInBytes() {
        return this.size;
    }

    @Override
    public void addChildrenTo(Collection<DiskResident> childrenCollector) {
        //DO NOTHING, NO CHILDREN TO ADD
    }

    @Override
    public boolean isLaterThan(DirectoryStructureNode otherNode) {
        if (!(otherNode instanceof FileNode)) {
            return false;
        }

        FileNode other = (FileNode) otherNode;
        return this.commonNodeState.isLaterThan(other.commonNodeState);
    }

    @Override
    public DirectoryStructureNode findChild(Path absolutePath) {
        return null;
    }

    @Override
    public boolean removeChild(DirectoryStructureNode child) {
        return false;
    }

    @Override
    public boolean addChild(DirectoryStructureNode child) {
        return false;
    }

    @Override
    public DirectoryStructureNode getParent() {
        return this.parent;
    }

    @Override
    public Path getPath() {
        return this.commonNodeState.getAbsolutePath();
    }

    @Override
    public boolean compareAndReplaceChild(DirectoryStructureNode currentChild, DirectoryStructureNode newChild) {
        return false;
    }
}
