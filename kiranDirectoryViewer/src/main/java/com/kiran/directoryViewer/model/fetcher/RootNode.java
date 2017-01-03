package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public class RootNode implements DirectoryStructureNode {

    private final CommonDirectoryStructureNodeState commonNodeState;
    private final List<DirectoryStructureNode> children = new ArrayList<>();

    RootNode(List<DirectoryStructureNode> children, Path absolutePath, FileTime createdTime, FileTime modifiedTime) {
        this.commonNodeState = new CommonDirectoryStructureNodeState(absolutePath, createdTime, modifiedTime);
        if (children != null) {
            this.children.addAll(children);
        }
    }

    @Override
    public DiskResident toDiskResident() {
        return null;
    }


    @Override
    public long getSizeInBytes() {
        return SIZE_NOT_KNOWN;
    }

    @Override
    public void addChildrenTo(Collection<DiskResident> childrenCollector) {
        childrenCollector.addAll(this.children.stream()
                .map(directoryStructureNode -> directoryStructureNode.toDiskResident())
                .collect(Collectors.toList()));
    }

    @Override
    public boolean isLaterThan(DirectoryStructureNode otherNode) {
        if (!(otherNode instanceof RootNode)) {
            return false;
        }

        RootNode other = (RootNode) otherNode;
        return this.commonNodeState.isLaterThan(other.commonNodeState);
    }


    @Override
    public DirectoryStructureNode findChild(Path absolutePath) {
        //noinspection OptionalGetWithoutIsPresent
        return this.children.stream()
                .filter(directoryStructureNode -> Objects.equals(absolutePath, directoryStructureNode.getPath()))
                .findFirst().get();
    }

    @Override
    public boolean removeChild(DirectoryStructureNode child) {
        return this.children.remove(child);
    }

    @Override
    public boolean addChild(DirectoryStructureNode child) {
        return this.children.add(child);
    }

    @Override
    public DirectoryStructureNode getParent() {
        return null;
    }

    @Override
    public Path getPath() {
        return this.commonNodeState.getAbsolutePath();
    }

    @Override
    public boolean compareAndReplaceChild(DirectoryStructureNode currentChild, DirectoryStructureNode newChild) {
        DirectoryStructureNode child = this.findChild(currentChild.getPath());
        if (!Objects.equals(child, currentChild)) {
            return false;
        }

        if (currentChild.isLaterThan(newChild)) {
            return false;
        }

        this.children.remove(child);
        this.children.add(newChild);

        return true;
    }
}
