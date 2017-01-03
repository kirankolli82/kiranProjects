package com.kiran.directoryViewer.model.fetcher;

import java.nio.file.Path;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public interface ViewUpdater {
    void updateViewForPath(Path absolutePath);
}
