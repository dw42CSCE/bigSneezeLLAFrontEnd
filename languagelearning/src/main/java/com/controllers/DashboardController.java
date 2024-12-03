package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DashboardController {

    private CourseManagerFacade cmf;

    public DashboardController() {
        cmf = cmf.getInstance();
    }

    @FXML
    void goToCourseHome(MouseEvent event) throws IOException {

        cmf.addUserCourse(cmf.getCourse());
        if(cmf.getCourse() != null){
            cmf.setLesson(cmf.getUser().getCourseProgress(cmf.getCourse()));
        } else{
            cmf.setLesson(0);
            cmf.addUserCourse(cmf.getCourse());
        }
        
        App.setRoot("coursehome");
    }

    @FXML
    void goToSettings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private Text PPText;

    @FXML
    private Text firstnameText;

    @FXML
    private Text usernameText;

    @FXML
    public void initialize(){
        User currentUser = cmf.getUser();
        firstnameText.setText(currentUser.getFirstName()+ "!");
        usernameText.setText(currentUser.getUsername());
        int pPoints = currentUser.getProfPoints();
        String points = Integer.toString(pPoints);
        PPText.setText(points);
    } 
    



}
