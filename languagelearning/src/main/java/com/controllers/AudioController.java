package com.controllers;

import static org.junit.Assert.assertThrows;

import java.io.IOException;

import com.language.App;
import com.model.*;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import com.narration.*;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class AudioController {

    private CourseManagerFacade cmf;
    private Lesson currentLesson;
    private Audio currentExercise;

    @FXML
    private Text lessonTitleText;

    @FXML
    private RadioButton option1Radio;
    @FXML
    private RadioButton option2Radio;
    @FXML
    private RadioButton option3Radio;
    @FXML
    private RadioButton option4Radio;

    // @FXML
    private boolean correct;

    private ToggleGroup toggleGroup;

    public AudioController() {
        cmf = cmf.getInstance();
    }

    @FXML
    public void initialize() {
        // Initialize ToggleGroup
        toggleGroup = new ToggleGroup();

        // Add RadioButtons to the ToggleGroup
        option1Radio.setToggleGroup(toggleGroup);
        option2Radio.setToggleGroup(toggleGroup);
        option3Radio.setToggleGroup(toggleGroup);
        option4Radio.setToggleGroup(toggleGroup);

        // Retrieve lesson and exercise
        currentLesson = cmf.getLesson();
        currentExercise = (Audio) cmf.getExercise();

        if (currentLesson != null && currentExercise != null) {
            lessonTitleText.setText(currentLesson.getSubject());

            option1Radio.setText(currentExercise.getOptions().get(0).getMeaning());
            option2Radio.setText(currentExercise.getOptions().get(1).getMeaning());
            option3Radio.setText(currentExercise.getOptions().get(2).getMeaning());
            option4Radio.setText(currentExercise.getOptions().get(3).getMeaning());
        } else {
            System.out.println("Lesson or exercise is null.");
        }
    }



    @FXML
    void checkAnswer(ActionEvent event) {
        for(Exercise exercise : cmf.getExercises()){
            System.out.println(exercise.getWord());
        }
        RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedButton != null) {
            String selectedAnswer = selectedButton.getText();
            System.out.println(selectedAnswer);

            if (currentExercise.isCorrect(selectedAnswer)) {
                System.out.println("Correct Answer!");
                correct = true;
                if(currentExercise.getFirstTry()){
                    cmf.incrementScore();
                }
            } else {
                System.out.println("Incorrect Answer!");
            }
        } else {
            System.out.println("No option selected.");
        }
    }


    @FXML
    void nextQuestion(ActionEvent event) throws IOException {
        if(correct){
            cmf.incrementLessonProgress();
            if(cmf.getLessonProgress() == 5){
                System.out.println("Switching to summary");
                App.setRoot("summary");
            } else{
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
                }//  else if(nextExercise.getType().equals("matching")){
                //     System.out.println("Switching to matching");
                //     App.setRoot("matching");
                // }
            

                // THIS IS THE TESTING LOGIC TEMPORARY!!!!
                // Word word1 = new Word("test", "test");
                // Word word2 = new Word("test2", "test2");
                // Word word3 = new Word("test3", "test3");
                // Word word4 = new Word("test4", "test4");
                // Word[] words = {word1, word2, word3, word4};
                // Audio audio = new Audio(words);
                // cmf.setExercise(audio);
                // App.setRoot("audio");
            }
        }
    }

    @FXML
    void previousQuestion(ActionEvent event) throws IOException {
        if (cmf.getLessonProgress() > 1) {
            cmf.decrementLessonProgress();
            Exercise prevExercise = cmf.getExercises().get(cmf.getLessonProgress() - 1);
            initialize();
    
            switch (prevExercise.getType()) {
                case "translation":
                    System.out.println("Switching to translation");
                    App.setRoot("translation");
                    break;
                case "fillin":
                    System.out.println("Switching to fillin");
                    App.setRoot("fillin");
                    break;
                case "audio":
                    System.out.println("Switching to audio");
                    App.setRoot("audio");
                    break;
                default:
                    System.out.println("Unknown exercise type");
                    break;
            }
        } else {
            System.out.println("Already at the first question. Cannot go back.");
        }
    }
    
    @FXML
    void sayWord(MouseEvent event) {
        if (currentExercise != null) {
            System.out.println("Lesson progress: " + cmf.getLessonProgress());
            System.out.println("Number of exercises: " + cmf.getExercises().size());

            Word word = currentExercise.getWord();
            Narrator.playSound(word.getWord());
            System.out.println("Playing word: " + word);
        }
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