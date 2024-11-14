package com.model;

// Translation Exercise Class
public class Translation extends Exercise {

    protected Word answer;

    /**
     * Parameterized Constructor
     * @param word Word word
     */
    public Translation(Word word) {
        this.answer = word;
    }

    public Word getWord(){
        return answer;
    }

    /**
     * Checks if user's answer is right
     * @param useranswer String of the user's answer
     * @return Boolean, true if right, false if wrong
     */
    public boolean isCorrect(String useranswer){
        return useranswer.trim().equalsIgnoreCase(answer.getWord());
    }

    public String toString(){
        return "The spanish translation for " + answer.getMeaning() + " is:";
    }
}
