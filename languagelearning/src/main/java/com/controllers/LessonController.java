package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.Exercise;
import com.narration.*;
import com.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LessonController {

    private CourseManagerFacade cmf;

    @FXML
    public void initialize(){
        cmf = cmf.getInstance();
    }

    @FXML
    private void switchToDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

    @FXML
    public void nextQuestion() throws IOException{
        cmf.generateExercise(); 
        Exercise nextExercise = cmf.getExercise();
        if(nextExercise.getType().equals("translation")){
            System.out.println("Switching to translation");
            App.setRoot("translation");
        } else if(nextExercise.getType().equals("fillin")){
            System.out.println("Switching to fillin");
            App.setRoot("fillin");
        }  else if(nextExercise.getType().equals("audio")){
            System.out.println("Switching to audio");
            App.setRoot("audio");
        }
    }
    
}