package com.kiran.directoryViewer.model.fetcher;

import java.nio.file.Path;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public interface NodeNavigationCapable {
    DirectoryStructureNode gotoRootNode();

    DirectoryStructureNode gotoNode(Path absolutePath);

    void forceCreateNode(Path absolutePath);

    void forceCreateNodeFullDepth(Path absolutePath);
}
