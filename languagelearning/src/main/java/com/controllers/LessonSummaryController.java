package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.WordList;

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

    @FXML
    private Text missedwordthree;

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
        WordList missedWords = cmf.getUser().getIncorrect();
        if(missedWords.getWords().get(0) != null && missedWords.getWords().size() >= 1){
            missedwordone.setText(missedWords.getWords().get(0).getWord());
        }
        else{
            missedwordone.setText("");
        }
        if(missedWords.getWords().get(0) != null && missedWords.getWords().size() >= 2){
            missedwordtwo.setText(missedWords.getWords().get(1).getWord());
        }
        else{
            missedwordtwo.setText("");
        }
        if(missedWords.getWords().get(0) != null && missedWords.getWords().size() >= 3){
            missedwordthree.setText(missedWords.getWords().get(2).getWord());
        }
        else{
            missedwordthree.setText("");
        }
    }

}
