package com.tictactoegui.controllers;

import com.tictactoegui.gameLogic.LoadGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.File;

public class LoadGameController {
    LoadGame loadGame;
    @FXML
    ListView<String> savesList;

    MainController mainController;
    @FXML
    private Label mainText;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void loadGame() {
        if(!savesList.getItems().isEmpty() && savesList.getSelectionModel().getSelectedItem() != null) {
            String name = savesList.getSelectionModel().getSelectedItem();
            loadGame.loadGame(name,mainController);
        }
    }
    @FXML
    private void deleteSave() {
        String name = savesList.getSelectionModel().getSelectedItem();
        savesList.getItems().remove(name);
        File f = new File("src/main/resources/save games/" + name);
        f.delete();

    }
    public void initialize() {
        File resourcesFolder = new File("src/main/resources/save games");
        File[] files = resourcesFolder.listFiles();
        for (File file : files) {
            savesList.getItems().add(file.getName());
        }
        if(files.length == 0) {
            mainText.setText("Brak Zapisów Do Wczytania");
        }
    }
    public void setMainController(MainController mainController, LoadGame loadGame) {
        this.loadGame = loadGame;
        this.mainController = mainController;
    }
}
