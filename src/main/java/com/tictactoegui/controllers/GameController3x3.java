package com.tictactoegui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameController3x3 {
    int column;
    int row;
    @FXML
    private Button button;
    MainController mainController;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private GridPane gameBoard3x3Pane;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                column = GridPane.getColumnIndex(button);
                row = GridPane.getRowIndex(button);
            }
        });
    }
    @FXML
    private void buttonClicked() {
        System.out.println(column + " : " + row);
    }
}
