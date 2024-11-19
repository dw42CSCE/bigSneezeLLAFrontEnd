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

public class SignUpController {

    private CourseManagerFacade cmf;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void signup() {
        cmf = cmf.getInstance();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String emailAddress = emailAddressField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(cmf.signUp(username, emailAddress, password) != null){
            cmf.signUp(username, emailAddress, password);
            System.out.println("Successful Signup!");
            // App.setRoot("dashboard");
        } else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Invalid Information");
            alert.showAndWait();
        }

        

    }

}