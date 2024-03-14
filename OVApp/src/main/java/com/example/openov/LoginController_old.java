package com.example.openov;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController_old implements Initializable {

    @FXML
    private Button homeButton, registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        homeButton.setOnMouseClicked(event -> {
            try {
                SceneHandler.setCurrentScene("Home");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        registerButton.setOnMouseClicked(event -> {
            try {
                SceneHandler.setCurrentScene("Registeren");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
