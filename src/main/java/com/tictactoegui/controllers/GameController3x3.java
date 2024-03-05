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
import java.util.List;
import java.util.Objects;

public class GameController3x3 {
    private String actualMove;
    private Board board;
    private char enemyType;
    private MainController mainController;
    private MoveProcess moveProcess;
    private CheckWin winCheck;
    private SaveGame saveGame;
    @FXML
    private GridPane gameBoard3x3Pane;
    private String staringMove;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void reset() {
        createNewGameLogic();
        clearBoard();
        setActualMove(staringMove);
    }
    @FXML
    private void openSaveMenu() throws IOException {
        saveGame = new SaveGame(board,actualMove, enemyType);
        saveGame.openSaveMenu();
    }
    public void setValues(MainController mainController,char firstMove,char playerType,boolean isNewGame) {
        this.mainController = mainController;
        staringMove = String.valueOf(firstMove);
        this.enemyType = playerType;
        setActualMove(String.valueOf(staringMove));
        if(isNewGame) {
            createNewGameLogic();
            createBoard(3);
        }
        setActualMove(String.valueOf(staringMove));
    }
    public void createBoard(int boardSize) {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setMinSize(167, 167);
                button.setOnAction(new ButtonClickHandler(button,row, col));
                button.setText(board.getBoard()[row][col]);
                if(board.getBoard()[row][col] != null) {
                    button.setDisable(true);
                }
                button.setFocusTraversable(false);
                gameBoard3x3Pane.add(button, col, row);
            }
        }
    }
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private final Button button;
        private final int rowIndex;
        private final int columnIndex;
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
                winCheck.checkWin3x3();
                if(enemyType == 'C' && winCheck.isGameInProgress()) {
                    cpuMoveProcess();
                    winCheck.checkWin3x3();
                }
            }
        }
    }
    private Button findButtonByPosition(int row, int col) {
        for (javafx.scene.Node node : gameBoard3x3Pane.getChildren()) {
            if (node instanceof Button && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }
    private void cpuMoveProcess() {
        moveProcess.cpuMove(actualMove);
        int[] cpuMovePositionValues = moveProcess.getCpuMoveList();
        Button cpuButton = findButtonByPosition(cpuMovePositionValues[0], cpuMovePositionValues[1]);
        cpuButton.setDisable(true);
        cpuButton.setText(getActualMove());
        changeActualMove();
    }
    private void changeActualMove() {
        actualMove = Objects.equals(getActualMove(), "X") ? "O" : "X";
    }
    private void createNewGameLogic() {
        board = new Board();
        board.setBoard(3);
        moveProcess = new MoveProcess(board);
        winCheck = new CheckWin(board);
    }
    private void clearBoard() {
        gameBoard3x3Pane.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .forEach(button -> {
                    button.setText("");
                    button.setDisable(false);
                });
    }
    public void loadGame(List<String> valuesOfBoard) {
        Board board = new Board();
        board.setBoard(3);
        board.valuesToBoard(valuesOfBoard);
        winCheck = new CheckWin(board);
        moveProcess = new MoveProcess(board);
        this.board = board;
        createBoard(3);
    }
    private String getActualMove() {
        return actualMove;
    }
    private void setActualMove(String actualMove) {
        this.actualMove = actualMove;
    }
}