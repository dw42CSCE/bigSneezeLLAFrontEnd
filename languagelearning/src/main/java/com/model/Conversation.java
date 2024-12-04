package com.model;

// Conversation Exercise Class
public class Conversation extends Exercise {
    protected Phrase phrase;
    protected Phrase question;
    private boolean firstTry;

    /**
     * Parameterized Constructor for Conversation
     * @param question UNSURE, AUTHOR OF CLASS WRITE
     * @param options UNSURE, AUTHOR OF CLASS WRITE
     */
    public Conversation(Phrase question) {
        this.question = question;
    }

    /**
     * Returns the type, conversation
     */
    public String getType(){
        return "conversation";
    }

    public boolean getFirstTry(){
        return firstTry;
    }

    public void tried(){
        firstTry = false;
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