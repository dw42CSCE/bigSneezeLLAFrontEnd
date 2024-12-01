package com.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import com.narration.*;

// CourseManagerFacade Class
public class CourseManagerFacade {
    private static CourseManagerFacade instance;

    private User user;
    private UserList users;
    private CourseList courses;
    private Course course;
    private Lesson lesson;
    private Exercise exercise;

    /**
     * Default Constructor, will get newest UserList and CourseList
     */
    private CourseManagerFacade() {
        courses = CourseList.getInstance();
        users = UserList.getInstance();
    }

    // Public method to access the single instance of the CMF
    public static CourseManagerFacade getInstance() {
        if (instance == null) {
            instance = new CourseManagerFacade();
        }
        return instance;
    }

    public void updateUser(String firstName, String lastName, String email, String password){
        users.editUser(firstName, lastName, email, password);
    }

    /**
     * Logs in a user and sets this user
     * @param username String of username
     * @param password String of password
     * @return User, null if not found
     */
     public User login(String username, String password) {
         users = UserList.getInstance();
         user = users.getUser(username, password);
         if (user == null) {
             System.out.println("Login failed: user not found.");
             return null;
         } else{
            System.out.println("Useremail:" + user.getEmailAddress());
            return user;
         }
     }

    /**
     * Adds a course to this user's list of courses
     * @param course Course to be added
     */
    public void addUserCourse(Course course){
        user.addCourse(course);
    }

    public User getUser(){
        return this.user;
    }
    
    /**
     * Adds a user to the userlist with minimum required info, checks for duplicate 
     * @param username String of username
     * @param email String of email
     * @param password String of password
     */
    public User signUp(String username, String email, String password) {
        users = UserList.getInstance();
        
        for (User existingUser : users.getUsers()) {
            if (existingUser.getUsername().equals(username)) {
                System.out.println("Username already taken.");
                return user;
            }
    
            if (existingUser.getEmailAddress().equals(email)) {
                System.out.println("Email already in use.");
                return user;
            }
        }
    
        users.addUser(username, email, password);
        DataReadWriter.updateUsers(users.getUsers());
        System.out.println("User signed up successfully!");
        user = users.getUser(username, password);
        return user;
    }
    
    /**
     * Gets a course by uuid
     * @param uuid UUID of course to be gotten
     * @return Course with matching UUID
     */
    public Course getCourse(UUID uuid) {
        course = user.getCourse(uuid);
        return course;
    }

    /**
     * Gets entire CourseList
     * @return CourseList current CourseList
     */
    public CourseList getAllCourses() {
        return courses;
    }

    /**
     * Gets entire current UserList
     * @return UserList current UserList
     */
    public UserList getUsers() {
        return users;  
    }

    /**
     * Toggles user dark mode
     * @param isDarkMode Boolean of mode to be set to
     */
    public void toggleDarkMode(boolean isDarkMode) {
        user.getSettings().toggleDarkMode(isDarkMode);
    }

    /**
     * Toggle email notifications
     * @param emailNotification Boolean of mode to be set to
     */
    public void toggleEmailNotification(boolean emailNotification) {
        user.getSettings().toggleEmailNotification(emailNotification);
    }

    /**
     * Sets current lesson to the lesson at that index in the current course
     * @param index Lesson number - 1
     */
    public void setLesson(int index) {
        lesson = course.getLesson(index);
    }

    /**
     * Gets current lesson
     * @return Lesson current Lesson
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Sets current exercise to the exercise at that index in the current lesson
     * @param index
     */
    public void setExercise(int index) {
        exercise = lesson.getExercise(index);
    }

    /**
     * Gets current Exercise
     * @return Exercise current Exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Checks if user answer is correct
     * @param userAnswer userAnswer to be checked
     * @return True if userAnswer is right, else false
     */
    public boolean isCorrect(String userAnswer) {
        return exercise.isCorrect(userAnswer);
    }

    /**
     * Logs out. Writes all changes to json
     */
    public void logOut() {
        this.user = null;
        DataReadWriter.updateUsers(users.getUsers());
    }

    /**
     * Gets the Users settings
     * @return Settings current User Settings
     */
    public Settings getUserSettings() {
        return user.getSettings();
    }

    /**
     * Shows user's current position info
     */
    public void displayPosition(){
        this.course = user.getCourses().keySet().iterator().next();
        int coursePos = user.getCourseProgress(course);
        int coursePerc = coursePos/course.getLessons().size() * 100;
        int lessonNum = coursePos + 1;
        System.out.println("Course: " + course.getLanguage() + "\n" + "Progress: %" + coursePerc + "\n" + "Lesson: " + lessonNum);
    }

    /**
     * Displays the words and phrases the user is struggling with
     */
    public void displayStudyStuff(){
        WordList words = user.getIncorrect();
        for(Word word : words.getWords()){
            System.out.println(word.toString());
        }
    }

