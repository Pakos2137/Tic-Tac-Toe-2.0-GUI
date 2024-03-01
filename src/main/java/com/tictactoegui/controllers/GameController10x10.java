package com.tictactoegui.controllers;

import com.tictactoegui.gameLogic.Board;
import com.tictactoegui.gameLogic.MoveProcess;
import com.tictactoegui.gameLogic.CheckWin;
import com.tictactoegui.gameLogic.SaveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class GameController10x10 {
    SaveGame saveGame;
    private String actualMove = "X";
    Board board;
    char playerType;
    MainController mainController;
    MoveProcess moveProcess;
    CheckWin winCheck;
    private String staringMove;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void reset() {
        gameLogicSetter();
        clearBoard();
        setActualMove(staringMove);
    }
    @FXML
    private void openSaveMenu() throws IOException {
        saveGame = new SaveGame(board);
        saveGame.openSaveMenu();
    }
    @FXML
    private GridPane gameBoard10x10Pane;

    public void createBoard(int boardValue,char firstMove) {
        gameLogicSetter();
        for (int row = 0; row < boardValue; row++) {
            for (int col = 0; col < boardValue; col++) {
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setMinSize(50, 50);
                button.setOnAction(new ButtonClickHandler(button,row, col));
                button.setText("");
                button.setFocusTraversable(false);
                gameBoard10x10Pane.add(button, col, row);
                setActualMove(String.valueOf(firstMove));
                staringMove = String.valueOf(firstMove);
            }
        }
    }
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private Button button;
        private int rowIndex;
        private int columnIndex;
        public ButtonClickHandler(Button button,int row, int col) {
            this.button = button;
            this.rowIndex = row;
            this.columnIndex = col;
        }
        @Override
        public void handle(ActionEvent event) {
            if(winCheck.isGameInProgress()) {
                button.setText(getActualMove());
                button.setDisable(true);
                moveProcess.playerMoveProcess(rowIndex, columnIndex,actualMove);
                changeActualMove();
                winCheck.checkWin10x10();
                if(playerType == 'C' && winCheck.isGameInProgress()) {
                    cpuMoveProcess();
                }
            }
        }
    }
    private Button findButtonByPosition(int row, int col) {
        for (javafx.scene.Node node : gameBoard10x10Pane.getChildren()) {
            if (node instanceof Button && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }
    private void cpuMoveProcess() {
        moveProcess.cpuMove(actualMove);
        int[] cpuMovePositionList = moveProcess.getCpuMoveList();
        Button cpuButton = findButtonByPosition(cpuMovePositionList[0], cpuMovePositionList[1]);
        cpuButton.setDisable(true);
        cpuButton.setText(getActualMove());
        changeActualMove();
    }
    private void changeActualMove() {
        actualMove = Objects.equals(getActualMove(), "X") ? "O" : "X";
    }
    private void gameLogicSetter() {
        board = new Board();
        board.setBoard(10);
        moveProcess = new MoveProcess(board);
        winCheck = new CheckWin(board);

    }
    private void clearBoard() {
        gameBoard10x10Pane.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .forEach(button -> {
                    button.setText("");
                    button.setDisable(false);
                });
    }
    private String getActualMove() {
        return actualMove;
    }
    private void setActualMove(String actualMove) {
        this.actualMove = actualMove;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public void setPlayerType(char playerType) {
        this.playerType = playerType;
    }
}
