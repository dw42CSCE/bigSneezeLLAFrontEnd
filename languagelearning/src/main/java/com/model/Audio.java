package com.model;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Audio Exercise Class
public class Audio extends Exercise {
    protected ArrayList<Word> options;
    protected Word answer;

    /**
     * Constructor for Audio
     * param options Arraylist of strings to generate question from
     *
    public Audio(ArrayList<String> options) {
        this.options = options;
        Random random = new Random();
        int r = random.nextInt(options.size());
        this.answer = options.get(r);
    }
    */

    /**
     * Constructor for Audio
     * @param options Arraylist of strings to generate question from
     */
    public Audio(Word[] words) {
        this.options = new ArrayList<Word>();
        for (Word word : words) {
            this.options.add(word);
        }

        Collections.shuffle(options);
        Random random = new Random();
        int r = random.nextInt(options.size());
        this.answer = options.get(r);
    }

    /**
     * Checks user answer
     * @param userAnswer string of the users attempt
     * @return True if the user answer is the actual answer, else false
     */
    public boolean isCorrect(String userAnswer) {
        return (this.answer.getWord().trim().equalsIgnoreCase(userAnswer.trim()));
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
