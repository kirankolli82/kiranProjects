package com.kiran.directoryviewer.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;

import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 05-12-2016.
 */
public class File {

    private final String fileName;
    private final String sizeDescriptor;
    private final SimpleLongProperty size = new SimpleLongProperty(-1L);
    private final FileTime createdDate;
    private final FileTime modifiedDate;

    public File(String fileName, long size, FileTime createdDate, FileTime modifiedDate) {
        this.fileName = fileName;
        this.sizeDescriptor = SizeDescriptionGenerator.getSizeDescription(size);
        this.size.setValue(size);

        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSizeDescriptor() {
        return sizeDescriptor;
    }

    public FileTime getCreatedDate() {
        return createdDate;
    }

    public FileTime getModifiedDate() {
        return modifiedDate;
    }

    void addSizeListener(ChangeListener<Number> changeListener) {
        this.size.addListener(changeListener);
    }
}
