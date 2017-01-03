package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.DiskResident.Type;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;

/**
 * Created by Kiran Kolli on 01-01-2017.
 */
public class RootNodeCreationStrategy implements NodeCreationStrategy {

    private static final Logger LOGGER = LogManager.getLogger(RootNodeCreationStrategy.class);

    @Override
    public RootNode createNode() {
        RootNode rootNode = new RootNode(null, null, null, null);
        for (java.io.File root : java.io.File.listRoots()) {
            Path absolutePath = root.toPath();
            try {
                //long rootSize = Files.getFileStore(absolutePath).getTotalSpace();
                rootNode.addChild(getDummyNode(absolutePath, Type.FILESTORE, rootNode));
            } catch (Exception e) {
                LOGGER.warn(absolutePath + "  is not accessible", e.getCause());
            }
        }
        return rootNode;
    }
}
