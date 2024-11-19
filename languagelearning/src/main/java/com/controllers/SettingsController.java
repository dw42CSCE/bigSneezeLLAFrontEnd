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
import javafx.event.ActionEvent;

public class SettingsController {

    private CourseManagerFacade cmf;

    @FXML
    void switchToUpdateAccount(ActionEvent event) {
        cmf = cmf.getInstance();
        System.out.println(cmf.getUser().getEmailAddress());
    }

    @FXML
    void toggleEmail(ActionEvent event) {

    }

    @FXML
    void toggleSound(ActionEvent event) {

    }

}


