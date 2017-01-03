package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
public class Directory implements DiskResidentConvertable {
    private static final int UNKNOWN_SIZE = -1;
    private final String name;
    private final Path absolutePath;
    private final FileTime createdTime;
    private final FileTime modifiedTime;
    private final List<File> fileChildren = new ArrayList<>();
    private final List<Directory> dirChildren = new ArrayList<>();
    private Directory parent;
    private boolean scanningComplete = false;


    public Directory(String name, Path absolutePath, FileTime createdTime, FileTime modifiedTime) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    void addChild(File file) {
        fileChildren.add(file);
    }

    void addChild(Directory directory) {
        dirChildren.add(directory);
        directory.parent = this;
    }

    Directory getParent() {
        return parent;
    }

    Path getAbsolutePath() {
        return absolutePath;
    }

    Directory getChildDir(Path absolutePath) {
        //noinspection OptionalGetWithoutIsPresent
        return dirChildren.stream()
                .filter(directory -> Objects.equals(absolutePath, directory.getAbsolutePath()))
                .findFirst().get();
    }

    List<File> getFileChildren() {
        return fileChildren;
    }

    List<Directory> getDirChildren() {
        return dirChildren;
    }

    void setScanningComplete(boolean scanningComplete) {
        this.scanningComplete = scanningComplete;
    }

    private boolean isFullTreeScanningComplete() {
        if (!scanningComplete) {
            return false;
        }

        return dirChildren.stream().noneMatch(directory -> !directory.isFullTreeScanningComplete());
    }

    boolean isScanningComplete() {
        return scanningComplete;
    }

    long getSize() {
        if (!isFullTreeScanningComplete()) {
            return UNKNOWN_SIZE;
        }

        long size = fileChildren.stream().map(File::getSize).reduce((result, currFileSize) -> result + currFileSize).get();

        return size +
                dirChildren.stream().map(Directory::getSize).reduce((result, currDirSize) -> result + currDirSize).get();

    }

    @Override
    public DiskResident toDiskResident() {
        return new com.kiran.directoryViewer.model.Directory(absolutePath, getSize(), createdTime, modifiedTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Directory directory = (Directory) o;

        return absolutePath != null ? absolutePath.equals(directory.absolutePath) : directory.absolutePath == null;
    }

    @Override
    public int hashCode() {
        return absolutePath != null ? absolutePath.hashCode() : 0;
    }
}
