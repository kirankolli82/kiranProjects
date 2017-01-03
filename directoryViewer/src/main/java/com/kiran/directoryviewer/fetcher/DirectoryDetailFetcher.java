package com.kiran.directoryviewer.fetcher;

import com.kiran.directoryviewer.model.Directory;
import com.kiran.directoryviewer.model.DirectoryContents;

/**
 * Created by Kiran Kolli on 05-12-2016.
 */
public interface DirectoryDetailFetcher {
    DirectoryContents getContents(Directory directory);
}
