package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import com.model.*;

import com.language.App;

public class LessonController {

    private CourseManagerFacade cmf;

    @FXML
    private Text lessonIntro;

    @FXML
    private Text lessonKeyWords;

    @FXML
    private Text lessonTitleText;

    @FXML
    private Rectangle lessonKeyWordsBg; 

    @FXML
    public void initialize() {
        // Initialize the CourseManagerFacade instance
        cmf = CourseManagerFacade.getInstance();
        // Set the lesson data dynamically
        updateLessonContent();
    }

    private void updateLessonContent() {
        // Example lesson data fetching from CourseManagerFacade
        String lessonTitle = cmf.getLesson().getSubject();
        String lessonIntroduction = cmf.getLesson().getIntro();
        String lessonKeywords = cmf.getLesson().toString();

        // Update the text elements in the UI
        lessonTitleText.setText(lessonTitle);
        lessonIntro.setText(lessonIntroduction);
        lessonKeyWords.setText("Keywords: " + lessonKeywords);
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
    public void nextQuestion() throws IOException {
        cmf.generateExercise();
        Exercise nextExercise = cmf.getExercise();
        switch (nextExercise.getType()) {
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
                System.err.println("Unknown exercise type!");
        }
    }
}
