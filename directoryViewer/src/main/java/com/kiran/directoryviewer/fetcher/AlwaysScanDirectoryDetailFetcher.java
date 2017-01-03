package com.kiran.directoryviewer.fetcher;

import com.kiran.directoryviewer.model.Directory;
import com.kiran.directoryviewer.model.DirectoryContents;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran Kolli on 06-12-2016.
 */
public class AlwaysScanDirectoryDetailFetcher implements DirectoryDetailFetcher {

    private static final Logger LOGGER = LogManager.getLogger(AlwaysScanDirectoryDetailFetcher.class);

    @Override
    public DirectoryContents getContents(Directory directory) {
        File file = new File(directory.getAbsolutePath());

        if (!file.exists()) {
            return new DirectoryContents(new ArrayList<>(), new ArrayList<>(), "");
        }
        try {
            return constructDirectoryContents(file);
        } catch (IOException ioe) {
            LOGGER.error("Error constructing the Directory Contents", ioe);
            String error = "Error fetching details:" + ioe.getMessage();
            return new DirectoryContents(new ArrayList<>(), new ArrayList<>(), error);
        }
    }

    private DirectoryContents constructDirectoryContents(File file) throws IOException {
        List<Directory> childDirs = new ArrayList<>();
        List<com.kiran.directoryviewer.model.File> childFiles = new ArrayList<>();

        for (File child : file.listFiles()) {
            if (child.isDirectory()) {
                childDirs.add(constructDirectory(child));
            } else {
                childFiles.add(constructFile(child));
            }
        }

        return new DirectoryContents(childDirs, childFiles, null);
    }

    private Directory constructDirectory(File file) {
        return new Directory(file.getName(), -1L,
                file.getAbsolutePath(), new ArrayList<>(), new ArrayList<>());
    }

    private com.kiran.directoryviewer.model.File constructFile(File file) throws IOException {
        Path path = file.toPath();
        BasicFileAttributes fileAttributes =
                Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
        return new com.kiran.directoryviewer.model.File(file.getName(),
                fileAttributes.size(), fileAttributes.creationTime(), fileAttributes.lastModifiedTime());
    }
}
