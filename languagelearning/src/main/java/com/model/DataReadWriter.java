package com.model;

import java.util.ArrayList;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;
import java.util.HashMap;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.BufferedReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Paths;

import software.amazon.awssdk.utils.async.InputStreamConsumingPublisher;

@SuppressWarnings("unused")

/**
 * Class that loads and saves application data
 */
public class DataReadWriter extends DataConstants{
    
    /**
     * Method to load users from JSON file into application
     * @return An array list of Users
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
    
        try {
            InputStream inputStream = DataReadWriter.class.getResourceAsStream(DataConstants.USER_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);
            
            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject) peopleJSON.get(i);
                UUID uuid = UUID.fromString((String) personJSON.get(USER_UUID));
                String firstname = (String) personJSON.get(USER_FIRST_NAME);
                String lastname = (String) personJSON.get(USER_LAST_NAME);
                String username = (String) personJSON.get(USER_USER_NAME);
                String email = (String) personJSON.get(USER_EMAIL);
                String password = (String) personJSON.get(USER_PASSWORD);
                int profPts = ((Long) personJSON.get(USER_PROFICIENCY_PTS)).intValue();
                
                JSONObject settingsObj = (JSONObject) personJSON.get(SETTINGS);
                Settings settings = parseSettings(settingsObj);
    
                // Parse user courses
                JSONArray coursesJSON = (JSONArray) personJSON.get(COURSES);
                HashMap<Course, Integer> courses = parseUserCourses(coursesJSON);
                
                // Parse user's words
                JSONArray wordsJSON = (JSONArray) personJSON.get("words");
                ArrayList<String> strings = new ArrayList<>();
                ArrayList<Word> words = new ArrayList<>();
                if (wordsJSON != null) {
                    for (Object wordObj : wordsJSON) {
                        strings.add((String) wordObj);
                    }
                    
                    for (String string : strings){
                        String parts[] = string.split(":");
                        Word word = new Word(parts[0].trim(), parts[1].trim());
                        words.add(word);
                    }
                }
                
                User user = new User(username, password, uuid, firstname, lastname, email, courses, profPts, settings);
                user.getIncorrect().setWords(words);
                
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            System.out.println("No File Found");
            e.printStackTrace();
        }
        return null;
    }
    

    private static Settings parseSettings(JSONObject settings) {
        if (settings == null) {
            return null;
        }

        boolean emailNotif = true;
        boolean darkMode = true;

        if (settings.get(EMAIL_NOTIF) == "false"){
            emailNotif = false;
        }
        if (settings.get(DARK_MODE) == "false"){
            darkMode = true;
        }


        return new Settings(emailNotif,darkMode);
    }

    private static Course parseCourse(JSONObject course) {
        if (course == null) {
            return null;
        }
        UUID uuid = UUID.fromString((String)course.get(USER_UUID));
        int progress = ((Long)course.get(COURSE_PROGRESS)).intValue();
        return null;
        // new Course(uuid, progress);
    }
        

    /**
     * Updates user JSON File from arraylist of Users
     * @param users Array of current Users kept in UserList class
     */
    public static void updateUsers(ArrayList<User> users) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
    
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"uuid\": \"").append(user.getUuid()).append("\",\n");
            jsonBuilder.append("    \"firstname\": \"").append(user.getFirstName()).append("\",\n");
            jsonBuilder.append("    \"lastname\": \"").append(user.getLastName()).append("\",\n");
            jsonBuilder.append("    \"username\": \"").append(user.getUsername()).append("\",\n");
            jsonBuilder.append("    \"email\": \"").append(user.getEmailAddress()).append("\",\n");
            jsonBuilder.append("    \"password\": \"").append(user.getPassword()).append("\",\n");
            jsonBuilder.append("    \"ProfPoints\": ").append(user.getProfPoints()).append(",\n");
    
            // Settings
            jsonBuilder.append("    \"settings\": {\n");
            jsonBuilder.append("      \"emailNotifications\": \"").append(user.getSettings().getEmailNotifications()).append("\",\n");
            jsonBuilder.append("      \"darkMode\": \"").append(user.getSettings().getDarkMode()).append("\"\n");
            jsonBuilder.append("    },\n");
    
            // Courses
            jsonBuilder.append("    \"courses\": [\n");
            if (user.getCourses() != null) {
                for (Map.Entry<Course, Integer> entry : user.getCourses().entrySet()) {
                    UUID courseUuid = entry.getKey().getUuid();
                    int progress = entry.getValue();
                    
                    jsonBuilder.append("      {\n");
                    jsonBuilder.append("        \"uuid\": \"").append(courseUuid).append("\",\n");
                    jsonBuilder.append("        \"progress\": ").append(progress).append("\n");
                    jsonBuilder.append("      }");
                    
                    // Add comma if not the last entry
                    if (!entry.equals(user.getCourses().entrySet().toArray()[user.getCourses().size() - 1])) {
                        jsonBuilder.append(",");
                    }
                    jsonBuilder.append("\n");
                }
            }
            jsonBuilder.append("    ],\n");
    
            // Words
            jsonBuilder.append("    \"words\": [\n");
            if (user.getIncorrect() != null && !user.getIncorrect().getWords().isEmpty()) {
                for (int j = 0; j < user.getIncorrect().getWords().size(); j++) {
                    String word = user.getIncorrect().getWords().get(j).toString();
                    jsonBuilder.append("      \"").append(word).append("\"");
    
                    // Add comma if not the last entry
                    if (j < user.getIncorrect().getWords().size() - 1) {
                        jsonBuilder.append(",");
                    }
                    jsonBuilder.append("\n");
                }
            }
            jsonBuilder.append("    ]\n"); // Closing for words array
    
            jsonBuilder.append("  }");
    
            if (i < users.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
    
        jsonBuilder.append("]");
    
        // Write the JSON string to the file
        try {
            URI url = DataReadWriter.class.getResource(USER_FILE_NAME).toURI();
            FileWriter writer = new FileWriter(url.getPath());

            writer.write(jsonBuilder.toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets courses from JSON File
     * @return ArrayList of all courses in JSON
     */
    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
    
        try {
            InputStream inputStream = DataReadWriter.class.getResourceAsStream(DataConstants.COURSE_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray coursesJSON = (JSONArray) new JSONParser().parse(reader);
    
            for (int i = 0; i < coursesJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) coursesJSON.get(i);
    
                // Extract course details
                Language language = Language.valueOf(((String) courseJSON.get("language")).toUpperCase());
                UUID uuid = UUID.fromString((String) courseJSON.get(COURSE_UUID));
                int progress = ((Long)courseJSON.get(COURSE_PROGRESS)).intValue();
    
                // Assuming getLessons() is a method that retrieves lessons for each course
                ArrayList<Lesson> lessons = getLessons((JSONArray) courseJSON.get(LESSONS));
    
                // Create the course and add it to the list
                courses.add(new Course(language, uuid, lessons));
            }
    
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }

    /**
     * Parses lessons from JSON
     * @param lessonsJSON Object lessonsJSON from a Course in JSON
     * @return ArrayList of Lessons
     */
    private static ArrayList<Lesson> getLessons(JSONArray lessonsJSON){

        ArrayList<Lesson> lessons = new ArrayList<>();

        for (int i = 0; i < lessonsJSON.size(); i++){

            JSONObject lessonJSON = (JSONObject) lessonsJSON.get(i);

            String subject = (String)lessonJSON.get(SUBJECT);
            int progress = ((Long)lessonJSON.get(COURSE_PROGRESS)).intValue();
            String intro = (String)lessonJSON.get(INTRO);
            // ArrayList<Exercise> exercises = getExercises((JSONArray) lessonJSON.get(EXERCISES));
            Word[] words = getKeyWords((JSONArray) lessonJSON.get(KEYWORDS));
            Phrase[] phrases = getKeyPhrases((JSONArray) lessonJSON.get(KEYPHRASES));


            lessons.add(new Lesson(subject, intro, words, phrases, progress));
        }
        return lessons;
    }

    /**
     * 
     * @param exercisesJSON
     * @return
     */
    // private static ArrayList<Exercise> getExercises(JSONArray exercisesJSON){
    //     ArrayList<Exercise> exercises = new ArrayList<>();

    //     for (int i = 0; i <exercisesJSON.size(); i++){
    //         JSONObject exerciseJSON = (JSONObject) exercisesJSON.get(i);

    //         String question = (String)exerciseJSON.get(QUESTION);
    //         String type = (String)exerciseJSON.get(TYPE);
    //         String options = (String)exerciseJSON.get(OPTIONS);
    //         String answer = (String)exerciseJSON.get(ANSWER);

    //         exercises.add(new Exercise(question,type, options, answer));
    //     }

    //     return exercises;
    // }

    /**
     * 
     * @param keywordsJSON
     * @return
     */
    private static Word[] getKeyWords(JSONArray keywordsJSON){
        Word[] words = new Word[keywordsJSON.size()];

        for (int i = 0; i < keywordsJSON.size(); i++) {
            String[] data = ((String)keywordsJSON.get(i)).split(":");
            String word = data[0].trim();
            String meaning = data[1].trim();
            words[i] = new Word(word, meaning);
        }
        return words;
    }

    /**
     * 
     * @param keyphrasesJSON
     * @return
     */
    private static Phrase[] getKeyPhrases(JSONArray keyphrasesJSON){
        Phrase[] phrases = new Phrase[keyphrasesJSON.size()];

        for (int i = 0; i < keyphrasesJSON.size(); i++) {
            String[] data = ((String)keyphrasesJSON.get(i)).split(":");
            String phrase = data[0].trim();
            String meaning = data[1].trim();
            phrases[i] = new Phrase(phrase, meaning);
        }
        return phrases;
    }

    /**
     * 
     * @param userCourses
     * @return
     */
    private static HashMap<Course, Integer> parseUserCourses(JSONArray userCourses) {
        HashMap<Course, Integer> courses = new HashMap<>();
        CourseList courselist = CourseList.getInstance();  // Ensure CourseList is populated before this step
    
        if (userCourses != null) {
            for (int i = 0; i < userCourses.size(); i++) {
                JSONObject courseJSON = (JSONObject) userCourses.get(i);
                UUID uuid = UUID.fromString(((String) courseJSON.get("uuid")).trim());
    
                // Fetch the course using the UUID from CourseList
                Course course = courselist.getCourse(uuid);
                if (course != null) {
                    int progress = ((Long) courseJSON.get(COURSE_PROGRESS)).intValue();
                    courses.put(course, progress);
                } else {
                    System.out.println("Course with UUID " + uuid + " not found in CourseList.");
                }
            }
        } else {
            System.out.println("userCourses is null.");
        }
        return courses;
    }
    public static void main(String[] args) {
        // Initialize the test class and call the getUsers method

        String path = Paths.get("languagelearning/src/resources/UserInfo.json").toAbsolutePath().toString();
        System.out.println("Absolute Path: " + path); // To confirm the path
        ArrayList<User> users = DataReadWriter.getUsers();

        // Check if the users list was populated and print the result
        if (users != null) {
            System.out.println("Number of users loaded: " + users.size());
            for (User user : users) {
                System.out.println("User: " + user.getUsername());
            }
        } else {
            System.out.println("Failed to load users. Please check file path and contents.");
        }
    }
}