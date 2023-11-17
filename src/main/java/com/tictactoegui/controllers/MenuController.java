package com.tictactoegui.controllers;

import javafx.fxml.FXML;

public class MenuController {
    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void quitGame() {
        System.exit(0);
    }
    @FXML
    private void loadSaveMenu() {

    }
}
