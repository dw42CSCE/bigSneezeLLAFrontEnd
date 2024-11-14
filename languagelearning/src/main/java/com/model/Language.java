package com.model;

public enum Language {
    SPANISH("Spanish"),
    FRENCH("French"),
    JAPANESE("Japanese");

    private String label;

    private Language(String label) {
        this.label = label;
    }
}
