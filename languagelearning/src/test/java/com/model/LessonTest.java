package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class LessonTest {

    //private Lesson lesson;
    Word[] keyWords = new Word[] { 
        new Word("Hola", "Hello"), 
        new Word("Adios", "Bye") };
    Phrase[] keyPhrases = new Phrase[] { 
        new Phrase("Como Estas", "How are you?"),
        new Phrase("Hasta Luego","See you later") };
    private Lesson lesson = new Lesson("Language Basics", "Introduction to basic phrases", keyWords, keyPhrases, 0);
    

    // @BeforeEach
    // public void setup() {
    //     Word[] keyWords = new Word[] { 
    //         new Word("Hola", "Hello"), 
    //         new Word("Adios", "Bye") };
    //     Phrase[] keyPhrases = new Phrase[] { 
    //         new Phrase("Como Estas", "How are you?"),
    //         new Phrase("Hasta Luego","See you later") };
    //     lesson = new Lesson("Language Basics", "Introduction to basic phrases", keyWords, keyPhrases, 0);
        
    // }

    @AfterEach
    public void tearDown() {
        lesson = null;
    }

    @Test
    public void testGetExercisesNotNull() {
        Exercise game = lesson.generateExercise();
        assertNotNull(game);
    }

    @Test
    public void testGenerateExerciseAreDifferentTwoWords() {
        Exercise e1 = lesson.generateExercise();
        Exercise e2 = lesson.generateExercise();
        assertNotEquals(e1, e2);
    }

    @Test
    public void testGenerateExerciseNullPhrases() {
        Word[] keyWords = new Word[] { 
            new Word("Hola", "Hello")}; 
        Phrase[] keyPhrases = null;
        Lesson lesson1 = new Lesson("Language Basics", "Introduction to basic phrases", keyWords, keyPhrases, 0);
        Exercise e1 = lesson1.generateExercise();
        assertNotNull(e1);
    }

    @Test
    public void testGenerateExerciseNullWords() {
        Word[] keyWords = null;
        Phrase[] keyPhrases = new Phrase[] { 
            new Phrase("Como Estas", "How are you?"),
            new Phrase("Hasta Luego","See you later") };
        Lesson lesson1 = new Lesson("Language Basics", "Introduction to basic phrases", keyWords, keyPhrases, 0);
        Exercise e1 = lesson1.generateExercise();
        assertNotNull(e1);
    }
}