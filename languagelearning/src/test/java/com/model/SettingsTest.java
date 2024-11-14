package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SettingsTest {

    private Settings settings;

    @BeforeEach
    public void setUp() {
        // Initialize Settings object with default values
        settings = new Settings(false, false);
    }

    @Test
    public void testConstructor() {
        Settings settings = new Settings(true, true);
        assertTrue(settings.getEmailNotifications(), "Email notifications should be enabled.");
        assertTrue(settings.getDarkMode(), "Dark mode should be enabled.");
    }

    @Test
    public void testToggleDarkMode() {
        // Toggle dark mode to true
        settings.toggleDarkMode(true);
        assertTrue(settings.getDarkMode(), "Dark mode should be enabled.");

        // Toggle dark mode to false
        settings.toggleDarkMode(false);
        assertFalse(settings.getDarkMode(), "Dark mode should be disabled.");
    }

    @Test
    public void testToggleEmailNotification() {
        // Toggle email notifications to true
        settings.toggleEmailNotification(true);
        assertTrue(settings.getEmailNotifications(), "Email notifications should be enabled.");

        // Toggle email notifications to false
        settings.toggleEmailNotification(false);
        assertFalse(settings.getEmailNotifications(), "Email notifications should be disabled.");
    }

    @Test
    public void testToString() {
        settings.toggleDarkMode(true);
        settings.toggleEmailNotification(true);
        String expectedOutput = "Dark Mode: true\nEmail Notifications: true";
        assertEquals(expectedOutput, settings.toString(), "toString() should return the correct string representation.");
    }
}
