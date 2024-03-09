package com.tictactoegui.gameLogic;

import com.tictactoegui.controllers.SaveMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SaveGame {
    CheckWin checkWin;
    String actualMove;
    char enemyType;
    Stage saveGameStage;
    Board board;

    public SaveGame(Board board, String actualMove, char enemyType,CheckWin checkWin) {
        this.checkWin = checkWin;
        this.board = board;
        this.actualMove = actualMove;
        this.enemyType = enemyType;
    }
    public void openSaveMenu() throws IOException {
        this.saveGameStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/tictactoegui/fxmlFiles/SaveMenu.fxml"));
        Scene saveScene = new Scene(fxmlLoader.load(),300,200);
        saveGameStage.setScene(saveScene);
        saveGameStage.setTitle("Zapisz Grę");
        saveGameStage.initModality(Modality.APPLICATION_MODAL);
        saveGameStage.setAlwaysOnTop(true);
        saveGameStage.setResizable(false);
        saveGameStage.show();
        SaveMenuController saveMenuController = fxmlLoader.getController();
        saveMenuController.setSaveGame(this);
    }
    public void addNewGameSave(String saveName) throws IOException {
        File directory = new File("src/main/resources/save games");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, saveName);
        if (!file.exists()) {
            file.createNewFile();
        }
        Path path = Paths.get("src/main/resources/save games/" + saveName);
        List<String> boardValuesList = getBoardValuesList();
        Files.write(path,boardValuesList, StandardOpenOption.TRUNCATE_EXISTING);
        saveGameStage.close();
    }

    private List<String> getBoardValuesList() {
        List<String> boardValuesList = new ArrayList<>();
        boardValuesList.add(String.valueOf(board.board.length));
        boardValuesList.add(String.valueOf(checkWin.getMoveCounter()));
        boardValuesList.add(actualMove);
        boardValuesList.add(String.valueOf(enemyType));
        for (int row = 0; row < board.board.length; row++) {
            for (int column = 0; column < this.board.board[row].length; column++) {
                boardValuesList.add(String.valueOf(board.board[row][column]));
            }
        }
        return boardValuesList;
    }
}
