package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.*;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;

public class TranslationController {

    private CourseManagerFacade cmf;
    private Lesson currentLesson;
    private Translation currentExercise;
    private boolean correct;

    @FXML
    private Text lessonTitleText;

    @FXML
    private TextField userInputField;

    @FXML
    private Text spanishTranslation;

    public TranslationController() {
        cmf = cmf.getInstance();
    }

    @FXML
    public void initialize() {
        // Retrieve lesson and exercise
        currentLesson = cmf.getLesson();
        currentExercise = (Translation) cmf.getExercise();

        if (currentLesson != null && currentExercise != null) {
            lessonTitleText.setText(currentLesson.getSubject());
            spanishTranslation.setText(currentExercise.getWord().getWord());
        } else {
            System.out.println("Lesson or exercise is null.");
        }
    }

    @FXML
    void checkAnswer(ActionEvent event) {
        String userAnswer = userInputField.getText().trim();
        if (currentExercise != null) {
            if (currentExercise.isCorrect(userAnswer)) {
                System.out.println("Correct answer!");
                correct = true;
            } else {
                System.out.println("Incorrect answer. Try again!");
                System.out.println(currentExercise.getWord().getMeaning());
                System.out.println(userAnswer);
            }
        } else {
            System.out.println("No exercise to check.");
        }
    }


    @FXML
    void nextQuestion(ActionEvent event) throws IOException {
        if(correct){
            cmf.incrementLessonProgress();
            System.out.println(cmf.getLessonProgress());
            if(cmf.getLessonProgress() == 5){
                System.out.println("Switching to summary");
                App.setRoot("summary");
            } else{
                cmf.generateExercise(); 
                Exercise nextExercise = cmf.getExercise();
                if(nextExercise.getType().equals("conversation")){
                    System.out.println("Switching to conversation");
                    App.setRoot("converation");
                } else if(nextExercise.getType().equals("translation")){
                    System.out.println("Switching to translation");
                    App.setRoot("translation");
                } else if(nextExercise.getType().equals("fillin")){
                    System.out.println("Switching to fillin");
                    App.setRoot("fillin");
                } else if(nextExercise.getType().equals("matching")){
                    System.out.println("Switching to matching");
                    App.setRoot("matching");
                } else if(nextExercise.getType().equals("audio")){
                    System.out.println("Switching to audio");
                    App.setRoot("audio");
                }
            

                // THIS IS THE TESTING LOGIC TEMPORARY!!!!
                // Word word1 = new Word("test", "test");
                // Word word2 = new Word("test2", "test2");
                // Word word3 = new Word("test3", "test3");
                // Word word4 = new Word("test4", "test4");
                // Word[] words = {word1, word2, word3, word4};
                // cmf.setExercise(words);
                // App.setRoot("audio");
            }
        }
    }

    @FXML
    void previousQuestion(ActionEvent event) throws IOException{
        cmf.decrementLessonProgress();
        Exercise prevExercise = cmf.getExercises().get(cmf.getLessonProgress()-1);
        initialize();

        if(prevExercise.getType().equals("conversation")){
            System.out.println("Switching to conversation");
            App.setRoot("converation");
        } else if(prevExercise.getType().equals("translation")){
            System.out.println("Switching to translation");
            App.setRoot("translation");
        } else if(prevExercise.getType().equals("fillin")){
            System.out.println("Switching to fillin");
            App.setRoot("fillin");
        } else if(prevExercise.getType().equals("matching")){
            System.out.println("Switching to matching");
            App.setRoot("matching");
        } else if(prevExercise.getType().equals("audio")){
            System.out.println("Switching to audio");
            App.setRoot("audio");
        }

        // App.setRoot("audio");

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