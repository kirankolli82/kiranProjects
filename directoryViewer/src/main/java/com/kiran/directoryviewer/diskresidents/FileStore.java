package com.kiran.directoryviewer.diskresidents;

import com.kiran.directoryviewer.DiskResident;

import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public class FileStore extends AbstractDiskResident {

    private final List<FileStore> childDirectories = new ArrayList<>();
    private final List<File> files = new ArrayList<>();

    public FileStore(String fileName, String absolutePath, long size,
                     FileTime createdTime, FileTime modifiedTime,
                     List<FileStore> childDirectories, List<File> files) {
        super(fileName, absolutePath, size, createdTime, modifiedTime);
        this.childDirectories.addAll(childDirectories);
        this.files.addAll(files);
    }

    @Override
    public Iterator<DiskResident> getChildren() {
        return new Iterator<DiskResident>() {
            int index = 0;
            Iterator<FileStore> directoryIterator = childDirectories.iterator();
            Iterator<File> fileIterator = files.iterator();

            @Override
            public boolean hasNext() {
                if (index == 0) {
                    return childDirectories.isEmpty() && files.isEmpty();
                }

                return index > childDirectories.size() + files.size();
            }

            @Override
            public DiskResident next() {
                if (directoryIterator.hasNext()) {
                    index++;
                    return directoryIterator.next();
                }

                if (fileIterator.hasNext()) {
                    index++;
                    return fileIterator.next();
                }

                throw new RuntimeException("No more DiskResidents available!");
            }
        };
    }

    @Override
    public void exportTo(DiskResidentExporter exporter) {
        super.exportTo(exporter);
        childDirectories.forEach(directory -> exporter.addChild(directory));
        files.forEach(file -> exporter.addChild(file));
    }

    @Override
    public DiskResidentType getType() {
        return DiskResidentType.FILESTORE;
    }
}
