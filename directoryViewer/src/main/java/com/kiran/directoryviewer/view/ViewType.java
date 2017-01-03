package com.kiran.directoryviewer.view;

/**
 * Created by Kiran Kolli on 06-12-2016.
 */
enum ViewType {
    LARGE_ICONS("Large Icons"), DETAILS_VIEW("Details View");

    private final String uiText;

    ViewType(String uiText) {
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }
}
