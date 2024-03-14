package com.example.openov;

import com.example.openov.database.models.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private Button homeButton, registerButton;
    @FXML
    private TextField emailInput, usernameInput, passwordInput;
    @FXML
    private Label registerMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButton.setOnMouseClicked(event -> {
            try {
                SceneHandler.setCurrentScene("Home");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void register() throws IOException, SQLException
    {
        if (!emailInput.getText().isBlank() && !usernameInput.getText().isBlank() && !passwordInput.getText().isBlank()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("gebruikersnaam", usernameInput.getText());
            data.put("wachtwoord", passwordInput.getText());

            Users users = new Users();
            users.addUser(data);

            emailInput.setText("");
            usernameInput.setText("");
            passwordInput.setText("");

            SceneHandler.setCurrentScene("Login");
        }

        registerMessage.setText("Alle velden zijn vereist.");
    }
}
