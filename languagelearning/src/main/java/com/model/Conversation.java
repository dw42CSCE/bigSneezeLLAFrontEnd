package com.model;

import java.util.ArrayList;

// Conversation Exercise Class
public class Conversation extends Exercise {
    protected Phrase phrase;
    protected Phrase question;

    /**
     * Parameterized Constructor for Conversation
     * @param question UNSURE, AUTHOR OF CLASS WRITE
     * @param options UNSURE, AUTHOR OF CLASS WRITE
     */
    public Conversation(Phrase question) {
        this.question = question;
    }

    /**
     * Checkes user answer
     * @param userAnswer String of the users input
     * @return True if the equal to actual answer, else false
     */
    public boolean isCorrect(String userAnswer) {
        for (String option : question.getResponses())  {
            if (option.trim().equalsIgnoreCase(userAnswer.trim()))
                return true;
        }
        return false;
    }

    public Word getWord(){
        return new Word(phrase.getPhrase(), phrase.getTranslation());
    }

    /**
     * To String
     * @return String of instructions
     */
    public String toString() {
        return ("Conversation: Respond to the following phrase.\n"+this.question.getPhrase());
    }
}