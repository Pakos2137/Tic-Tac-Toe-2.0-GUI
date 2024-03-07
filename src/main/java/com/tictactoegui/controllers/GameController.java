package com.tictactoegui.controllers;

import com.tictactoegui.gameLogic.Board;
import com.tictactoegui.gameLogic.CheckWin;
import com.tictactoegui.gameLogic.MoveProcess;
import javafx.scene.control.Button;

import java.util.List;

public abstract class GameController {
    public void buttonEditor(Button button, Board board, int row, int col) {
        button.setText(board.getBoard()[row][col]);
        if(board.getBoard()[row][col] != null) {
            button.setDisable(true);
        }
        button.setFocusTraversable(false);
    }
}
