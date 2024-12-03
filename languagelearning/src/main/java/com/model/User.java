package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Represents a user in the system with various attributes and methods 
 * to manage user-related information and functionalities.
 */
public class User {
    private String userName;
    private String password;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private HashMap<Course, Integer> courses;
    private int proficiencyPointTotal;
    private Settings settings = new Settings(false, false);
    private WordList correctWords;
    private WordList incorrectWords;

    /**
     * Constructs a User object with specified details.
     * @param userName             the username of the user
     * @param password             the password of the user
     * @param uuid                 the unique identifier for the user
     * @param firstName            the first name of the user
     * @param lastName             the last name of the user
     * @param emailAddress         the email address of the user
     * @param courses              a HashMap of courses and their progress
     * @param proficiencyPointTotal the total proficiency points of the user
     * @param settings             the settings associated with the user
     */
    public User(String userName, String password, UUID uuid, String firstName, String lastName, 
                String emailAddress, HashMap<Course, Integer> courses, int proficiencyPointTotal, Settings settings ){
        this.userName = userName;
        this.password = password;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName; 
        this.emailAddress = emailAddress;
        this.courses = courses;
        this.proficiencyPointTotal = proficiencyPointTotal;
        this.settings = settings;      
        correctWords = new WordList(); 
        incorrectWords = new WordList();             
    }

    /**
     * Constructs a User object with a username, password, and email address.
     * A UUID is generated automatically.
     * @param userName     the username of the user
     * @param password     the password of the user
     * @param emailAddress the email address of the user
     */
    public User(String userName, String password, String emailAddress){
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.uuid = createUUID();
    }

    public WordList getIncorrect(){
        return this.incorrectWords;
    }

    public WordList getCorrect() {
        return this.correctWords;
    }
    /** 
     * Gets the username of the user.
     * @return the username
     */
    public String getUsername(){
        return userName;
    }

    public void setWordList(WordList words){
        this.incorrectWords = words;
    }

    /** 
     * Gets the first name of the user.
     * @return the first name
     */
    public String getFirstName(){
        return firstName;
    }

    /** 
     * Gets the last name of the user.
     * @return the last name
     */
    public String getLastName(){
        return lastName;
    }

    /** 
     * Gets the password of the user.
     * @return the password
     */
    public String getPassword(){
        return password;
    }

    /** 
     * Gets the email address of the user.
     * @return the email address
     */
    public String getEmailAddress(){
        return emailAddress;
    }

    /**
     * Retrieves a specific course associated with the user by its UUID.
     * @param language the UUID of the course to retrieve
     * @return the Course associated with the specified UUID, or null if not found
     */
    public Course getCourse(UUID language){
        return null;
    }

    /** 
     * Gets the progress of the specified course.
     * @param course the course to get the progress for
     * @return the progress percentage of the course
     */
    public int getCourseProgress(Course course){
        return courses.get(course);
    }

    /** 
     * Gets the HashMap of courses associated with the user.
     * @return a HashMap of courses and their progress
     */
    public HashMap<Course, Integer> getCourses(){
        return this.courses;
    }

    /** 
     * Gets the UUID of the user.
     * @return the UUID
     */
    public UUID getUuid(){
        return this.uuid;
    }

    /** 
     * Gets the total proficiency points of the user.
     * @return the proficiency point total
     */
    public int getProfPoints(){
        return this.proficiencyPointTotal;
    }

    /**
     * Sets the user's first and last name.
     * @param firstName the new first name
     * @param lastName  the new last name
     */
    public void setName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets the user's email address.
     * @param emailAddress the new email address
     */
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    /**
     * Sets the progress of courses based on an ArrayList of lesson progress.
     * @param lessonProgress an ArrayList representing lesson progress
     */
    // public void setCourseProgress(ArrayList<Integer> lessonProgress){
    //     // Implementation goes here
    // }

    /**
     * Sets the user's password.
     * @param password the new password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Adds a new course to the user's courses.
     * @param course the Course to be added
     */
    public void addCourse(Course course){
        if(courses == null){
            courses = new HashMap<Course, Integer>();
        }
        courses.put(course, 0);
    }

    /**
     * Updates the progress for a specific lesson in a course.
     * @param course        the course to update
     * @param lesson        the lesson index
     * @param lessonProgress the progress to be added for the lesson
     */
    // public void addCourseProgress(Course course, int lesson, int lessonProgress){
    //     int addProgress = 1 / courses.size();
    //     int progress = courses.get(course) + addProgress;
    //     courses.put(course, progress);
    // }

    /**
     * Returns a string representation of the user, including their username and name.
     * @return a string containing the username and name
     */
    public String toString() {
        return "Username: " + userName +
               "\nName: " + firstName + " " + lastName;
    }

    /**
     * Checks if the provided username and password match the user's credentials.
     * @param username the username to check
     * @param password the password to check
     * @return true if the credentials match, false otherwise
     */
    public boolean isMatch(String username, String password){
       return this.userName.equals(username) && this.password.equals(password);
    }

    /**
     * Sets the user's settings for dark mode and email notifications.
     * @param darkModeOn       boolean indicating if dark mode should be enabled
     * @param emailNotifsOn    boolean indicating if email notifications should be enabled
     */
    public void setSettings(Boolean darkModeOn, Boolean emailNotifsOn){
       settings.toggleDarkMode(darkModeOn);
       settings.toggleEmailNotification(emailNotifsOn);
    }

    /**
     * Retrieves the settings associated with the user.
     * @return the Settings object
     */
    public Settings getSettings(){
        return settings;
    }

    /**
     * Generates a new UUID for the user.
     * @return a randomly generated UUID
     */
    private UUID createUUID(){
        return UUID.randomUUID();
    }
}
