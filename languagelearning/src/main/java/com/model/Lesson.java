package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Lesson Class
public class Lesson {
    
    private String subject;
    private String intro;
    private ArrayList<Exercise> exercises;
    private Word[] keyWords;
    private Phrase[] keyPhrases;
    private int progress;

    /**
     * Parameterized Constructor for Lesson
     * @param subject String subject of lesson
     * @param intro String intro to lesson
     * @param exercises Exercies in lesson, NEEDED?
     * @param words Word Array of useful words in lesson
     * @param phrases Phrase array of useful phrases in lesson
     * @param progress Int representing lesson progress, NEEDED?
     */
    public Lesson(String subject, String intro, Word[] words, Phrase[] phrases, int progress) {

        this.subject = subject;
        this.intro = intro;
        this.keyWords = words;
        this.keyPhrases = phrases;        
    }

    /**
     * Gets an exercise from the list based on index
     * @param index Int exercise number
     * @return Exercise at the index
     */
    public Exercise getExercise(int index) {
        return exercises.get(index);
    }

    /**
     * Gets all Exercises from the lesson
     * @return ArrayList of all exercises
     */
    public ArrayList<Exercise> getExercises() {
        return this.exercises;
    }

    /**
     * Generates a random exercise
     * @return Exercise random exercise
     */
    public Exercise generateExercise() {
        Random rand = new Random();
        int choice = rand.nextInt(0,5);
        Exercise exercise = null;
        switch (choice) {
            case 0: //Translation
                exercise = new Translation(keyWords[(rand.nextInt(0, keyWords.length))]);
                // System.out.println("test1");
                break;
            case 1: //Matching
                exercise = new Matching(keyWords);
                // System.out.println("test2");
                break;
            case 2: //Audio
                exercise = new Audio(keyWords);
                // System.out.println("test3");
                break;
            case 3: //Conversation
                // exercise = new Conversation(keyPhrases[(rand.nextInt(0, keyPhrases.length))]);
                // System.out.println("test4");
                // break;
            case 4: //Fillin
                exercise = new FillIn(keyPhrases[(rand.nextInt(keyPhrases.length))]);
                // System.out.println("test5");
                break;
            default:
                exercise = new Translation(keyWords[(rand.nextInt(0, keyWords.length))]);
                // System.out.println("testD");
                break;
        }
        return exercise;
    }

    /**
     * Gets the subject of the lesson
     * @return String lesson subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the intro to the lesson
     * @return String lesson intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * Gives the lesson in String format
     * @return String of the lesson
     */
    public String toString() {
        return (this.subject + "\n" + this.intro) + getWords(keyWords) + getPhrases(keyPhrases);
    }

    /**
     * Makes a string of the words and their meaning
     * @param words Array of words
     * @return String of words and meanings
     */
    private String getWords(Word[] words){
        String sentence = "\n";
        for (int i = 0; i < words.length; i++){
            sentence += words[i].toString() + "\n";
        }
        return sentence;
    }

    /**
     * Makes a string of phrases and their meaning 
     * @param phrases Array of phrases
     * @return String of phrases and meanings
     */
    private String getPhrases(Phrase[] phrases){
        String sentence = "\n";
        for (int i = 0; i < phrases.length; i++){
            sentence += phrases[i].toString() + "\n";
        }
        return sentence;
    }
}
