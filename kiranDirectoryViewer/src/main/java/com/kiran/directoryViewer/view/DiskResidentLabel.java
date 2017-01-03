package com.kiran.directoryViewer.view;

import com.kiran.directoryViewer.model.DiskResident;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Created by Kiran Kolli on 22-12-2016.
 */
public class DiskResidentLabel extends Label {

    private static final Logger LOGGER = LogManager.getLogger(DiskResidentLabelBuilder.class);
    private static Image FOLDER_IMAGE;
    private static Image FILE_IMAGE;
    private static Image FILESTORE_IMAGE;

    static {
        try {
            //noinspection ConstantConditions
            FOLDER_IMAGE = new Image(DiskResidentLabelBuilder.class.getClassLoader().getResource("ic_folder_black_36dp.png").openStream());
            //noinspection ConstantConditions
            FILE_IMAGE = new Image(DiskResidentLabelBuilder.class.getClassLoader().getResource("ic_file_black_36dp.png").openStream());
            //noinspection ConstantConditions
            FILESTORE_IMAGE = new Image(DiskResidentLabelBuilder.class.getClassLoader().getResource("ic_storage_black_36dp.png").openStream());
        } catch (Exception e) {
            LOGGER.error("Error while loading images", e);
            throw new RuntimeException(e);
        }
    }

    private final DiskResident.Type type;
    private final long size;
    private final Path absolutePath;
    private final FileTime createdTime;
    private final FileTime modifiedTime;

    DiskResidentLabel(DiskResident.Type type, long size, Path absolutePath, FileTime createdTime, FileTime modifiedTime) {
        this.type = type;
        this.size = size;
        this.absolutePath = absolutePath;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    DiskResident.Type getType() {
        return this.type;
    }

    Path getAbsolutePath() {
        return absolutePath;
    }

    FileTime getCreatedTime() {
        return createdTime;
    }

    public FileTime getModifiedTime() {
        return modifiedTime;
    }

    private void initialize() {
        setText(absolutePath.getFileName().toString());
        setImage();
    }

    private void setImage() {
        switch (type) {
            case FILE:
                setGraphic(new ImageView(FILE_IMAGE));
                break;
            case DIRECTORY:
                setGraphic(new ImageView(FOLDER_IMAGE));
                break;
            case FILESTORE:
                setGraphic(new ImageView(FILESTORE_IMAGE));
                break;
        }
    }

    private void addToTooltip(String text) {
        String currentToolTip;
        if (getTooltip() != null && !StringUtils.isBlank(getTooltip().getText())) {
            currentToolTip = getTooltip().getText();
            currentToolTip = currentToolTip.concat(System.lineSeparator() + text);
        } else {
            currentToolTip = text;
        }

        setTooltip(new Tooltip(currentToolTip));
    }

    String getSizeDescription() {
        return getSizeDescription(size, 0);
    }

    private String getSizeDescription(double size, int depth) {

        if (size < 0) {
            return "UNKNOWN SIZE";
        }

        if ((size / 1000) >= 1) {
            return getSizeDescription(size / 1000D, depth + 1);
        }

        switch (depth) {
            case 0:
                return String.format("%3.2g Bytes", size / 1000D);
            case 1:
                return String.format("%3.2g KB", size / 1000D);
            case 2:
                return String.format("%3.2g MB", size / 1000D);
            case 3:
                return String.format("%3.2g GB", size / 1000D);
            case 4:
                return String.format("%3.2g TB", size / 1000D);
            default:
                return "Size calculation error! Cannot compute for depth - " + depth;
        }
    }
}
