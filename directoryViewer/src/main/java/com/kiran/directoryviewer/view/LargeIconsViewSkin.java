package com.kiran.directoryviewer.view;

import com.kiran.directoryviewer.model.Directory;
import com.kiran.directoryviewer.model.DirectoryContents;
import com.kiran.directoryviewer.model.File;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Kiran Kolli on 06-12-2016.
 */
class LargeIconsViewSkin extends SkinBase<DirectoryViewerControl> {

    private static final Logger LOGGER = LogManager.getLogger(LargeIconsViewSkin.class);
    private static final double LABEL_LENGTH = 160D;
    private static final int NUMBER_OF_ITEMS_PER_ROW = 5;
    private static Image FOLDER_IMAGE;
    private static Image FILE_IMAGE;

    static {
        try {
            //noinspection ConstantConditions
            FOLDER_IMAGE = new Image(LargeIconsViewSkin.class.getClassLoader().getResource("ic_folder_black_36dp.png").openStream());
            //noinspection ConstantConditions
            FILE_IMAGE = new Image(LargeIconsViewSkin.class.getClassLoader().getResource("ic_file_black_36dp.png").openStream());
        } catch (Exception e) {
            LOGGER.error("Error while loading images", e);
            throw new RuntimeException(e);
        }
    }

    LargeIconsViewSkin(DirectoryViewerControl control) {
        super(control);
        GridPane gridPane = createGridPane();

        DirectoryContents directoryContents = control.getDirectoryContents();
        placeItemsInGrid(gridPane, directoryContents);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        VBox vBox = new VBox(control.getMenuBar(), scrollPane);
        getChildren().add(vBox);
    }

    private void placeItemsInGrid(GridPane gridPane, DirectoryContents directoryContents) {
        int itemsPlaced = 0;

        for (Directory directory : directoryContents.getDirectories()) {
            Label label = constructFolderLabel(directory);
            itemsPlaced++;
            gridPane.add(label, itemsPlaced % NUMBER_OF_ITEMS_PER_ROW, itemsPlaced / NUMBER_OF_ITEMS_PER_ROW);
        }


        for (File file : directoryContents.getFiles()) {
            Label label = constructLabelForFile(file);
            itemsPlaced++;
            gridPane.add(label, itemsPlaced % NUMBER_OF_ITEMS_PER_ROW, itemsPlaced / NUMBER_OF_ITEMS_PER_ROW);
        }
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        //Useful for debugging purposes
        //gridPane.setGridLinesVisible(true);

        for (int colIndex = 0; colIndex < NUMBER_OF_ITEMS_PER_ROW; colIndex++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100D / NUMBER_OF_ITEMS_PER_ROW);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        gridPane.setHgap(10D);
        gridPane.setVgap(10D);
        return gridPane;
    }

    private Label constructLabelForFile(File file) {
        return createLabel(file.getFileName(), FILE_IMAGE);
    }

    private Label constructFolderLabel(Directory directory) {
        return createLabel(directory.getDirectoryName(), FOLDER_IMAGE);
    }

    private Label createLabel(String name, Image image) {
        Label label = new Label(name, new ImageView(image));
        label.setMaxWidth(LABEL_LENGTH);
        label.setPrefWidth(LABEL_LENGTH);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.RIGHT);
        label.setAlignment(Pos.CENTER);
        GridPane.setHalignment(label, HPos.CENTER);
        label.setContentDisplay(ContentDisplay.TOP);
        label.setTooltip(new Tooltip(name));
        return label;
    }
}
