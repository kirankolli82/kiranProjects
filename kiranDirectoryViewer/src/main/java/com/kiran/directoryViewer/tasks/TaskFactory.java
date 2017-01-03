package com.kiran.directoryViewer.tasks;

import javafx.concurrent.Task;

import java.nio.file.Path;

/**
 * Created by Kiran Kolli on 22-12-2016.
 */
public interface TaskFactory<ReturnTypeT> {
    Task<ReturnTypeT> create(Path path);
}
