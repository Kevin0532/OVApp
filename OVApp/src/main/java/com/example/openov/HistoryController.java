package com.example.openov;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HistoryController implements Initializable {

    @FXML
    private Label historyLabel;

    @FXML
    private Button historyButton;


//    public void historyTracker(ActionEvent event) {
//        historyButton.setOnMouseClicked(event -> {
//            historyLabel.setText("Test");
//        });
//    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyButton.setOnMouseClicked(event -> {
            historyLabel.setText("Test");
        });
    }


}
