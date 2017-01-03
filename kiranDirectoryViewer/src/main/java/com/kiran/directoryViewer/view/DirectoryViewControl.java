package com.kiran.directoryViewer.view;

import com.kiran.directoryViewer.model.DiskResident;
import com.kiran.directoryViewer.tasks.TaskFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kiran Kolli on 22-12-2016.
 */
public class DirectoryViewControl extends Control {
    private final Map<DiskResident.Type, TaskFactory<?>> actionMap = new HashMap<>();

    {
        //TODO: INITIALIZE actionMap
    }

    private final ObservableList<DiskResidentLabel> labelsToDisplay = FXCollections.observableArrayList();

    void replaceCurrentLabels(List<DiskResidentLabel> newList) {
        labelsToDisplay.clear();
        labelsToDisplay.addAll(newList);
    }
}
