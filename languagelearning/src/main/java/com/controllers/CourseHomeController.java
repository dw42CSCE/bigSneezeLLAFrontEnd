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
        cmf.generateExercise(); 
        Exercise nextExercise = cmf.getExercise();
        if(nextExercise.getType().equals("translation")){
            System.out.println("Switching to translation");
            App.setRoot("translation");
        } else if(nextExercise.getType().equals("audio")){
            System.out.println("Switching to audio");
            App.setRoot("audio");
        } //else if(nextExercise.getType().equals("matching")){
        //     System.out.println("Switching to matching");
        //     App.setRoot("matching");
        // } else if(nextExercise.getType().equals("fillin")){
        //     System.out.println("Switching to fillin");
        //     App.setRoot("fillin");
        // }
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
