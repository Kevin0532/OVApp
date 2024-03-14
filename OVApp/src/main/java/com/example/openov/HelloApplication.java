package com.example.openov;

import com.example.openov.database.models.Users;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        new SceneHandler(stage);
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}