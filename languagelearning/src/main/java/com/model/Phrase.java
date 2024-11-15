package com.model;

import java.util.ArrayList;
import java.util.Random;

import software.amazon.awssdk.regions.servicemetadata.RamServiceMetadata;

// Phrase Class
public class Phrase {

    private String phrase;
    private String translation;
    private ArrayList<String> responses;
    private String phraseWithBlank;
    private String missingWord;

    /**
     * Parameterized Constructor
     * @param phrase String of the phrase
     * @param translation String of the english translation
     */
    public Phrase(String phrase, String translation) {
        if (phrase == null){
            phrase = "Default";
        }
        if (translation== null){
            translation = "Default";
        }
        this.phrase = phrase;
        this.translation = translation;
        String array[] = phrase.split(" ");
        Random r = new Random();
        int i = r.nextInt(array.length);
        this.missingWord = array[i];
        array[i] = "_____";
        this.phraseWithBlank = String.join(" ", array);
    }

    public Phrase(String phrase, String translation, ArrayList<String> responses, String phraseWithBlank, String missingWord) {
        this.phrase = phrase;
        this.translation = translation;
        this.responses = responses;
        this.phraseWithBlank = phraseWithBlank;
        this.missingWord = missingWord;
    }


    /**
     * Gets the string phrase
     * @return String of the phrase
     */
    public String getPhrase(){
        return this.phrase;
    }

    /**
     * Gets english translation
     * @return String english translation
     */
    public String getTranslation(){
        return this.translation;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public String getPhraseWithBlank(){
        return this.phraseWithBlank;
    }

    public String getMissingWord(){
        return this.missingWord;
    }

    public String toString(){
        return (this.phrase + " : " + this.translation);
    }
}
