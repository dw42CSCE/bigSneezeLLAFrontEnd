package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;

import javafx.fxml.FXML;

public class CourseHomeController {
    private CourseManagerFacade cmf;

    @FXML
    private void switchToDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
