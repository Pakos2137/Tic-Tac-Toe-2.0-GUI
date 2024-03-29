package com.tictactoegui.controllers;

import com.tictactoegui.gameLogic.LoadGame;
import com.tictactoegui.gameLogic.LoadingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController extends LoadingMethods {
    GameController3x3 gameController3x3;
    GameController10x10 gameController10x10;
    MainController mainController;
    @FXML
    private Slider boardSizeSlider;
    @FXML
    private Slider playerTypeSlider;
    @FXML
    private Button takeXButton;
    @FXML
    private Button takeOButton;
    @FXML
    private Label startText;
    private char firstMoveChar = 'X';

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void quitGame() {
        System.exit(0);
    }
    @FXML
    private void takeX() {
        takeOButton.setDisable(false);
        takeXButton.setDisable(true);
        takeOButton.setFocusTraversable(false);
        startText.setText("Zaczyna : X");
        setFirstMoveChar('X');
    }
    @FXML
    private void takeO() {
        takeXButton.setDisable(false);
        takeOButton.setDisable(true);
        takeXButton.setFocusTraversable(false);
        startText.setText("Zaczyna : O");
        setFirstMoveChar('O');
    }
    @FXML
    private void loadSaveMenu() {
        LoadGame loadGame = new LoadGame();
        loadGame.openLoadMenu(mainController);
    }
    @FXML
    private void startGame() {
        String boardReference = getReferenceToFXML(getBoardSizeValue());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(boardReference));
        Pane pane;
        try {
            pane = loader.load();
            if(getBoardSizeValue() == 3) {
                gameController3x3 = loader.getController();
                gameController3x3.setValues(mainController,firstMoveChar, sliderToPlayerType(),true);
            } else if (getBoardSizeValue() == 10) {
                gameController10x10 = loader.getController();
                gameController10x10.setValues(mainController,firstMoveChar, sliderToPlayerType(),true);
            }
            mainController.setMenuScreen(pane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getBoardSizeValue() {
        return (int) boardSizeSlider.getValue();
    }

    public char sliderToPlayerType() {
        char playerType = 0;
        if(playerTypeSlider.getValue() == 0.0) {
            playerType = 'C';
        }
        if(playerTypeSlider.getValue() == 1.0) {
            playerType = 'P';
        }
        return playerType;
    }

    public void setFirstMoveChar(char firstMoveChar) {
        this.firstMoveChar = firstMoveChar;
    }
}
