package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.Exercise;
import com.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CourseHomeController {
    
    @FXML
    private ScrollPane ch_missedWordsScroll;
    
    @FXML
    private ScrollPane ch_masteredWordsScroll;
    
    @FXML
    private ProgressBar ch_progressBar;
    
    @FXML
    private Text ch_progressText;
    
    @FXML
    private Label lbl_language;


    private CourseManagerFacade cmf;
    private ArrayList<Word> missed;
    private ArrayList<Word> correct;

    @FXML
    public void initialize(){
        cmf = cmf.getInstance();
        cmf.setLesson(0);
        updateProgress();
        missed = cmf.getUser().getIncorrect().getWords();
        correct = cmf.getUser().getCorrect().getWords();
        //missed.add(new Word("Hola", "Hello"));
        loadVocab();
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

    private void updateProgress() {
        ch_progressBar.setProgress(cmf.getLessonProgress());
    }

    private void loadVocab() {
        if (missed != null) {
            loadScroll(missed, ch_missedWordsScroll);
        }

        if (correct != null){
            loadScroll(correct, ch_masteredWordsScroll);
        }
    }

    private void loadScroll(ArrayList<Word> words, ScrollPane scrollP) {
        VBox vbox = new VBox();
        for (Word word : words) {
            Label vocLabel = new Label(word.getWord());
            vbox.getChildren().add(vocLabel);
            scrollP.setContent(vbox);
        }
    }
}
