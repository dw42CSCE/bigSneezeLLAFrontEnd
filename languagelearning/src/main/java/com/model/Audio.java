package com.model;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

// Audio Exercise Class
public class Audio extends Exercise {
    protected ArrayList<Word> options;
    protected Word answer;
    private boolean firstTry;

    /**
     * Constructor for Audio
     * @param options Arraylist of strings to generate question from
     */
    public Audio(Word[] words) {
        firstTry = true;
        this.options = new ArrayList<>();

        Set<Word> uniqueOptions = new HashSet<>();

        Random random = new Random();
        while (uniqueOptions.size() < 4 && uniqueOptions.size() < words.length) {
            Word randomWord = words[random.nextInt(words.length)];
            uniqueOptions.add(randomWord);
        }

        this.options.addAll(uniqueOptions);
        Collections.shuffle(options);

        this.answer = options.get(random.nextInt(options.size()));
    }


    /**
     * Checks user answer
     * @param userAnswer string of the users attempt
     * @return True if the user answer is the actual answer, else false
     */
    public boolean isCorrect(String userAnswer) {
        firstTry = false;
        return (this.answer.getMeaning().trim().equalsIgnoreCase(userAnswer.trim()));
    }

    public boolean getFirstTry(){
        return firstTry;
    }
    
    public Word getWord(){
        return answer;
    }

    public ArrayList<Word> getOptions(){
        return options;
    }

    public String getType(){
        return "audio";
    }

    /**
     * To String
     * @return String of instructions
     */
    public String toString() {
        String stringOptions = "";
        for (Word word : options) {
            stringOptions += (word+"\n");
        }
        answer.speak();
        return ("Audio: Listen to the audio and select the word.\n"+stringOptions);
    }
}
