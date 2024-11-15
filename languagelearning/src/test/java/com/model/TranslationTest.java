package com.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.BeforeEach;

public class TranslationTest {
    
    Translation translation;

    @BeforeEach
    public void setUp(){

        translation = new Translation(new Word("Test1", "Test2"));

    }

    @Test
    public void testIsCorrect() {

        assertTrue(translation.isCorrect("Test1"));
        assertFalse(translation.isCorrect("Test2"));
        assertFalse(translation.isCorrect(""));

    }

    @Test
    public void testGetWord() {

        assertEquals("Test1", translation.getWord().getWord());
        assertEquals("Test2", translation.getWord().getMeaning());

    }

}
