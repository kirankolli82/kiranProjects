package com.kiran.directoryviewer.diskresidents.exporters;

import com.kiran.directoryviewer.DiskResident;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.attribute.FileTime;
import java.util.Collection;

/**
 * Created by Kiran Kolli on 18-12-2016.
 */
public class LabelExporter implements DiskResident.DiskResidentExporter {

    private static Image FOLDER_IMAGE;
    private static Image FILE_IMAGE;
    private static final Logger LOGGER = LogManager.getLogger(LabelExporter.class);

    static {
        try {
            //noinspection ConstantConditions
            FOLDER_IMAGE = new Image(LabelExporter.class.getClassLoader().getResource("ic_folder_black_36dp.png").openStream());
            //noinspection ConstantConditions
            FILE_IMAGE = new Image(LabelExporter.class.getClassLoader().getResource("ic_file_black_36dp.png").openStream());
        } catch (Exception e) {
            LOGGER.error("Error while loading images", e);
            throw new RuntimeException(e);
        }
    }

    private final Label label = new Label();

    @Override
    public void setName(String name) {
        label.setText(name);
    }

    @Override
    public void setSize(String size) {
        addToTooltip("Size:" + size);
    }

    @Override
    public void setAbsolutePath(String absolutePath) {
        //NOT TO BE DISPLAYED
    }

    @Override
    public void setCreatedTime(FileTime createdTime) {
        addToTooltip("Created:" + createdTime);
    }

    @Override
    public void setModifiedTime(FileTime modifiedTime) {
        addToTooltip("Modified:" + modifiedTime);
    }

    @Override
    public void addChildren(Collection<DiskResident> children) {
        //NOT TO BE DISPLAYED
    }

    @Override
    public void addChild(DiskResident child) {
        //NOT TO BE DISPLAYED
    }

    @Override
    public void setType(DiskResident.DiskResidentType diskResidentType) {
        switch (diskResidentType) {
            case FILE:
                label.setGraphic(new ImageView(FILE_IMAGE));
                break;
            case DIRECTORY:
                label.setGraphic(new ImageView(FOLDER_IMAGE));
                break;
        }
    }

    private void addToTooltip(String text) {
        String currentToolTip;
        if (label.getTooltip() != null && !StringUtils.isBlank(label.getTooltip().getText())) {
            currentToolTip = label.getTooltip().getText();
            currentToolTip = currentToolTip.concat(System.lineSeparator() + text);
        } else {
            currentToolTip = text;
        }

        label.setTooltip(new Tooltip(currentToolTip));
    }
}
