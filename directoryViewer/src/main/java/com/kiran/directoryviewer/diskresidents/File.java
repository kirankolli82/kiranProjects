package com.kiran.directoryviewer.diskresidents;

import com.kiran.directoryviewer.DiskResident;

import java.nio.file.attribute.FileTime;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public class File extends AbstractDiskResident {

    public File(String fileName, String absolutePath, long size, FileTime createdTime, FileTime modifiedTime) {
        super(fileName, absolutePath, size, createdTime, modifiedTime);
    }

    @Override
    public Iterator<DiskResident> getChildren() {
        //noinspection unchecked
        return Collections.EMPTY_LIST.iterator();
    }

    @Override
    public void exportTo(DiskResidentExporter exporter) {
        super.exportTo(exporter);
    }

    @Override
    public DiskResidentType getType() {
        return DiskResidentType.FILE;
    }
}
