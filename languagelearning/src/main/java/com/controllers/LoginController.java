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

public class LoginController {

    private CourseManagerFacade cmf;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("signup");
    }

    @FXML
    private void login() {
        cmf = cmf.getInstance();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(cmf.login(username, password) != null){
            cmf.login(username, password);
            System.out.println("Successful Login!");
            // App.setRoot("dashboard");
        } else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Incorrect Username or Password");
            alert.showAndWait();
        }

        

    }
}
