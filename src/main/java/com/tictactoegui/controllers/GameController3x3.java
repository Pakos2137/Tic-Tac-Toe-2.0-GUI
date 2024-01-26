package com.tictactoegui.controllers;

import com.tictactoegui.Board;
import com.tictactoegui.MoveProcess;
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
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private GridPane gameBoard3x3Pane;


    public void createBoard(int boardValue) {
        for (int row = 0; row < boardValue; row++) {
            for (int col = 0; col < boardValue; col++) {
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setMinSize(167, 167);
                button.setOnAction(new ButtonClickHandler(button,row, col));
                button.setText("");
                button.setFocusTraversable(false);
                gameBoard3x3Pane.add(button, col, row);
                MoveProcess moveProcess = new MoveProcess(board);
                this.moveProcess = moveProcess;
            }
        }
    }
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private Button button;
        private int row;
        private int col;
        public ButtonClickHandler(Button button,int row, int col) {
            this.button = button;
            this.row = row;
            this.col = col;
        }
        @Override
        public void handle(ActionEvent event) {
            button.setText(getActualMove());
            button.setDisable(true);
            moveProcess.playerMoveProcess(row,col,actualMove);
            changeActualMove();
            if(playerType == 'C') {
                moveProcess.cpuMove(actualMove);
                int[] cpuMoveList = moveProcess.getCpuMoveList();
                Button cpuButton = findButtonByPosition(cpuMoveList[0], cpuMoveList[1]);
                cpuButton.setDisable(true);
                cpuButton.setText(actualMove);
                changeActualMove();
            }
            board.showBoard();
        }
        private Button findButtonByPosition(int row, int col) {
            //Button clickedButton = findButtonByPosition(row, col);
            for (javafx.scene.Node node : gameBoard3x3Pane.getChildren()) {
                if (node instanceof Button && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                    return (Button) node;
                }
            }
            return null;
        }
    }
    private void changeActualMove() {
        if(Objects.equals(getActualMove(), "X")) {
            setActualMove("O");
        } else {
            setActualMove("X");
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

    public void setBoard(Board board) {
        this.board = board;
    }
}
