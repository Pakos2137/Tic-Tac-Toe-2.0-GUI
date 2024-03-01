package com.tictactoegui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;

public class LoadGameController {
    @FXML
    ListView<String> savesList;
    MainController mainController;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void loadGame() {
        String name = savesList.getSelectionModel().getSelectedItem();
        System.out.println(name);
    }
    public void initialize() {
        File resourcesFolder = new File("src/main/resources/save games");
        File[] files = resourcesFolder.listFiles();
        for (File file : files) {
            savesList.getItems().add(file.getName());
        }
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
