package com.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.BeforeEach;

public class MatchingTest {
 
    Matching matching;

    @BeforeEach
    public void setUp(){

        Word[] words = {(new Word("Test1", "Test2")),(new Word("Test1", "Test2"))};
        matching = new Matching(words);

    }

    @Test
    public void testIsCorrect() {

        assertTrue(matching.isCorrect("Test1"));
        assertFalse(matching.isCorrect("Test2"));
        assertFalse(matching.isCorrect(""));

    }

    @Test
    public void testGetWord() {

        assertEquals("Test1", matching.getWord().getWord());
        assertEquals("Test2", matching.getWord().getMeaning());

    }

}
