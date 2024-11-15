package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.BeforeEach;

public class CourseListTest {
    
    private CourseList courses;
    private Course course;
    private Course testCourse;


    @BeforeEach
    public void setUp(){
        CourseList courses = CourseList.getInstance();
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Word[] testWords = {(new Word("testWord1", "testMeaning1")), (new Word("testWord2", "testMeaning2"))};
        Phrase[] testPhrases = {(new Phrase("testPhrase1", "testTranslation1")), (new Phrase("testPhrase2", "testTranslation2"))};
        lessons.add(new Lesson("testSubject", "testIntro", testWords, testPhrases, 0));

        course = new Course(Language.SPANISH, UUID.randomUUID(), lessons);
        testCourse = new Course(Language.FRENCH, UUID.randomUUID(), lessons);

    }


    @Test   //  Currently missing a way to add a course with a UUID
    public void testAddCourse() {

        courses.addCourse(course.getLanguage(), course.getLessons());

        course = courses.getCourse(1);

        assertEquals(course, courses.getCourse(1));

    }

    @Test   //  Currently missing a way to check for and not include a duplicate
    public void testAddDuplicateCourse() {

        courses.addCourse(course.getLanguage(), course.getLessons());

        courses.addCourse(course.getLanguage(), course.getLessons());

        assertTrue(courses.getCourse(2) == null);

    }

    @Test
    public void testGetCoursesFromUUID() {

        assertFalse(courses.getCourse(UUID.fromString("b1303bf7-6575-41ea-93fb-4bb8da658eda")) == null);

    }

    @Test   //  Currently pulls the first course in the list with the same language
    public void testGetCoursesFromLanguage() {

        courses.addCourse(course.getLanguage(), course.getLessons());
        
        courses.addCourse(testCourse.getLanguage(), testCourse.getLessons());

        assertEquals(testCourse.getLessons(), courses.getCourse(Language.FRENCH).getLessons());

    }

    @Test
    public void testGetCoursesFromIndex() {

        courses.addCourse(course.getLanguage(), course.getLessons());
        
        courses.addCourse(testCourse.getLanguage(), testCourse.getLessons());

        assertEquals(testCourse.getLessons(), courses.getCourse(2).getLessons());

    }

}
