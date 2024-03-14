package com.example.openov;

import com.example.openov.database.models.Users;
import com.example.openov.HelloController.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Button homeButton, registerButton, loginButton;
    @FXML
    private Label LoginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    private static int usrId = 0;

    private HelloController helloControllerInstance;

    public void setHelloControllerInstance(HelloController helloControllerInstance) {
        this.helloControllerInstance = helloControllerInstance;
    }


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
    public void loginButtonOnAction(ActionEvent event) throws SQLException, IOException
    {
        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
            boolean loginValidatie = validateLogin(usernameTextField.getText(), enterPasswordField.getText());
            if (loginValidatie) {

                if (helloControllerInstance != null) {
                    helloControllerInstance.setLogin(true, usernameTextField.getText());
                    helloControllerInstance.setIdUser(usrId);
                }

                SceneHandler.setCurrentScene("Home");

                usernameTextField.setText("");
                enterPasswordField.setText("");

                return;
            }
            LoginMessageLabel.setText("Onjuiste login gegevens");

            return;
        }

        LoginMessageLabel.setText("Please enter username and password");
    }

    public Boolean validateLogin(String gebruikersnaam, String wachtwoord) throws SQLException
    {
        Users users = new Users();
        ResultSet usrByGbr = users.getUserByGbr(gebruikersnaam);

        if (usrByGbr.next()) {
            if (usrByGbr.getString("wachtwoord").equals(wachtwoord)) {
                usrId = usrByGbr.getInt("usr_id");

                return true;
            }
        }

        return false;
    }

    public int getUsrId()
    {
        return usrId;
    }
}
