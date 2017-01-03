package com.kiran.directoryviewer.view;

import com.kiran.directoryviewer.fetcher.AlwaysScanDirectoryDetailFetcher;
import com.kiran.directoryviewer.model.Directory;
import com.kiran.directoryviewer.model.DirectoryContents;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Kiran Kolli on 04-10-2016.
 */
public class DirectoryViewerApp extends Application {

    //private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryViewerApp.class.getName());
    private static final Logger LOGGER = LogManager.getLogger(DirectoryViewerApp.class);
    private static final URL VIEW_DESCRIPTOR = DirectoryViewerApp.class.getClassLoader().getResource("directoryViewer.fxml");


    @Override
    public void start(Stage primaryStage) throws Exception {
        LOGGER.info("Started DirectoryViewerApp");
        primaryStage.setTitle("DirectoryViewer");
        AlwaysScanDirectoryDetailFetcher directoryDetailFetcher = new AlwaysScanDirectoryDetailFetcher();
        DirectoryContents directoryContents = directoryDetailFetcher.getContents(new Directory("C:", -1L, "C:\\", new ArrayList<>(), new ArrayList<>()));
        DirectoryViewerControl root = new DirectoryViewerControl(ViewType.LARGE_ICONS, directoryContents);
        Scene scene = new Scene(root, 500D, 500D);
        primaryStage.setScene(scene);
        primaryStage.show();
        LOGGER.info("Exiting DirectoryViewerApp");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
