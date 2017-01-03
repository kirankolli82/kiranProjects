package com.kiran.directoryViewer.model;

import java.util.List;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public interface CurrentViewDiskResidentAware {
    interface CurrentDirectoryResidentListener {
        void onChange(List<DiskResident> newResidents);
    }

    Object registerListener(CurrentDirectoryResidentListener listener);

    void unregister(Object handle);
}
