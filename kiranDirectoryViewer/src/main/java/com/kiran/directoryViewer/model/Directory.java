package com.kiran.directoryViewer.model;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public class Directory extends AbstractDiskResident {


    public Directory(Path absolutePath, long size,
                     FileTime createdTime, FileTime modifiedTime) {
        super(absolutePath, size, createdTime, modifiedTime);
    }


    @Override
    public Type getType() {
        return Type.DIRECTORY;
    }
}
