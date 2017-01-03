package com.kiran.directoryViewer.model.fetcher;

import java.nio.file.Path;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
public interface DirectoryDetailFetcher {

    void updateCurrentDirectoryResidents(Path directoryPath);
}
