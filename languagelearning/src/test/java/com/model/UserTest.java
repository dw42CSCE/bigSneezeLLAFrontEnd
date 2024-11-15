package com.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;  
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("testUser", "password123", "john.doe@example.com");
    }

    @Test
    public void testUserInitialization() {
        HashMap<Course, Integer> courses = new HashMap<>();
        User user = new User("testUser", "password123", UUID.randomUUID(),
                             "John", "Doe", "john.doe@example.com", courses, 100, 
                             new Settings(true, false));

        assertEquals("testUser", user.getUsername());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testAddCourse() {
        CourseList courses = CourseList.getInstance();
        Course course = courses.getCourse(0);

        user.addCourse(course);
        
        assertTrue(user.getCourses().containsKey(course));
    }

    @Test
    public void testUpdateUserSettings() {
        user.setSettings(true, true);
        Settings settings = user.getSettings();

        assertTrue(settings.getDarkMode());
        assertTrue(settings.getEmailNotifications());
    }

    @Test
    public void testGettersAndSetters() {
        user.setName("Jane", "Smith");
        user.setEmailAddress("jane.smith@example.com");

        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane.smith@example.com", user.getEmailAddress());
    }

    @Test
    public void testIsMatch() {
        assertTrue(user.isMatch("testUser", "password123"));
        assertFalse(user.isMatch("testUser", "wrongPassword"));
    }
}
