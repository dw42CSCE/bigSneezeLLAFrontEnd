package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    private CourseManagerFacade cmf;

    @FXML
    void goToCourseHome(MouseEvent event) throws IOException {

        
        cmf = cmf.getInstance();
        cmf.addUserCourse(cmf.getCourse());
        if(cmf.getCourse() != null){
            cmf.setLesson(cmf.getUser().getCourseProgress(cmf.getCourse()));
        } else{
            cmf.setLesson(0);
        }
        
        App.setRoot("coursehome");
    }

    @FXML
    void goToSettings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

}
