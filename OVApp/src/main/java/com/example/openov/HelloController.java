package com.example.openov;

import com.example.openov.database.models.Favorieten;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import findShortestPath.RouteFinder;

public class HelloController implements Initializable {

    @FXML
    private ChoiceBox<String> departureBox, arrivalBox;

    @FXML
    private Spinner<Integer> comboBox1, comboBox2;

    @FXML
    private BorderPane myPane1, myPane3, myPane2;

    @FXML
    private AnchorPane resultPane;

    @FXML
    private Button planButton, loginButton, historyButton, englishButton, favoriteButton, addFavoriteButton, switchButton;

    @FXML
    private VBox searchBox;


    @FXML
    private Label resultText, departureText1, departureText2, departureText3,
            startingTime1, startingTime2, startingTime3,
            totalDurationText1, totalDurationText2, totalDurationText3, historyLabel;

    private String blueStyle = "-fx-background-color: #0084c5; -fx-background-radius: 20px; -fx-border-radius: 10px; -fx-border-color: white; -fx-border-width: 5px;";
    private String pressedStyle = "-fx-background-color: #58b6d9; -fx-background-radius: 20px; -fx-border-radius: 10px; -fx-border-color: #18648f; -fx-border-width: 5px;";
    private String planPressedStyle = "-fx-background-color: #a72b28; -fx-background-radius: 20px; -fx-border-radius: 10px; -fx-border-color: white; -fx-border-width: 5px; -fx-text-fill: white";

    private String laterTime, halfHourLater, hourLater, arrivingTime1, arrivingTime2, arrivingTime3, totalDuration;

    private LocalTime laterTimeTemp, halfHourLaterTemp, hourLaterTemp;

    private ArrayList<Object[]> resultRoute;

    private ArrayList<String> historyRoutes = new ArrayList<String>();
    private ArrayList<String> favorites = new ArrayList<String>();

    private boolean login = false;


    private int idUser;


    private boolean nederlands = true;

    private boolean favoriteButtonOff = false;

    private String username = "Login";


    private final String[] locations = {
            "'s-Hertogenbosch",
            "Amersfoort Centraal",
            "Amersfoort Schothorst",
            "Amsterdam Amstel",
            "Amsterdam Bijlmer Arena",
            "Amsterdam Centraal",
            "Amsterdam Muiderpoort",
            "Amsterdam Science Park",
            "Amsterdam Sloterdijk",
            "Baarn",
            "Bilthoven",
            "Bovenkarspel Flora",
            "Bovenkarspel-Grootebroek",
            "Bussum Zuid",
            "Den Dolder",
            "Den Haag Centraal",
            "Diemen",
            "Dordrecht",
            "Eindhoven Centraal",
            "Enkhuizen",
            "Gouda",
            "Heerenveen",
            "Hilversum",
            "Hilversum Media Park",
            "Hoogkarspel",
            "Hoorn",
            "Hoorn Kersenboogerd",
            "Leeuwarden",
            "Maastricht",
            "Meppel",
            "Naarden Bussum",
            "Roermond",
            "Rotterdam Alexander",
            "Rotterdam Blaak",
            "Rotterdam Centraal",
            "Sittard",
            "Soest",
            "Soest Zuid",
            "Soestdijk",
            "Steenwijk",
            "Utrecht Centraal",
            "Utrecht Overvecht",
            "Weert",
            "Weesp",
            "Zwolle"
    };

    public void timeDecider(String inputTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m");
        LocalTime originalTime = LocalTime.parse(inputTime, formatter);

        // Add a random time between 2 and 30 minutes
        laterTimeTemp = originalTime.plusMinutes((long) (Math.random() * 19) + 2);
        laterTime = String.valueOf(laterTimeTemp);

        // Add half an hour
        halfHourLaterTemp = laterTimeTemp.plusMinutes(30);
        halfHourLater = String.valueOf(halfHourLaterTemp);

        // Add an hour
        hourLaterTemp = laterTimeTemp.plusHours(1);
        hourLater = String.valueOf(hourLaterTemp);
    }

