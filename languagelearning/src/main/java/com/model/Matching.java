package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Matching Exercise Class
public class Matching extends Exercise {
    protected String question;
    protected String options;
    protected String answer;
    protected Word[] words;
    private boolean firstTry;

    /**
     * CLASS AUTHOR WRITE THIS
     * @param answers
     */
    public Matching(Word[] words) {
        firstTry = true;
        this.question = "";
        this.options = "";
        this.answer = "";
    
        // Limit words to a maximum of three items
        ArrayList<Word> answers = new ArrayList<>(Arrays.asList(words));
        Collections.shuffle(answers); // Shuffle for randomness
        if (answers.size() > 3) {
            answers = new ArrayList<>(answers.subList(0, 3)); // Keep only the first three
        }

        Word[] shuffledWords = {answers.get(0), answers.get(1), answers.get(2)};
        this.words = shuffledWords;

        // Building the question part
        int i = 1;
        for (Word word : answers) {
            this.question += (i + ". " + word.getWord() + "\n");
            this.answer += (word.getMeaning()+",");
            i++;
        }

        this.answer = answer.substring(0, answer.length() - 1);
    
        // Shuffle again for answer options to randomize them
        Collections.shuffle(answers);
    
        // Building the options part
        char optionLetter = 'A';
        i = 1;
    
        for (Word word : answers) {
            this.options += (optionLetter + ". " + word.getMeaning() + "\n");
            i++;
            optionLetter++;
        }
    }

    public void tried(){
        firstTry = false;
    }

    public boolean getFirstTry(){
        return firstTry;
    }

    public Word getWord(){
        return words[0];
    }

    public Word[] getWords(){
        return this.words;
    }

    public String getType(){
        return "matching";
    }

    /**
     * Checks if userAnswer is correct
     * @param userAnswer String userAnswer to be checked against
     * @return True if userAnswer is correct, else false
     */
    public boolean isCorrect(String userAnswer) {
        return (this.answer.trim().equalsIgnoreCase(userAnswer.trim()));
    }

    public String toString() {
        return ("Match the words to the translations. \n(Answer Format: \"TranslationA,TranslationC,TranslationD,TranslationA\")\n"+question+"\n"+options);
    }


}

/*
package com.narration;

import java.util.ArrayList;
import java.util.Random;

public class Matching extends Exercise1{
    protected Word answer;
    protected ArrayList<Word> answers;
    private ArrayList<String> choices;
    private int answerIndex;

    public Matching(ArrayList<Word> words){
        super(words);
        Random rand = new Random();
        this.answer = words.get(rand.nextInt(0, words.size()));
        choices = new ArrayList<String>(4);
        answerIndex = rand.nextInt(0, 4);
        for(int i = 0; i < choices.size(); i++) {
            if(i == answerIndex) {
                choices.add(answer.getWord());
            }
            choices.add(answer.getSimilarWords().get(i).getWord());
        }
    }

    public boolean isCorrect(String userAnswer){
        return answerIndex + 1 == Integer.parseInt(userAnswer);
    }

    public String toString(){
        return "Which of the following words is the Spanish equivalent of " + answer.getMeaning() + "?" +
        "\n 1. " + choices.get(0) +
        "\n 2. " + choices.get(1) +
        "\n 3. " + choices.get(2) +
        "\n 4. " + choices.get(3);
    }
}
*/