package com.tictactoegui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    MainController mainController;
    @FXML
    private Slider boardSizeValue;
    @FXML
    private Slider playerTypeSlider;

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
    @FXML
    private void startGame() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/tictactoegui/fxmlFiles/GameScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
            GameController gameController = loader.getController();
            gameController.setMainController(mainController);
            mainController.setMenuScreen(pane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getBoardSizeValue() {
        return (int) boardSizeValue.getValue();
    }

    public char getPlayerType() {
        char playerType = 0;
        if(playerTypeSlider.getValue() == 0.0) {
            playerType = 'K';
        }
        if(playerTypeSlider.getValue() == 1.0) {
            playerType = 'P';
        }
        return playerType;
    }
}
