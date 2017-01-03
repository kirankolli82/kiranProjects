package com.kiran.directoryviewer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran Kolli on 05-12-2016.
 */
public class DirectoryContents {

    private final List<Directory> directories;
    private final List<File> files;
    private final String error;

    public DirectoryContents(List<Directory> directories, List<File> files, String error) {
        this.directories = directories;
        this.files = files;
        this.error = error;
    }

    public List<Directory> getDirectories() {
        return new ArrayList<>(directories);
    }

    public List<File> getFiles() {
        return new ArrayList<>(files);
    }
}
