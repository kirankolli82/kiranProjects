package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.util.Collection;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public interface DirectoryStructureNode {
    long SIZE_NOT_KNOWN = -1;

    DiskResident toDiskResident();

    long getSizeInBytes();

    boolean isLaterThan(DirectoryStructureNode otherNode);

    void addChildrenTo(Collection<DiskResident> childrenCollector);

    DirectoryStructureNode findChild(Path absolutePath);

    boolean removeChild(DirectoryStructureNode child);

    boolean addChild(DirectoryStructureNode child);

    boolean compareAndReplaceChild(DirectoryStructureNode currentChild, DirectoryStructureNode newChild);

    DirectoryStructureNode getParent();

    Path getPath();
}
