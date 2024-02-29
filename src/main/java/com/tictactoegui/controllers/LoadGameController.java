package com.tictactoegui.controllers;

import javafx.fxml.FXML;

public class LoadGameController {
    MainController mainController;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void loadGame() {

    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
