package com.model;

import java.util.ArrayList;
import java.util.UUID;

// CourseList Class
public class CourseList {

    private ArrayList<Course> courses;
    private static CourseList courseList;

    /**
     * Private constructor, gets all courses
     */
    private CourseList() {
        DataReadWriter drw = new DataReadWriter();
        courses = DataReadWriter.getCourses();
    }

    /**
     * Ensures only one courselist can exist
     * @return Currect CourseList
     */
    public static CourseList getInstance() {
        if (courseList == null){
            courseList = new CourseList();
        }
        return courseList;
    }

    /**
     * Adds course to courselist
     * @param language Language of course to be added
     * @param lessons Lessons in course to be added
     */
    public void addCourse(Language language, ArrayList<Lesson> lessons) {
        Course course = new Course(language, lessons);
        courses.add(course);
    }

    /**
     * Gets a course based on its UUID
     * @param uuid UUID of course to be searched for
     * @return Course that matches the UUID given
     */
    public Course getCourse(UUID uuid) {
        for (Course course : courses){
            if (course.getUuid().equals(uuid)) {
                return course;
            }
        }
    return null;
    }

    /**
     * Gets course from a language
     * @param language language to be searched for
     * @return Course, 1st course of this language
     */
    public Course getCourse(Language language) {
        for (Course course : courses){
            if (course.getLanguage() == language) {
                return course;
            }
        }
    return null;
    }

    /**
     * Gets a course based on it's index in the list
     * @param index Int index of course to be searched for
     * @return Course at index
     */
    public Course getCourse(int index){
        return courses.get(index);
    }

    /**
     * Displays All available courses by name and index
     */
    public String toString(){
        String display = "Available Courses: \n";
        int i = 0;
        String tempString = "";
        for (Course course : courses){
            tempString += (i+1 + ". " + course.getLanguage().name() + "\n");
        }
        display += tempString;
        return display;
    }
}