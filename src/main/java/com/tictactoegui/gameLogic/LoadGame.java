package com.tictactoegui.gameLogic;

import com.tictactoegui.controllers.GameController10x10;
import com.tictactoegui.controllers.GameController3x3;
import com.tictactoegui.controllers.LoadGameController;
import com.tictactoegui.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LoadGame extends LoadingMethods {
    GameController3x3 gameController3x3;
    GameController10x10 gameController10x10;
    MainController mainController;
    List<String> valuesOfBoard;
    Stage loadGameMenuStage;
    private int boardSizeValue;
    private char enemyType;
    private String actualMove;
    private int moveCounter;

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
        File directory = new File("src/main/resources/save games");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path saveToLoad = Paths.get("src/main/resources/save games/" + name);

        try {
            valuesOfBoard = Files.readAllLines(saveToLoad);
            setBoardSizeValue(Integer.parseInt(valuesOfBoard.get(0)));
            setMoveCounter(Integer.parseInt(valuesOfBoard.get(1)));
            setActualMove(valuesOfBoard.get(2));
            setEnemyType(valuesOfBoard.get(3).charAt(0));
            createGameWithLoadedValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGameWithLoadedValues() {
        String boardReference = getReferenceToFXML(getBoardSizeValue());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(boardReference));
        Pane pane;
        try {
            pane = loader.load();

            if(getBoardSizeValue() == 3) {
                gameController3x3 = loader.getController();
                gameController3x3.loadGame(valuesOfBoard,moveCounter);
                gameController3x3.setValues(mainController,actualMove.charAt(0),enemyType,false);
            } else if (getBoardSizeValue() == 10) {
                gameController10x10 = loader.getController();
                gameController10x10.loadGame(valuesOfBoard,moveCounter);
                gameController10x10.setValues(mainController,actualMove.charAt(0),enemyType,false);
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
    public void setEnemyType(char enemyType) {
        this.enemyType = enemyType;
    }
    public void setActualMove(String actualMove) {
        this.actualMove = actualMove;
    }
    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }
}
