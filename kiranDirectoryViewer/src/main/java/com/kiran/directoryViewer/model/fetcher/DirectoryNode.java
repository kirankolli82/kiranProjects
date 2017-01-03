package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.AbstractDiskResident;
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
public class DirectoryNode implements DirectoryStructureNode {

    private final CommonDirectoryStructureNodeState commonNodeState;
    private final Path absolutePath;
    private final FileTime createdTime;
    private final FileTime modifiedTime;
    private final List<DirectoryStructureNode> children = new ArrayList<>();
    private final DirectoryStructureNode parent;

    DirectoryNode(Path absolutePath, FileTime createdTime, FileTime modifiedTime, DirectoryStructureNode parent) {
        this(absolutePath, createdTime, modifiedTime, parent, null);
    }

    DirectoryNode(Path absolutePath, FileTime createdTime, FileTime modifiedTime,
                  DirectoryStructureNode parent, List<DirectoryStructureNode> children) {
        this.commonNodeState = new CommonDirectoryStructureNodeState(absolutePath, createdTime, modifiedTime);
        this.absolutePath = absolutePath;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.parent = parent;
        this.children.addAll(children);
    }

    @Override
    public DirectoryStructureNode getParent() {
        return parent;
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
    public DiskResident toDiskResident() {
        return new AbstractDiskResident(absolutePath, getSizeInBytes(), createdTime, modifiedTime) {
            @Override
            public Type getType() {
                return Type.DIRECTORY;
            }
        };
    }

    @Override
    public long getSizeInBytes() {
        if (hasDummyNode()) {
            return SIZE_NOT_KNOWN;
        }

        //noinspection OptionalGetWithoutIsPresent
        return this.children.stream().map(directoryStructureNode -> directoryStructureNode.getSizeInBytes())
                .reduce((result, sizeInBytes) -> result + sizeInBytes).get();
    }

    @Override
    public void addChildrenTo(Collection<DiskResident> childrenCollector) {
        childrenCollector.addAll(children.stream().
                map(directoryStructureNode -> directoryStructureNode.toDiskResident())
                .collect(Collectors.toList()));
    }

    @Override
    public boolean isLaterThan(DirectoryStructureNode otherNode) {
        if (!(otherNode instanceof DirectoryNode)) {
            return false;
        }

        DirectoryNode other = (DirectoryNode) otherNode;
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

    @Override
    public Path getPath() {
        return this.commonNodeState.getAbsolutePath();
    }

    private boolean hasDummyNode() {
        boolean hasDummyNode = this.children.stream()
                .anyMatch(directoryStructureNode -> directoryStructureNode instanceof DummyNode);

        if (!hasDummyNode) {
            return this.children.stream()
                    .filter(directoryStructureNode -> directoryStructureNode instanceof DirectoryNode)
                    .map(directoryStructureNode -> (DirectoryNode) directoryStructureNode)
                    .anyMatch(directoryNode -> directoryNode.hasDummyNode());
        } else {
            return true;
        }
    }
}
