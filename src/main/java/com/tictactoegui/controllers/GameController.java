package com.tictactoegui.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GameController {
    MainController mainController;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private GridPane gameBoard3x3Pane;

    @FXML
    private void test() {
        System.out.println(gameBoard3x3Pane.getLayoutX() + " " + gameBoard3x3Pane.getLayoutY());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
