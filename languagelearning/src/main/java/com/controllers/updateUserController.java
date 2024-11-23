package com.controllers;

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

public class UpdateUserController {
    
    private CourseManagerFacade cmf;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField passwordField;

    @FXML
    private void switchToDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private void updateUser() {
        cmf = cmf.getInstance();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String emailAddress = emailAddressField.getText();
        String password = passwordField.getText();

    }

}
