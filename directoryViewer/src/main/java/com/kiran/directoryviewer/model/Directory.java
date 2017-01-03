package com.kiran.directoryviewer.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;

import java.util.List;

/**
 * Created by Kiran Kolli on 05-12-2016.
 */
public class Directory {

    private final String directoryName;
    private final String sizeDescriptor;
    private final SimpleLongProperty size = new SimpleLongProperty(-1L);
    private final String absolutePath;
    private final List<Directory> childDirectories;
    private final List<File> files;

    public Directory(String directoryName, long sizeVal, String absolutePath, List<Directory> childDirectories, List<File> files) {
        this.directoryName = directoryName;
        this.sizeDescriptor = SizeDescriptionGenerator.getSizeDescription(sizeVal);
        this.childDirectories = childDirectories;
        this.size.set(sizeVal);
        this.files = files;
        ChangeListener<Number> sizeListener = (observable, oldValue, newValue) -> {
            long delta = 0L;
            if (oldValue.longValue() != -1L) {
                delta = newValue.longValue() - oldValue.longValue();
            } else {
                delta = newValue.longValue();
            }
            size.setValue(size.get() + delta);
        };

        files.forEach(file -> file.addSizeListener(sizeListener));

        childDirectories.forEach(directory -> directory.addSizeListener(sizeListener));

        this.absolutePath = absolutePath;
    }

    void addSizeListener(ChangeListener<Number> changeListener) {
        this.size.addListener(changeListener);
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public String getSizeDescriptor() {
        return sizeDescriptor;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }
}
