package com.tictactoegui.controllers;

import com.tictactoegui.gameLogic.Board;
import com.tictactoegui.gameLogic.MoveProcess;
import com.tictactoegui.gameLogic.CheckWin;
import com.tictactoegui.gameLogic.SaveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GameController10x10 extends GameController{
    private String actualMove;
    private Board board;
    private char enemyType;
    private MainController mainController;
    private MoveProcess moveProcess;
    private CheckWin checkWin;
    private String startingMove;
    @FXML
    private GridPane gameBoard10x10Pane;
    @FXML
    Label moveText;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void reset() {
        createNewGameLogic();
        clearBoard();
        setActualMove(startingMove);
    }
    @FXML
    private void openSaveMenu() throws IOException {
        SaveGame saveGame = new SaveGame(board, actualMove, enemyType, checkWin);
        saveGame.openSaveMenu();
    }
    public void setValues(MainController mainController, char firstMove, char playerType, boolean isNewGame) {
        this.mainController = mainController;
        startingMove = String.valueOf(firstMove);
        this.enemyType = playerType;
        setActualMove(startingMove);
        moveText.setText("Ruch: " + actualMove);

        if(isNewGame) {
            createNewGameLogic();
            createFrontBoard();
        }
        setActualMove(String.valueOf(startingMove));
    }
    private void createFrontBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setMinSize(50, 50);
                button.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                button.setOnAction(new GameController10x10.ButtonClickHandler(button,row, col));
                buttonEditor(button,board,row,col);
                gameBoard10x10Pane.add(button, col, row);
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
            if(checkWin.isGameInProgress()) {
                button.setText(getActualMove());
                button.setDisable(true);
                moveProcess.playerMoveProcess(rowIndex, columnIndex,actualMove);
                changeActualMove();
                checkWin.checkWin10x10();
                if(enemyType == 'C' && checkWin.isGameInProgress()) {
                    cpuMoveProcess();
                    checkWin.checkWin10x10();
                }
                if(!checkWin.isGameInProgress()) {
                    setGameResultText();
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
        int[] cpuMovePositionValues = moveProcess.getCpuMoveList();
        Button cpuButton = findButtonByPosition(cpuMovePositionValues[0], cpuMovePositionValues[1]);
        cpuButton.setDisable(true);
        cpuButton.setText(getActualMove());
        changeActualMove();
    }
    private void changeActualMove() {
        actualMove = Objects.equals(getActualMove(), "X") ? "O" : "X";
        moveText.setText("Ruch: " + actualMove);
    }
    private void createNewGameLogic() {
        board = new Board();
        board.setBoard(10);
        moveProcess = new MoveProcess(board);
        checkWin = new CheckWin(board);
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
    public void loadGame(List<String> valuesOfBoard, int moveCounter) {
        Board board = new Board();
        board.setBoard(10);
        board.valuesToBoard(valuesOfBoard);
        checkWin = new CheckWin(board);
        checkWin.setMoveCounter(moveCounter);
        moveProcess = new MoveProcess(board);
        this.board = board;
        createFrontBoard();
    }
    private String getActualMove() {
        return actualMove;
    }
    private void setActualMove(String actualMove) {
        this.actualMove = actualMove;
    }
    public void setGameResultText() {
        moveText.setText(checkWin.getGameResultText());
    }
}