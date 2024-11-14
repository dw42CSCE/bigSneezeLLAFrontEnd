package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class WordListTest {

    private WordList words;
    private Word word;
    private Word testWord;

    @BeforeEach
    public void setUp(){
        words = new WordList();
        word = new Word("Test", "Test");
    }
    

    //AddWord Tests
    @Test
    public void testAddWord(){
        this.words.addWord(word);
        testWord = words.getWord("Test");
        assertEquals(word, testWord);
    }

    @Test
    public void testAddDuplicateWord() {
        words.addWord(word);
        words.addWord("Test", "Test");
        int count = 0;
        for (Word testWord : words.getWords()) {
            if (testWord.getWord().equals("Test")) {
                count++;
            }
        }
        assertEquals(1, count);
    }
    

    @Test
    public void testNullWord(){
        Word test = words.addWord(null, "Test");
        assertNotNull(test.getWord());
    }

    @Test
    public void testNullMeaning(){
        Word test = words.addWord("Test", null);
        assertNotNull(test.getMeaning());
    }

    @Test
    public void testSameWordDiffMeaning(){
        words.addWord(new Word("Test", "First Meaning"));
        words.addWord(new Word("Test", "Different Meaning"));
        assertNotEquals(words.getWords().get(0).getMeaning(), words.getWords().get(1).getMeaning());
    }


    //Remove Word Tests
    @Test
    public void testRemoveWord(){
        words.addWord(word);
        words.removeWord(word);
        assertTrue(words.getWords().isEmpty());
    }

    @Test
    public void testRemoveWordWithMultipleMeanings(){
        words.addWord(new Word("Test", "First Meaning"));
        words.removeWord(words.getWord("Test"));
        assertTrue(words.getWords().isEmpty());
    }

    @Test
    public void testCaseSensitiveRemove(){
        words.addWord(word);
        words.removeWord(words.getWord("test"));
        assertTrue(words.getWords().isEmpty());
    }

    //Edit Word
    @Test
    public void testEditWord(){
        words.editWord("Test", "New word", "new def");
        assertEquals(words.getWord("New word").getWord(),"New word");
    }

    @Test
    public void testEditNonExist(){
        words.addWord(word);
        words.editWord("Unreal", "EditTest", "Test");
        int count =0;
        if(words.getWord("EditTest").equals("EditTest"))
            count++;
        assertEquals(0,count);
    }

    @Test
    public void testCaseSensitive(){
        words.addWord(word);
        words.editWord("test", "NewTest", "NewDef");
        assertEquals("NewTest", words.getWord("NewTest").getWord());
    }



    



 
 
    


    







}