    /**
     * Writes the Users incorrect words and phrases to text file for studying
     */
    public void makeStudyFile() {
        WordList words = user.getIncorrect();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("study.txt"))) {
            for (Word word : words.getWords()) {
                writer.write(word.toString());
                writer.newLine(); // Add a new line after each word
            }
            System.out.println("Study material saved to study.txt");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void setUserSettings(boolean sound, boolean email){
        user.setSettings(sound, email);
        update();
    }

    public void update(){
        DataReadWriter.updateUsers(users.getUsers());
    }

    /**
     * Tests the user on only the words and phrases they are strugging with
     */
    public void testStudyStuff() {
        Scanner k = new Scanner(System.in);
        WordList words = user.getIncorrect();
    
        if (words == null || words.getWords() == null || words.getWords().isEmpty()) {
            System.out.println("No words to study.");
            return;
        }
    
        ArrayList<Word> wordsToRemove = new ArrayList<>();
    
        for (Word word : words.getWords()) {
            Exercise exercise = new Translation(word);
            
            System.out.println(exercise.toString());
            String input = k.nextLine();
    
            if (exercise.isCorrect(input)) {
                wordsToRemove.add(word);
                System.out.println("Correct! You answered: " + word.getWord());
            }
        }
    
        for (Word word : wordsToRemove) {
            words.removeWord(word);
        }
    }
    

    /**
     * Goes through User's first course, through lessons asking questions and tracking score
     */
    public void playGame(){
        
        Scanner k = new Scanner(System.in);
        CourseManagerFacade cmf = new CourseManagerFacade();

        HashMap<Course, Integer> userCourses = user.getCourses();

        if (userCourses != null && !userCourses.isEmpty()) {
            Course firstCourse = userCourses.keySet().iterator().next();

            if (firstCourse != null) {
                System.out.println("First course language: " + firstCourse.getLanguage());

                int userProgress = user.getCourseProgress(firstCourse);

                while (userProgress != firstCourse.getLessons().size()){
                    Lesson lesson = firstCourse.getLesson(userProgress);
                    int correct = 0;
                    int asked = 0;
                    System.out.println(lesson.toString());
                    Narrator.playSound(lesson.getIntro()); //UNCOMMENT THIS FOR SOUND
                    
                    while(asked < 5 ){
                        Exercise exercise = lesson.generateExercise();
                        System.out.println(exercise.toString());
                        String input = k.nextLine();
                        if (input.equalsIgnoreCase("quit")){
                            return;
                        }
                        if(exercise.isCorrect(input)){
                            correct++;
                            System.out.println("Correct!");
                        } else{
                            WordList words = user.getIncorrect();
                            Word word = exercise.getWord();
                            words.addWord(word);
                            user.setWordList(words);
                        }
                        asked++;
                    }

                    System.out.println("You got " + correct + "\\" + 5);

                    if ((double)correct/5 > 0.7){
                        userProgress++;
                        userCourses.put(firstCourse, userProgress);
                        System.out.println("You may move on");
                    } else { 
                        System.out.println("YOu did not pass! Try again");
                    }
                }
                    
                }
                
            } else {
            System.out.println("First course is null.");
            } 
        }


// TEST FOR SIGNUP, will not write new user to json yet
    // public static void main(String[] args) {
    //     CourseManagerFacade CMF = new CourseManagerFacade();
    
    //     // Sign up a new user
    //     CMF.signUp("Dw", "dw@gmail.com", "pw");
    //     User user1 = CMF.login("Dw", "pw");
    //     System.out.println("User signed up with email: " + user1.getEmailAddress());
    
    //     // Attempt to log in with the correct credentials
    //     System.out.println("\nAttempting login with correct credentials...");
    //     CMF.login("Dw", "pw");
    //     User loggedInUser = CMF.getUsers().getUser("Dw", "pw");
    
    //     if (loggedInUser != null) {
    //         System.out.println("Login successful. Welcome " + loggedInUser.getUsername() + "!");
    //         System.out.println(loggedInUser.getUsername() + "'s email is: " + loggedInUser.getEmailAddress());
    //     } else {
    //         System.out.println("Login failed.");
    //     }

    //     CMF.logOut();

    // } 

// // // FULL TEST FOR LOGGING IN, ANSWERING QUESTIONS, DOES NOT ATTEMPT TO SAVE
//     public static void main(String[] args) {

//         Scanner k = new Scanner(System.in);
//         CourseManagerFacade cmf = new CourseManagerFacade();

//         User user = cmf.login("asmith", "password");

//         System.out.println("Login successful. Welcome " + user.getUsername() + "!");
//         System.out.println("User's email is " + user.getEmailAddress());

//         cmf.playGame();

//         cmf.logOut();

//     }   
}