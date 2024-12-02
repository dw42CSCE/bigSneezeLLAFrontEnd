package com.model;

public abstract class Exercise {

    public abstract boolean isCorrect(String userAnswer);

    public abstract String toString();

    public abstract String getType();

    public abstract boolean getFirstTry();

    /**
     * Gets the word used in a specific question, if its a phrase, its converted to a word type
     * @return Subject of question in word form
     */
    public abstract Word getWord();

}

/*
package com.narration;

import java.util.ArrayList;
import java.util.Random;

public class Exercise {
    protected String question;
    protected String options;
    protected String answer;
    protected String type;

    public Exercise(String question, String type, String options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.type = type;
    }

    public abstract boolean isCorrect(String userInput);

    public abstract String toString();
}
*/