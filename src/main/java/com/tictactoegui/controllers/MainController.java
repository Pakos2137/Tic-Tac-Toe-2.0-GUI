package com.tictactoegui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() throws IOException {
        loadMenuScreen();
    }
    public void loadMenuScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/tictactoegui/fxmlFiles/MenuScene.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setMenuScreen(pane);
    }
    public void setMenuScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
}
