package com.model;

// FillIn Exercise Class
public class FillIn extends Exercise {
    protected Phrase phrase;
    protected String question;
    protected String answer;
    protected String translation;
    private boolean firstTry;

    /**
     * CLASS AUTHOR FILL THIS IN
     * @param question
     */
    public FillIn(Phrase question) {
        firstTry = true;
        this.phrase = question;
        this.translation = question.getTranslation();
        this.question = question.getPhraseWithBlank();
        this.answer = question.getMissingWord();
    }

    public boolean getFirstTry(){
        return firstTry;
    }

    public String getType(){
        return "fillin";
    }

    public void tried(){
        firstTry = false;
    }

    public Word getWord(){
        return new Word(phrase.getPhrase(), phrase.getTranslation());
    }

    /**
     * CLASS AUTHOR FILL THIS IN
     */
    public boolean isCorrect(String userAnswer) {
        return (answer.trim().equalsIgnoreCase(userAnswer.trim()));
    }

    public String toString() {
        return ("Fillin: Fill in the missing word in the following phrase:\n"+ this.translation + "\n" + this.question );
    }
}


/*
package com.narration;

import java.util.ArrayList;
import java.util.Random;

public class FillIn extends Exercise1{

    public FillIn(ArrayList<Word> words) {
        super(words);
        Random rand = new Random();
        this.answer = words.get(rand.nextInt(0, words.size()));
    }

    public boolean isCorrect(String useranswer){
        return useranswer.trim().equalsIgnoreCase(answer.getWord());
    }

    public String toString(){
        return "The spanish translation for " + answer.getMeaning() + " is: _______";
    }
}
*/