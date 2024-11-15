package com.model;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.AfterEach;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.UUID;

public class CourseTest {

    private Course course;

    @AfterEach
    public void tearDown() {
        course = null;
    }

    @Test
    public void testGetLesson() {

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Word[] testWords = {(new Word("testWord1", "testMeaning1")),(new Word("testWord2", "testMeaning2"))};
        Phrase[] testPhrases = {(new Phrase("testPhrase1", "testTranslation1")), (new Phrase("testPhrase2", "testTranslation2"))};
        lessons.add(new Lesson("testSubject1", "testIntro1", testWords, testPhrases, 0));

        Course course = new Course(Language.SPANISH, UUID.randomUUID(), lessons);

        assertEquals("testSubject1", course.getLesson(0).getSubject());

    }

    @Test
    public void testGetLesson5() {

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Word[] testWords = {(new Word("testWord1", "testMeaning1")),(new Word("testWord2", "testMeaning2"))};
        Phrase[] testPhrases = {(new Phrase("testPhrase1", "testTranslation1")), (new Phrase("testPhrase2", "testTranslation2"))};
        lessons.add(new Lesson("testSubject1", "testIntro1", testWords, testPhrases, 0));
        lessons.add(new Lesson("testSubject2", "testIntro1", testWords, testPhrases, 0));
        lessons.add(new Lesson("testSubject3", "testIntro1", testWords, testPhrases, 0));
        lessons.add(new Lesson("testSubject4", "testIntro1", testWords, testPhrases, 0));
        lessons.add(new Lesson("testSubject5", "testIntro1", testWords, testPhrases, 0));

        Course course = new Course(Language.SPANISH, UUID.randomUUID(), lessons);

        assertEquals("testSubject3", course.getLesson(2).getSubject());
        assertEquals("testSubject4", course.getLesson(3).getSubject());
        assertEquals("testSubject5", course.getLesson(4).getSubject());

    }

    @Test
    public void testGetLessons() {

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Word[] testWords = {(new Word("testWord1", "testMeaning1")),(new Word("testWord2", "testMeaning2"))};
        Phrase[] testPhrases = {(new Phrase("testPhrase1", "testTranslation1")), (new Phrase("testPhrase2", "testTranslation2"))};
        lessons.add(new Lesson("testSubject1", "testIntro1", testWords, testPhrases, 0));

        Course course = new Course(Language.SPANISH, UUID.randomUUID(), lessons);

        assertEquals(lessons, course.getLessons());

    }

    @Test
    public void testGetTestUuid() {

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Word[] testWords = {(new Word("testWord1", "testMeaning1")),(new Word("testWord2", "testMeaning2"))};
        Phrase[] testPhrases = {(new Phrase("testPhrase1", "testTranslation1")), (new Phrase("testPhrase2", "testTranslation2"))};
        lessons.add(new Lesson("testSubject1", "testIntro1", testWords, testPhrases, 0));

        UUID uuid = UUID.randomUUID();
        Course course = new Course(Language.SPANISH, uuid, lessons);

        assertEquals(uuid, course.getUuid());

    }

    @Test
    public void testGetLanguages() {

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Word[] testWords = {(new Word("testWord1", "testMeaning1")),(new Word("testWord2", "testMeaning2"))};
        Phrase[] testPhrases = {(new Phrase("testPhrase1", "testTranslation1")), (new Phrase("testPhrase2", "testTranslation2"))};
        lessons.add(new Lesson("testSubject1", "testIntro1", testWords, testPhrases, 0));

        Course course = new Course(Language.SPANISH, UUID.randomUUID(), lessons);

        assertEquals(Language.SPANISH, course.getLanguage());

    }

}
