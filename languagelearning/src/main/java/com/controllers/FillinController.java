package com.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.language.App;
import com.model.Audio;
import com.model.CourseManagerFacade;
import com.model.Exercise;
import com.model.FillIn;
import com.model.Lesson;
import com.narration.*;
import com.model.User;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FillinController {

    private CourseManagerFacade cmf;
    private Lesson currentLesson;
    private FillIn currentExercise;
    private boolean correct;

    @FXML
    private Text lessonTitleText;

    @FXML
    private Text questionText;

    @FXML
    private TextField answerField;

    public FillinController() {
        cmf = cmf.getInstance();
    }

    @FXML
    public void initialize() {
        currentLesson = cmf.getLesson();
        currentExercise = (FillIn) cmf.getExercise();

        if (currentLesson != null && currentExercise != null) {
            lessonTitleText.setText(currentLesson.getSubject());
            questionText.setText(currentExercise.toString());
        } else {
            System.out.println("Lesson or exercise is null.");
        }
    }

    @FXML
    public void checkAnswers(ActionEvent event) {
        String selectedAnswer = answerField.getText();
        if (currentExercise.isCorrect(selectedAnswer)) {
            System.out.println("Correct Answer!");
            this.questionCorrect();
            correct = true;
            if(currentExercise.getFirstTry()){
                cmf.removeWord(currentExercise.getWord());
                cmf.incrementScore();
                System.out.println("Adding to score: " + cmf.getCurrentScore());
            }
        } else {
            cmf.addWord(currentExercise.getWord());
            currentExercise.tried();
            this.questionIncorrect();
            System.out.println("Incorrect Answer!");
        }
    }

    @FXML
    void nextQuestion(ActionEvent event) throws IOException {
        if(correct){
            System.out.println("Current Score: " + cmf.getCurrentScore());
            if(cmf.getCurrentScore() >=5 && cmf.getUser().getCourseProgress(cmf.getCourse()) < cmf.getCourse().getLessons().size() - 1&& cmf.getUser().getCourseProgress(cmf.getCourse()) <= cmf.getCourse().getLessons().indexOf(cmf.getLesson())){
                System.out.println("Lessons: " + cmf.getCourse().getLessons().size());
                System.out.println("adding course progress");
                cmf.addCourseProgress();
                System.out.println("Course Progress: " + cmf.getUser().getCourseProgress(cmf.getCourse()));
            }
            System.out.println(cmf.getLessonProgress());
            cmf.incrementLessonProgress();
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
                }  else if(nextExercise.getType().equals("audio")){
                    System.out.println("Switching to audio");
                    App.setRoot("audio");
                }
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