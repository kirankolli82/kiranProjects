package com.kiran.directoryviewer.view;

import com.kiran.directoryviewer.model.DirectoryContents;
import javafx.scene.control.*;

/**
 * Created by Kiran Kolli on 06-12-2016.
 */
public class DirectoryViewerControl extends Control {
    private final ViewType currentViewType;
    private final MenuBar menuBar;
    private final DirectoryContents directoryContents;


    public DirectoryViewerControl(ViewType currentViewType, DirectoryContents directoryContents) {
        this.currentViewType = currentViewType;
        this.directoryContents = directoryContents;
        this.menuBar = new MenuBar();
        Menu menu = new Menu("Display Options");
        this.menuBar.getMenus().add(menu);
        for (ViewType viewType : ViewType.values()) {
            MenuItem menuItem = new MenuItem(viewType.getUiText());
            menuItem.setOnAction(event -> this.setSkin(getSkinFor(viewType)));
        }
    }

    @Override
    protected Skin<DirectoryViewerControl> createDefaultSkin() {
        return new LargeIconsViewSkin(this);
    }

    private Skin<DirectoryViewerControl> getSkinFor(ViewType viewType) {
        switch (viewType) {
            case DETAILS_VIEW:
                return new DetailsViewSkin(this);
            case LARGE_ICONS:
                return new LargeIconsViewSkin(this);
            default:
                return new LargeIconsViewSkin(this);
        }
    }

    MenuBar getMenuBar() {
        return menuBar;
    }

    DirectoryContents getDirectoryContents() {
        return directoryContents;
    }
}


