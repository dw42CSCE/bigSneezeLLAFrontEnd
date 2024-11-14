package com.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import com.narration.*;

public class Word {
    private String word;
    private String meaning;
    private ArrayList<Word> similarWords;
    private UUID uuid;

    public Word(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord(){
        return this.word;
    }

    public String getMeaning(){
        return this.meaning;
    }

    public void speak(){
        Narrator.playSound(this.word);
    }

    public String toString(){
        return (this.word+" : "+this.meaning);
    }

    public UUID getUUID(){
        return this.uuid;
    }

    public ArrayList<Word> getSimilarWords(){
        
        return similarWords;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Word)) return false;
        Word other = (Word) obj;
        return this.word.equals(other.word) && this.meaning.equals(other.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, meaning);
    }
        
}
