package com.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.BeforeEach;

public class AudioTest {
    
    Audio audio;

    @BeforeEach
    public void setUp(){

        Word[] words = {(new Word("Test1", "Test2")), (new Word("Test1", "Test2"))};
        audio = new Audio(words);

    }

    @Test
    public void testIsCorrect() {

        assertTrue(audio.isCorrect("Test1"));
        assertFalse(audio.isCorrect("Test2"));
        assertFalse(audio.isCorrect(""));

    }

    @Test
    public void testGetWord() {
    
        assertEquals("Test1", audio.getWord().getWord());
        assertEquals("Test2", audio.getWord().getMeaning());

    }

}
