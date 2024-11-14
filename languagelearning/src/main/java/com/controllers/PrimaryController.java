package com.controllers;

import java.io.IOException;

import com.language.language.App;

import javafx.fxml.FXML;
import com.language.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
