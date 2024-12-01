package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.language.App;
import com.model.CourseManagerFacade;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private CourseManagerFacade cmf;

    @FXML
    private CheckBox emailCheckBox;

    @FXML
    private CheckBox soundCheckBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmf = CourseManagerFacade.getInstance(); // Initialize the CourseManagerFacade instance
        
        // Get user settings
        boolean emailNotifications = cmf.getUser().getSettings().getEmailNotifications();
        boolean darkMode = cmf.getUser().getSettings().getDarkMode();
        
        // Set the checkboxes based on the settings
        emailCheckBox.setSelected(emailNotifications);
        soundCheckBox.setSelected(darkMode);
    }

    @FXML
    void switchToUpdateAccount() throws IOException {
        App.setRoot("updateuser");
    }

    @FXML
    public void switchToDashboard(MouseEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void switchToLogin() throws IOException {
        cmf = cmf.getInstance();
        cmf.logOut();
        App.setRoot("login");
    }

    @FXML
    void toggleEmail(ActionEvent event) {
        cmf = cmf.getInstance();
        
        // Update user settings based on checkbox state
        boolean email = emailCheckBox.isSelected();
        boolean sound = cmf.getUser().getSettings().getDarkMode();
        
        cmf.setUserSettings(sound, email);

        System.out.println("Email notifications updated to: " + email);
        cmf.update();
    }

    @FXML
    void toggleSound(ActionEvent event) {
        cmf = cmf.getInstance();
        
        // Update user settings based on checkbox state
        boolean sound = soundCheckBox.isSelected();
        boolean email = cmf.getUser().getSettings().getEmailNotifications();
        
        cmf.setUserSettings(sound, email);

        System.out.println("Sound settings updated to: " + sound);
        cmf.update();
    }
}
