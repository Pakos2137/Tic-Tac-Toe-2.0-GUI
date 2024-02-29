package com.tictactoegui.gameLogic;

import com.tictactoegui.controllers.LoadGameController;
import com.tictactoegui.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadGame {
    Stage loadGameMenuStage;
    int boardSizeValue;

    public LoadGame(int boardSizeValue) {
        this.boardSizeValue = boardSizeValue;
    }
    public void openLoadMenu(MainController mainController) {
        this.loadGameMenuStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/tictactoegui/fxmlFiles/LoadGameMenu.fxml"));
        Pane pane;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainController.setMenuScreen(pane);
        LoadGameController loadGameController;
        loadGameController = fxmlLoader.getController();
        loadGameController.setMainController(mainController);
    }
}
