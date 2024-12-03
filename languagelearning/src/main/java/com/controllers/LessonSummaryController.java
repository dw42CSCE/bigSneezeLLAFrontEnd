package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LessonSummaryController {

    @FXML
    private Text lessonTitleText;

    @FXML
    private Text missedwordone;

    @FXML
    private Text missedwordtwo;

    private CourseManagerFacade cmf;

    public LessonSummaryController() {
        cmf = cmf.getInstance();
    }
    @FXML
    void nextQuestion(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void switchToDashboard(MouseEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void switchToSettings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    public void initialize(){
        lessonTitleText.setText("Summary: "+cmf.getLesson().getSubject());
        
    }

}
