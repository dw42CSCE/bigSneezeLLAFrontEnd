package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.language.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}