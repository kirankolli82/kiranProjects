package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.util.Collection;

/**
 * Created by Kiran Kolli on 01-01-2017.
 */
public abstract class DummyNode implements DirectoryStructureNode {
    private final CommonDirectoryStructureNodeState commonNodeState;
    private final DirectoryStructureNode parent;

    DummyNode(Path absolutePath, DirectoryStructureNode parent) {
        this.commonNodeState = new CommonDirectoryStructureNodeState(absolutePath, null, null);
        this.parent = parent;
    }

    @Override
    public boolean isLaterThan(DirectoryStructureNode otherNode) {
        if (!(otherNode instanceof DummyNode)) {
            return false;
        }

        DummyNode other = (DummyNode) otherNode;
        return this.commonNodeState.isLaterThan(other.commonNodeState);
    }

    @Override
    public long getSizeInBytes() {
        return SIZE_NOT_KNOWN;
    }

    @Override
    public void addChildrenTo(Collection<DiskResident> childrenCollector) {
        //DO NOTHING, CHILDREN ARE NOT KNOWN TO DUMMY NODES
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
        //NO CHILDREN FOR DUMMY NODES
        return false;
    }
}