    public void arrivingTimeSetter(String duration) {
        arrivingTime1 = String.valueOf(laterTimeTemp.plusMinutes(Integer.parseInt(duration)));
        arrivingTime2 = String.valueOf(halfHourLaterTemp.plusMinutes(Integer.parseInt(duration)));
        arrivingTime3 = String.valueOf(hourLaterTemp.plusMinutes(Integer.parseInt(duration)));
    }

    private void resetPaneStyles() {
        myPane1.setStyle(blueStyle);
        myPane2.setStyle(blueStyle);
        myPane3.setStyle(blueStyle);

        departureText1.setStyle("-fx-text-fill: white");
        departureText2.setStyle("-fx-text-fill: white");
        departureText3.setStyle("-fx-text-fill: white");

        startingTime1.setStyle("-fx-text-fill: white");
        startingTime2.setStyle("-fx-text-fill: white");
        startingTime3.setStyle("-fx-text-fill: white");

        totalDurationText1.setStyle("-fx-text-fill: white");
        totalDurationText2.setStyle("-fx-text-fill: white");
        totalDurationText3.setStyle("-fx-text-fill: white");
    }

    private void resetPlanButton() {
        planButton.setStyle(blueStyle);
    }
    private void resetAddButton() {
        addFavoriteButton.setStyle("-fx-background-color: white; -fx-background-radius: 13; -fx-border-color: #cfcfcf; -fx-border-width: 5; -fx-border-radius: 10; -fx-text-fill: #a72b28;");
    }

