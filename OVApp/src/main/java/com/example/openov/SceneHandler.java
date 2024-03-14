package com.example.openov;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.HashMap;

public class SceneHandler {

    static Stage primaryStage;
    Scene currentScene, homeScene, loginScene, registerScene, homeSceneEnglish;
    private static String css;

    private static HashMap<String, Scene> sceneMap = new HashMap<String, Scene>();

    private static FXMLLoader loginLoader, homeLoader;

    public SceneHandler(Stage stage) throws IOException {
        loadScenes();
        loadHashMap();

        primaryStage = stage;

        currentScene = sceneMap.get("Home");

        css = this.getClass().getResource("styling.css").toExternalForm();
        currentScene.getStylesheets().add(css);

        primaryStage.setMaximized(true);
        primaryStage.setTitle("OV App - Homepage");
        primaryStage.setScene(currentScene);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(SceneHandler.class.getResourceAsStream("bus.png"))));

        primaryStage.show();
    }

    public static void setCurrentScene(String title) throws IOException {
        if(title.equals("Login")){
            LoginController loginController = loginLoader.getController();
            loginController.setHelloControllerInstance(homeLoader.getController());
        }
//        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource(sceneMap.get(title)));
        Scene currentScene = sceneMap.get(title);
        currentScene.getStylesheets().add(css);

        primaryStage.setTitle("OV App - " + title);
        primaryStage.setScene(currentScene);

        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
    }
    public void loadHashMap() {
        sceneMap.put("Home", homeScene);
        sceneMap.put("Login", loginScene);
        sceneMap.put("Registeren", registerScene);
        sceneMap.put("English", homeSceneEnglish);
    }
    public void loadScenes() throws IOException {
        homeLoader = new FXMLLoader(SceneHandler.class.getResource("home-view.fxml"));
        homeScene = new Scene(homeLoader.load());

        loginLoader = new FXMLLoader(SceneHandler.class.getResource("login-view.fxml"));
        loginScene = new Scene(loginLoader.load());

        FXMLLoader registerLoader = new FXMLLoader(SceneHandler.class.getResource("register-view.fxml"));
        registerScene = new Scene(registerLoader.load());

        FXMLLoader homeLoaderEnglish = new FXMLLoader(SceneHandler.class.getResource("english-view.fxml"));
        homeSceneEnglish = new Scene(homeLoaderEnglish.load());


    }
}
