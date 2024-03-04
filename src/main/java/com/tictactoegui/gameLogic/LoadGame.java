package com.tictactoegui.gameLogic;

import com.tictactoegui.controllers.GameController10x10;
import com.tictactoegui.controllers.GameController3x3;
import com.tictactoegui.controllers.LoadGameController;
import com.tictactoegui.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LoadGame {
    GameController3x3 gameController3x3;
    GameController10x10 gameController10x10;
    Board board;
    MainController mainController;
    private String boardReference;
    List<String> valuesOfBoard;
    Stage loadGameMenuStage;
    int boardSizeValue;

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
        loadGameController.setMainController(mainController,this);
    }
    public void loadGame(String name,MainController mainController) {
        this.mainController = mainController;

        Path saveToLoad = Paths.get("src/main/resources/save games/" + name);

        try {
            valuesOfBoard = Files.readAllLines(saveToLoad);
            setBoardSizeValue(Integer.parseInt(valuesOfBoard.get(0)));
            createGameWithLoadedValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGameWithLoadedValues() {
        switch (getBoardSizeValue()) {
            case 3:
                boardReference = "/com/tictactoegui/fxmlFiles/GameScreen3x3.fxml";
                break;
            case 10:
                boardReference = "/com/tictactoegui/fxmlFiles/GameScreen10x10.fxml";
                break;
        }
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(boardReference));
        Pane pane;
        try {
            pane = loader.load();

            if(getBoardSizeValue() == 3) {
                gameController3x3 = loader.getController();
                gameController3x3.setMainController(mainController);
                gameController3x3.createBoard(getBoardSizeValue(),'X');
                gameController3x3.setPlayerType('C');
                gameController3x3.loadGame(valuesOfBoard);
            } else if (getBoardSizeValue() == 10) {
                gameController10x10 = loader.getController();
                gameController10x10.setMainController(mainController);
                gameController10x10.createBoard(getBoardSizeValue(),'X');
                gameController10x10.setPlayerType('C');
            }
            mainController.setMenuScreen(pane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getBoardSizeValue() {
        return boardSizeValue;
    }

    public void setBoardSizeValue(int boardSizeValue) {
        this.boardSizeValue = boardSizeValue;
    }
}
