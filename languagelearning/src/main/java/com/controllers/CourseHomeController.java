package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.language.App;
import com.model.CourseManagerFacade;
import com.model.Exercise;
import com.model.Lesson;
import com.model.User;
import com.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    @FXML
    private Text lessonName;

    @FXML
    private Text passengerName;

    @FXML
    private Text courseName;

    @FXML
    private ChoiceBox<String> lesssonSelection;


    private CourseManagerFacade cmf;
    private ArrayList<Word> missed;
    private ArrayList<Word> correct;

    @FXML
    public void initialize(){
        cmf = cmf.getInstance();
        cmf.setLesson(0);
        loadChoiceBox();
        // lesssonSelection.getItems().add(cmf.getCurrLesson().getSubject());
        updateProgress();
        missed = cmf.getUser().getIncorrect().getWords();
        correct = cmf.getUser().getCorrect().getWords();
        //missed.add(new Word("Hola", "Hello"));
        loadVocab();
        User user = cmf.getUser();
        passengerName.setText(user.getFirstName()+" "+ user.getLastName());
        lessonName.setText(cmf.getLesson().getSubject());
        lbl_language.setText(cmf.getCourse().getLanguage().toString());
        // lessonName.setText(lesssonSelection.getValue());
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

        // if (correct != null){
        //     loadScroll(correct, ch_masteredWordsScroll);
        // }
    }

    private void loadScroll(ArrayList<Word> words, ScrollPane scrollP) {
        VBox vbox = new VBox();
        for (Word word : words) {
            Label vocLabel = new Label(word.getWord());
            vbox.getChildren().add(vocLabel);
            scrollP.setContent(vbox);
        }
    }

    private void loadChoiceBox() {
        for (int i = 0; i <= cmf.getUser().getCourseProgress(cmf.getCourse()); i++)
            lesssonSelection.getItems().add(cmf.getCourse().getLesson(i).getSubject());
    }
}
