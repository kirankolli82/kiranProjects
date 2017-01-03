package com.kiran.directoryViewer.model.fetcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
class DirectoryDetailCache {
    private static final Logger LOGGER = LogManager.getLogger(DirectoryDetailCache.class);
    private static AtomicReference<RootNode> ROOT_NODE = new AtomicReference<>(null);

    static DirectoryStructureNode getRootNode() {
        if (ROOT_NODE.get() == null) {
            ROOT_NODE.compareAndSet(null, new RootNodeCreationStrategy().createNode());
        }

        return ROOT_NODE.get();
    }

    static DirectoryStructureNode gotoNode(Path absolutePath) throws NoSuchFileException, AccessDeniedException {
        if (!Files.exists(absolutePath)) {
            throw new NoSuchFileException(absolutePath + " cannot be located on disk");
        }

        if (!Files.isReadable(absolutePath)) {
            throw new AccessDeniedException(absolutePath + " not accessible to current user");
        }

        DirectoryStructureNode closestParent = findClosestParent(getRootNode(), absolutePath, -1);

        return createOrGet(closestParent.getParent(), absolutePath);
    }

    private static DirectoryStructureNode createOrGet(final DirectoryStructureNode grandfather, Path nodeToFind) throws AccessDeniedException, NoSuchFileException {
        if (!Files.exists(nodeToFind)) {
            throw new NoSuchFileException(nodeToFind + " cannot be located on disk");
        }

        if (!Files.isReadable(nodeToFind)) {
            throw new AccessDeniedException(nodeToFind + " not accessible to current user");
        }
        Path pathOfParent = grandfather.getPath().resolve(grandfather.getPath().relativize(nodeToFind).getName(0));
        DirectoryStructureNode parent = grandfather.findChild(pathOfParent);
        DirectoryStructureNode nodeToGoTo = parent.findChild(nodeToFind);

        if (nodeToGoTo != null) {
            return nodeToGoTo;
        }

        if (parent instanceof FileNode) {
            LOGGER.fatal(nodeToFind + " seems to contain a file within a file");
            throw new RuntimeException(nodeToFind + " seems to contain a file within a file");
        }

        if (parent instanceof DummyNode) {
            DirectoryStructureNode recreatedParent = new OneLevelDepthNodeCreationStrategy(parent).createNode();
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (grandfather) {
                grandfather.compareAndReplaceChild(grandfather, recreatedParent);
            }

            return createOrGet(recreatedParent, nodeToFind);
        } else {
            throw new RuntimeException("Parent node while searching not expected to be of type:" + grandfather.getClass());
        }
    }

    private static DirectoryStructureNode findClosestParent(DirectoryStructureNode currentParent, Path path, int index) {
        DirectoryStructureNode matchingNode;
        if (index < 0) {
            matchingNode = currentParent.findChild(path.getRoot());
        } else {
            matchingNode = currentParent.findChild(path.subpath(0, index + 1));
            if (matchingNode == null) {
                return currentParent;
            }
        }

        if (Objects.equals(path, matchingNode.getPath())) {
            return currentParent;
        } else {
            return findClosestParent(matchingNode, path, ++index);
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\");
        System.out.println(path);
        Path path2 = Paths.get("C:\\dir1\\dir2");
        System.out.println(path2.toFile().exists());
        Path path3 = path.relativize(path2);
        Iterator<Path> pathIterator = path3.iterator();
        int index = 0;
        while (pathIterator.hasNext()) {
            pathIterator.next();
            System.out.println(path2.subpath(0, ++index));
        }

    }

}
