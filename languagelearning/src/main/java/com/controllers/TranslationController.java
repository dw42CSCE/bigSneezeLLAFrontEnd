package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.*;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
                this.questionCorrect();
                correct = true;
                if(currentExercise.getFirstTry()){
                    cmf.removeWord(currentExercise.getWord());
                    cmf.incrementScore();
                    System.out.println("Adding to score: " + cmf.getCurrentScore());
                }
            } else {
                currentExercise.tried();
                cmf.addWord(currentExercise.getWord());
                this.questionIncorrect();
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
            System.out.println("Current Score: " + cmf.getCurrentScore());
            if(cmf.getCurrentScore() >=5 && cmf.getUser().getCourseProgress(cmf.getCourse()) < cmf.getCourse().getLessons().size() - 1 && cmf.getUser().getCourseProgress(cmf.getCourse()) <= cmf.getCourse().getLessons().indexOf(cmf.getLesson())){
                System.out.println("Lessons: " + cmf.getCourse().getLessons().size());
                System.out.println("adding course progress");
                cmf.addCourseProgress();
                System.out.println("Course Progress: " + cmf.getUser().getCourseProgress(cmf.getCourse()));
            }
            cmf.incrementLessonProgress();
            System.out.println(cmf.getLessonProgress());
            if(cmf.getLessonProgress() >= 5){
                System.out.println("Switching to summary");
                App.setRoot("lessonsummary");
            } else{
                cmf.generateExercise(); 
                Exercise nextExercise = cmf.getExercise();
                if(nextExercise.getType().equals("translation")){
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
                case "matching":
                    System.out.println("Switching to matching");
                    App.setRoot("matching");
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
    private void switchToDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

     @FXML
    private void questionCorrect() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Question Correct!\nProceed to the next question!"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    private void questionIncorrect() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Question Incorrect.\nTry Again!"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}