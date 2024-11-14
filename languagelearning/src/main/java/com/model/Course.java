package com.model;

import java.util.ArrayList;
import java.util.UUID;

// Course Class
public class Course {
    
    private Language language;
    private ArrayList<Lesson> lessons;
    private int proficiencyPts;
    private UUID uuid;

    /**
     * Parameterized Constructor for Course
     * @param language enum of the language the course is in
     * @param lessons List of lessons in the course
     */
    public Course(Language language, ArrayList<Lesson> lessons) {
        this.language = language;
        this.lessons = lessons;
    }

    /**
     * Parameterized Constructor for Course
     * @param language enum of the language the course is in
     * @param uuid UUID of the course
     * @param lessons List of lessons in the course
     */
    public Course(Language language, UUID uuid, ArrayList<Lesson> lessons) {
        this.language = language;
        this.lessons = lessons;
        this.uuid = uuid;
    }

    /**
     * Gets a lesson from a certain index in the courselist
     * @param index Int lesson number - 1 to be searched for. EX lesson 1 index = 0
     * @return Lesson at index
     */
    public Lesson getLesson(int index) {
        return lessons.get(index);
    }

    /**
     * Gives the whole list of lessons
     * @return Arraylist of Lessons
     */
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Gets the uuid of this course
     * @return UUID uuid of this course
     */
    public UUID getUuid(){
        return this.uuid;
    }

    /**
     * Gets language of this course
     * @return Language of the current course
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Not implemented yet, might need for GUI
     */
    public String toString() {
        return "Test";
    }
}
