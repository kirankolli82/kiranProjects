package com.kiran.directoryViewer.model.fetcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
class OneLevelDepthNodeCreationStrategy implements NodeCreationStrategy {
    private static final Logger LOGGER = LogManager.getLogger(OneLevelDepthNodeCreationStrategy.class);

    private final Path absolutePath;
    private final DirectoryStructureNode parent;

    OneLevelDepthNodeCreationStrategy(DirectoryStructureNode nodeToRecreate) {
        this(nodeToRecreate.getPath(), nodeToRecreate.getParent());
    }

    @SuppressWarnings("WeakerAccess")
    OneLevelDepthNodeCreationStrategy(Path absolutePath, DirectoryStructureNode parent) {
        this.absolutePath = absolutePath;
        this.parent = parent;
    }

    @Override
    public DirectoryStructureNode createNode() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(absolutePath)) {
            BasicFileAttributes fileAttributes = getBasicFileAttributes(absolutePath);
            DirectoryNode directoryNode = new DirectoryNode(absolutePath, fileAttributes.creationTime(),
                    fileAttributes.lastModifiedTime(), this.parent);
            for (Path child : stream) {
                if (Files.isDirectory(child)) {
                    directoryNode.addChild(getDummyNode(child, directoryNode));
                } else {
                    fileAttributes = getBasicFileAttributes(child);
                    directoryNode.addChild(new FileNode(directoryNode, this.absolutePath,
                            fileAttributes.creationTime(), fileAttributes.lastModifiedTime(), fileAttributes.size()));
                }
            }

            return directoryNode;
        } catch (IOException e) {
            LOGGER.error("Error while creating node for:" + absolutePath, e);
            throw new RuntimeException(e);
        }
    }

}
