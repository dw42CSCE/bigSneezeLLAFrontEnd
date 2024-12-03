package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.Exercise;

import javafx.fxml.FXML;

public class CourseHomeController {
    private CourseManagerFacade cmf;

    @FXML
    public void initialize(){
        cmf = cmf.getInstance();
        cmf.setLesson(0);
    }

    @FXML
    private void startLesson() throws IOException{
        cmf.setLesson(cmf.getUser().getCourseProgress(cmf.getCourse()));
        App.setRoot("lesson");
    }

    @FXML
    private void switchToDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
