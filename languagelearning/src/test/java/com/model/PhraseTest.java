package com.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;  
import java.util.ArrayList;

public class PhraseTest {

    @Test
    public void testPhrase(){
        Phrase phrase = new Phrase("Como te llamas", "What is your name");

        assertEquals(phrase.getPhrase(), "Como te llamas");
        assertEquals(phrase.getTranslation(), "What is your name");
    }


    @Test
    public void testNullPhrase(){
        Phrase word1 = new Phrase(null, "Test");
        Phrase word2 = new Phrase("Test", null);
        assertEquals(word1.getTranslation(), "Test");
        assertEquals(word1.getPhrase(), "Default");
        assertEquals(word2.getTranslation(), "Default");
        assertEquals(word2.getPhrase(), "Test");
    }

    @Test
    public void testToString(){
        Phrase phrase = new Phrase("Como te llamas", "What is your name");

        assertEquals(phrase.toString(), "Como te llamas : What is your name");
    }

    @Test
    public void testGetWordWithBlank(){
        Phrase phrase = new Phrase("test", "test");

        assertEquals(phrase.getPhraseWithBlank(), "_____");
    }
    
    @Test
    public void testGetMissingWord(){
        Phrase phrase = new Phrase("test", "test");

        assertEquals(phrase.getMissingWord(), "test");
    }

    @Test
    public void testParamConstructor(){
        ArrayList<String> array = new ArrayList();
        array.add("test1m : test1t");
        Phrase phrase = new Phrase("test word", "test word", array, "test ______", "word");

        assertEquals(phrase.getResponses().get(0), "test1m : test1t");
    }
}
