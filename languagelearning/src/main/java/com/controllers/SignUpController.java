package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.language.App;

public class SignUpController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}