package com.tictactoegui.controllers;

import com.tictactoegui.gameLogic.SaveGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveMenuController {
    SaveGame saveGame;
    @FXML
    TextField saveNameField;
    @FXML
    Label text;
    @FXML
    private void saveButton() throws IOException {
        if(3 <= saveNameField.getLength()) {
            Path path = Paths.get("src/main/resources/save games/" + saveNameField.getText());
            if(!Files.exists(path)) {
                saveGame.addNewGameSave(saveNameField.getText());
            } else {
                text.setText("Taki zapis już istnieje.");
                text.setTextFill(Color.RED);
            }
        } else {
            text.setText("Minimum 3 znaki.");
            text.setTextFill(Color.RED);
        }
    }
    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }
}
