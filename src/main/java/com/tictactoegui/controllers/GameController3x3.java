package com.tictactoegui.controllers;

import com.tictactoegui.backend.Board;
import com.tictactoegui.backend.MoveProcess;
import com.tictactoegui.backend.WinCheck;
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
    WinCheck winCheck;
    @FXML
    private void backToMenu() {
        mainController.loadMenuScreen();
    }
    @FXML
    private void reset() {
        backEndCreator();
        clearBoard();
    }
    @FXML
    private GridPane gameBoard3x3Pane;

    public void createBoard(int boardValue) {
        backEndCreator();
        for (int row = 0; row < boardValue; row++) {
            for (int col = 0; col < boardValue; col++) {
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setMinSize(167, 167);
                button.setOnAction(new ButtonClickHandler(button,row, col));
                button.setText("");
                button.setFocusTraversable(false);
                gameBoard3x3Pane.add(button, col, row);
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
                System.out.println(winCheck.winCheck3x3());
                if(playerType == 'C' && winCheck.isGameInProgress()) {
                    cpuMoveProcess();
                }
                board.showBoard();
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
        System.out.println(winCheck.winCheck3x3());
    }
    private void changeActualMove() {
        if(Objects.equals(getActualMove(), "X")) {
            setActualMove("O");
        } else {
            setActualMove("X");
        }
    }
    private void backEndCreator() {
        Board board = new Board();
        board.setBoard3x3();
        MoveProcess moveProcess = new MoveProcess(board);
        WinCheck winCheck = new WinCheck(board);
        this.board = board;
        this.winCheck = winCheck;
        this.moveProcess = moveProcess;
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
