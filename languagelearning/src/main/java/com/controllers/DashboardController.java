

package com.controllers;

import java.io.IOException;

import com.language.App;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    @FXML
    void goToCourseHome(MouseEvent event) throws IOException {
        App.setRoot("coursehome");
    }

    @FXML
    void goToSettings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

}
