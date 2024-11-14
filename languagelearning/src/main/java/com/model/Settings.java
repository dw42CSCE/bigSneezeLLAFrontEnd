package com.model;

// Settings Class
public class Settings {
    private boolean emailNotifications = false;
    private boolean isDarkMode = false;

    /**
     * Parameterized Constructor
     * @param emailNotif True if on, False if off
     * @param darkMode True if on, False if off
     */
    public Settings(boolean emailNotif, boolean darkMode) {
        this.emailNotifications = emailNotif;
        this.isDarkMode = darkMode;
    }
    
    /**
     * Changes darkmode
     * @param isDarkMode True if on, False if off
     */
    public void toggleDarkMode(boolean isDarkMode){
        this.isDarkMode = isDarkMode;
    }

    /**
     * Gets current darkmode setting
     * @return Boolean current darkmode setting
     */
    public boolean getDarkMode(){
        return this.isDarkMode;
    }

    /**
     * Changes emailNotifications
     * @param emailNotifications True if on, False if off
     */
    public void toggleEmailNotification(boolean emailNotification){
        this.emailNotifications = emailNotification;
    }

    /**
     * Gets current email notif settings
     * @return Boolean current email notif settings
     */
    public boolean getEmailNotifications(){
        return this.emailNotifications;
    }
    
    public String toString(){
        return ("Dark Mode: " + this.isDarkMode + "\nEmail Notifications: " + this.emailNotifications);
    }
}
