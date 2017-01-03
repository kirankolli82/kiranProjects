package com.kiran.directoryViewer.model.fetcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Kiran Kolli on 23-12-2016.
 */
class OneLevelDepthDetailFetcher {
    private static final Logger LOGGER = LogManager.getLogger(OneLevelDepthDetailFetcher.class);

    /*@Override
    public void updateCurrentDirectoryResidents(Path directoryPath) {
        Directory currentDirectory = DirectoryDetailCache.getCurrentDirectory();
        boolean exploreDown = goingOneLevelDown(directoryPath);
        boolean exploreUp = goingOneLevelUp(directoryPath);

        if (!(exploreDown || exploreUp)) {
            LOGGER.fatal("Could not find " + directoryPath + " in children/parent of " + currentDirectory.getAbsolutePath());
            throw new RuntimeException("FileSystem seems out of sync, please restart application.");
        }

        if(exploreDown){
            Directory childDir = currentDirectory.getChildDir(directoryPath);
            if(childDir.isScanningComplete()){
                DirectoryDetailCache.setCurrentDirectory(childDir);
            } else {
                exploreNewChild(childDir);
            }
        } else if(exploreUp){
            DirectoryDetailCache.setCurrentDirectory(currentDirectory.getParent());
        }
    }

    private void exploreNewChild(Directory childDir){
        File directory = childDir.getAbsolutePath().toFile();
        try {
            for (File child : directory.listFiles()) {
                Path path = child.toPath();
                BasicFileAttributes fileAttributes =
                        Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
                if (child.isDirectory()) {
                    Directory currDir = new Directory(child.getName(), child.toPath(), fileAttributes.creationTime(), fileAttributes.lastModifiedTime());
                    childDir.addChild(currDir);
                } else {
                    com.kiran.directoryViewer.model.fetcher.File file =
                            new com.kiran.directoryViewer.model.fetcher.File(child.getName(),
                                    path, fileAttributes.creationTime(), fileAttributes.lastModifiedTime(),
                                    fileAttributes.size(), childDir);
                    childDir.addChild(file);
                }
            }
            childDir.setScanningComplete(true);
            DirectoryDetailCache.setCurrentDirectory(childDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean goingOneLevelDown(Path directoryPath) {
        Directory currentDirectory = DirectoryDetailCache.getCurrentDirectory();
        return currentDirectory.getChildDir(directoryPath) != null;

        //LOGGER.fatal("Could not find " + directoryPath + " in children of " + currentDirectory.getAbsolutePath());
        //throw new RuntimeException("FileSystem seems out of sync, please restart application.");
    }

    public boolean goingOneLevelUp(Path directoryPath) {
        Directory currentDirectory = DirectoryDetailCache.getCurrentDirectory();
        return Objects.equals(directoryPath, currentDirectory.getParent().getAbsolutePath());
    }
*/
}
