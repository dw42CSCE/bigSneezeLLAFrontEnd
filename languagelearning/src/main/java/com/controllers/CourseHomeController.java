package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.Exercise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CourseHomeController {
    
    @FXML
    private ScrollPane ch_missedWordsScroll;
    
    @FXML
    private ScrollPane ch_missedWordsScroll1;
    
    @FXML
    private ProgressBar ch_progressBar;
    
    @FXML
    private Text ch_progressText;
    
    @FXML
    private Label lbl_language;


    private CourseManagerFacade cmf;

    @FXML
    public void initialize(){
        cmf = cmf.getInstance();
        cmf.setLesson(0);
    }

    @FXML
    private void startLesson() throws IOException{
        cmf.setLesson(cmf.getUser().getCourseProgress(cmf.getCourse()));
        App.setRoot("lesson");
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