    private void displayResult(LocalTime time) {
        if (resultRoute != null) {
            String finalText = "";
            LocalTime temp = time;

            for (Object[] station : resultRoute) {
                temp = temp.plusMinutes((int) station[1]);
                if (station[0] != "Total time") {
                    finalText += String.valueOf(temp) + " - " + station[0] + "\n";
                }
            }
            resultText.setText(finalText);
        }
    }
    private void displayHistory(){
        if (historyRoutes != null) {
            String finalText = "";

            for (String route : historyRoutes){
                finalText = route + "\n\n" + finalText;
            }
            resultText.setText(finalText);
        }
        else {
            resultText.setText("No history found");
        }
    }
    private void displayFavorites() throws SQLException {
        Favorieten favorieten = new Favorieten();
        try {
            ResultSet resultSet = favorieten.getFavorieten(idUser);

            String finalText = "";

            finalText += resultSet.getString("trajectnaam") + "\n\n";

            while (resultSet.next()) {
                String trajectnaam = resultSet.getString("trajectnaam");
                finalText += trajectnaam + "\n\n";
            }

            resultText.setText(finalText);
        }catch (Exception e){
            resultText.setText("No favorites added");
        }

    }
    private void cleanBoxes(){
        departureBox.valueProperty().set(null);
        arrivalBox.valueProperty().set(null);

        departureText1.setText(null);
        departureText2.setText(null);
        departureText3.setText(null);

        startingTime1.setText(null);
        startingTime2.setText(null);
        startingTime3.setText(null);

        totalDurationText1.setText(null);
        totalDurationText2.setText(null);
        totalDurationText3.setText(null);

        favoriteButtonOff = false;
        addFavoriteButton.setVisible(favoriteButtonOff);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RouteFinder routeFinder = new RouteFinder();
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        departureBox.getItems().addAll(locations);
        arrivalBox.getItems().addAll(locations);

        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        hourFactory.setValue(currentHour);
        minuteFactory.setValue(currentMinute);

        comboBox1.setValueFactory(hourFactory);
        comboBox2.setValueFactory(minuteFactory);

        loginButton.setText(username);


        myPane1.setOnMouseClicked(event -> {
            resetPaneStyles();
            myPane1.setStyle(pressedStyle);

            departureText1.setStyle("-fx-text-fill: black");
            startingTime1.setStyle("-fx-text-fill: black");
            totalDurationText1.setStyle("-fx-text-fill: black");

            displayResult(laterTimeTemp);
        });

        myPane2.setOnMouseClicked(event -> {
            resetPaneStyles();
            myPane2.setStyle(pressedStyle);

            departureText2.setStyle("-fx-text-fill: black");
            startingTime2.setStyle("-fx-text-fill: black");
            totalDurationText2.setStyle("-fx-text-fill: black");

            displayResult(halfHourLaterTemp);
        });

        myPane3.setOnMouseClicked(event -> {
            resetPaneStyles();
            myPane3.setStyle(pressedStyle);

            departureText3.setStyle("-fx-text-fill: black");
            startingTime3.setStyle("-fx-text-fill: black");
            totalDurationText3.setStyle("-fx-text-fill: black");

            displayResult(hourLaterTemp);
        });


        // Plan Button - Veranderd van Kleur van Blauw naar Rood en terug in 0.5 seconden
        planButton.setOnMouseClicked(event -> {
            planButton.setStyle(planPressedStyle);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // Vertraging van halve seconden
            pause.setOnFinished(e -> resetPlanButton());
            pause.play();

            resultRoute = routeFinder.RouteSearcher(departureBox.getValue(), arrivalBox.getValue());
            Object[] totalTime = resultRoute.get(resultRoute.size() - 1);
            String duration = String.valueOf((int) totalTime[1]);
            totalDuration = duration + " minuten";

            totalDurationText1.setText(totalDuration);
            totalDurationText2.setText(totalDuration);
            totalDurationText3.setText(totalDuration);

            departureText1.setText(departureBox.getValue() + " ->\n" + arrivalBox.getValue());
            departureText2.setText(departureBox.getValue() + " ->\n" + arrivalBox.getValue());
            departureText3.setText(departureBox.getValue() + " ->\n" + arrivalBox.getValue());

            timeDecider(comboBox1.getValue() + ":" + comboBox2.getValue());
            arrivingTimeSetter(duration);

            startingTime1.setText(laterTime + " - " + arrivingTime1);
            startingTime2.setText(halfHourLater + " - " + arrivingTime2);
            startingTime3.setText(hourLater + " - " + arrivingTime3);

//            resultText.setText("Kies een optie");
            displayResult(laterTimeTemp);
            resetPaneStyles();
            myPane1.setStyle(pressedStyle);

            departureText1.setStyle("-fx-text-fill: black");
            startingTime1.setStyle("-fx-text-fill: black");
            totalDurationText1.setStyle("-fx-text-fill: black");

            historyRoutes.add(laterTime + " - " + arrivingTime1 + "\n" + departureBox.getValue() + " -> " + arrivalBox.getValue());

            if (login) {
                favoriteButtonOff = true;
                addFavoriteButton.setVisible(favoriteButtonOff);
            }
        });

        loginButton.setOnMouseClicked(event -> {
            if(!login){
                try {
                    SceneHandler.setCurrentScene("Login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        historyButton.setOnMouseClicked(event ->{
            cleanBoxes();
            displayHistory();
        });

        englishButton.setOnMouseClicked(event -> {
            try {
                if (nederlands) {
                    SceneHandler.setCurrentScene("English");
                    this.nederlands=false;
                } else {
                    SceneHandler.setCurrentScene("Home");
                    this.nederlands=true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void switchButtonOnAction(ActionEvent event) {
        String temp_departure = departureBox.getValue();
        String temp_arrival = arrivalBox.getValue();

        departureBox.setValue(temp_arrival);
        arrivalBox.setValue(temp_departure);
    }
    public void addFavoriteButtonOnAction(ActionEvent event) throws SQLException {
        addFavoriteButton.setStyle(planPressedStyle);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // Vertraging van halve seconden
        pause.setOnFinished(e -> {
            resetAddButton();
            favoriteButtonOff = false;
            addFavoriteButton.setVisible(favoriteButtonOff);
        });
        pause.play();

        Favorieten favorieten = new Favorieten();
        favorieten.addFavoriet(idUser, departureBox.getValue() + " -> " + arrivalBox.getValue() + "\n" + laterTime + " - " + arrivingTime1);
    }
    public void favoriteButtonOnAction(ActionEvent event) throws SQLException {
        displayFavorites();
        cleanBoxes();
    }

    public void setLogin(boolean login, String username) {
        this.username = username;
        this.login = login;
        loginButton.setText(this.username);
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}