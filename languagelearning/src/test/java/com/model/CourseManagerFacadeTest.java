package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseManagerFacadeTest {
    
    private User user;
    private CourseManagerFacade CMF;

    @BeforeEach
    public void setUp() {
        // Initialize CourseManagerFacade object
        CMF = new CourseManagerFacade();
    }

    @Test
    public void testLoginNonUser() {
        user = CMF.login("hello", "goodbye");
        assertNull(user);
    }

    @Test
    public void testLoginExistingUser() {
        user = CMF.login("ttomacka", "password");
        assertNotNull(user);
    }

    @Test
    public void testLoginNull() {
        user = CMF.login(null, null);
        assertNull(user);
    }

    @Test
    public void testSignUpNullUsername() {
        user = CMF.signUp(null, "super@gmail.com", "password");
        assertNull(user);
    }

    @Test
    public void testSignUpnullEmail() {
        user = CMF.signUp("TheBroMan", null, "password");
        assertNull(user);
    }

    @Test
    public void testSignUpNullPassword() {
        user = CMF.signUp("TheBroMan", "super@gmail.com", null);
        assertNull(user);
    }

    @Test
    public void testSignUpNonUserAndNonEmail() {
        user = CMF.signUp("TheBroMan", "super@gmail.com", "password");
        assertNotNull(user);
    }

    @Test
    public void testSignUpExistingUsername() {
        user = CMF.signUp("ttomacka", "super@gmail.com", "password");
        assertNull(user);
    }

    @Test
    public void testSignUpExistingEmail() {
        user = CMF.signUp("TheBroMan", "ex@gmail.com", "password");
        assertNull(user);
    }
}
