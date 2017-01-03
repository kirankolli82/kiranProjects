package com.kiran.directoryviewer.fetcher.dao;

import com.kiran.directoryviewer.model.Directory;
import com.kiran.directoryviewer.model.DirectoryContents;

/**
 * Created by Kiran Kolli on 05-12-2016.
 */
public interface DirectoryDetailsDAO {

    void saveDirectoryContents(DirectoryContents directoryContents);

    DirectoryContents fetchContents(Directory directory);
}
