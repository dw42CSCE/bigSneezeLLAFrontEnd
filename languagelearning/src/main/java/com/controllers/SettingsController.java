package com.controllers;

import java.io.IO;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import com.language.App;
import com.model.CourseManagerFacade;
import com.narration.*;
import com.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    private CourseManagerFacade cmf;

    @FXML
    private CheckBox emailCheckBox;

    @FXML
    private CheckBox soundCheckBox;
    
    @FXML
    void switchToUpdateAccount() throws IOException{
        App.setRoot("updateuser");
    }

    @FXML
    public void switchToDashboard(MouseEvent event) throws IOException{
        App.setRoot("dashboard");
    }

    @FXML
    void switchToLogin() throws IOException{
        cmf = cmf.getInstance();
        cmf.logOut();
        App.setRoot("login");
    }

@FXML
void toggleEmail(ActionEvent event) {
    cmf = cmf.getInstance();
    
    System.out.println(cmf.getUserSettings().toString());
    
    // Get the current state of the email checkbox
    boolean email = emailCheckBox.isSelected();
    // Retain the current state of the sound setting
    boolean sound = cmf.getUser().getSettings().getDarkMode();

    // Update user settings
    cmf.setUserSettings(sound, email);

    System.out.println("Email notifications updated to: " + cmf.getUserSettings().toString());

    cmf.addUserCourse(cmf.getAllCourses().getCourse(0));

    cmf.update();
}

@FXML
void toggleSound(ActionEvent event) {
    cmf = cmf.getInstance();
    
    // Get the current state of the sound checkbox
    boolean sound = soundCheckBox.isSelected();
    // Retain the current state of the email setting
    boolean email = cmf.getUser().getSettings().getEmailNotifications();

    // Update user settings
    cmf.setUserSettings(sound, email);

    System.out.println("Sound settings updated to: " + sound);
}


}


