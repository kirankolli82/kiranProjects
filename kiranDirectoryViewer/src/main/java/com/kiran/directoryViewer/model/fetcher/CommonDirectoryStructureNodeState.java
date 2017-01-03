package com.kiran.directoryViewer.model.fetcher;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

/**
 * Created by Kiran Kolli on 01-01-2017.
 */
class CommonDirectoryStructureNodeState {

    private final Path absolutePath;
    private final FileTime createdTime;
    private final FileTime modifiedTime;

    CommonDirectoryStructureNodeState(Path absolutePath,
                                      FileTime createdTime, FileTime modifiedTime) {
        this.absolutePath = absolutePath;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }


    boolean isSamePath(Path absolutePath) {
        return Objects.equals(this.absolutePath, absolutePath);
    }


    boolean isSamePath(CommonDirectoryStructureNodeState otherState) {
        return Objects.equals(this.absolutePath, otherState.absolutePath);
    }


    boolean isLaterThan(CommonDirectoryStructureNodeState otherNode) {

        boolean isOthersCreateTimeGreater = isOtherFileTimeGreater(this.createdTime, otherNode.createdTime);

        if (isOthersCreateTimeGreater) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (Objects.equals(this.createdTime, otherNode.createdTime)) {
            return isOtherFileTimeGreater(this.modifiedTime, otherNode.modifiedTime);
        } else {
            return false;
        }
    }

    Path getAbsolutePath() {
        return absolutePath;
    }

    private boolean isOtherFileTimeGreater(FileTime mine, FileTime other) {
        if (other == null) {
            return false;
        }

        //noinspection SimplifiableIfStatement
        if (mine == null) {
            return true;
        }

        return mine.compareTo(other) < 0;
    }
}
