package com.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;  

public class WordTest {

    @Test
    public void testWord(){
        Word word = new Word("Hola", "Hello");
        assertEquals(word.getMeaning(), "Hello");
        assertEquals(word.getWord(), "Hola");
    }

    @Test
    public void testNullWord(){
        Word word1 = new Word(null, "Hello");
        Word word2 = new Word("Hola", null);
        assertEquals("Hello", word1.getMeaning() );
        assertEquals("Default", word1.getWord() );
        assertEquals("Default", word2.getMeaning() );
        assertEquals("Hola", word2.getWord() );
    }

    @Test
    public void testToString(){
        Word word = new Word("Hola", "Hello");
        assertEquals(word.toString(), "Hola : Hello");
    }
}
