package com.tictactoegui.controllers;

import com.tictactoegui.backend.Board;
import com.tictactoegui.backend.MoveProcess;
import com.tictactoegui.backend.CheckWin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.Objects;

public class GameController3x3 {
    private String actualMove = "X";
    Board board;
    char playerType;
    MainController mainController;
    MoveProcess moveProcess;
    CheckWin winCheck;
    @FXML
    private GridPane gameBoard3x3Pane;
    private String staringMove;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void reset() {
        backEndCreator();
        clearBoard();
        setActualMove(staringMove);
    }
    public void createBoard(int boardValue,char firstMove) {
        backEndCreator();
        for (int row = 0; row < boardValue; row++) {
            for (int col = 0; col < boardValue; col++) {
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setMinSize(167, 167);
                button.setOnAction(new ButtonClickHandler(button,row, col));
                button.setText("");
                button.setFocusTraversable(false);
                gameBoard3x3Pane.add(button, col, row);
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
                System.out.println(winCheck.checkWin3x3());
                if(playerType == 'C' && winCheck.isGameInProgress()) {
                    cpuMoveProcess();
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
        int[] cpuMoveList = moveProcess.getCpuMoveList();
        Button cpuButton = findButtonByPosition(cpuMoveList[0], cpuMoveList[1]);
        cpuButton.setDisable(true);
        cpuButton.setText(getActualMove());
        changeActualMove();
        System.out.println(winCheck.checkWin3x3());
    }
    private void changeActualMove() {
        actualMove = Objects.equals(getActualMove(), "X") ? "O" : "X";
    }
    private void backEndCreator() {
        board = new Board();
        board.setBoard(3);
        moveProcess = new MoveProcess(board);
        winCheck = new CheckWin(board);
    }
    private void clearBoard() {
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard().length; col++) {
                Button editedButton = findButtonByPosition(row, col);
                editedButton.setText("");
                editedButton.setDisable(false);
            }
        }
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
