package com.tictactoegui.backend;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CheckWinTest {

    Random random = new Random();

    @Test
    public void checkWinRows3x3test() {


        Board gameBoard = new Board();
        CheckWin checkWin = new CheckWin(gameBoard);
        gameBoard.setBoard(3);

       for (int i = 0;i < gameBoard.board.length ;i++) {
           gameBoard.setBoard(3);
           for (int k = 0; k < gameBoard.board.length ; k++) {
               gameBoard.board[i][k] = "X";
           }
           assertFalse(checkWin.checkWin3x3());
       }
    }
    @Test
    public void checkWinColumns3x3test() {

        Board gameBoard = new Board();
        CheckWin checkWin = new CheckWin(gameBoard);
        gameBoard.setBoard(3);

        for (int i = 0;i < gameBoard.board.length ;i++) {
            gameBoard.setBoard(3);
            for (int k = 0; k < gameBoard.board.length ; k++) {
                gameBoard.board[k][i] = "X";
            }
            assertFalse(checkWin.checkWin3x3());
        }
    }
    @Test
    public void checkWinCrosses3x3test() {

        Board gameBoard = new Board();
        CheckWin checkWin = new CheckWin(gameBoard);
        gameBoard.setBoard(3);

        gameBoard.board[0][0] = "X";
        gameBoard.board[1][1] = "X";
        gameBoard.board[2][2] = "X";

        assertFalse(checkWin.checkWin3x3());

        gameBoard.board[0][2] = "O";
        gameBoard.board[1][1] = "O";
        gameBoard.board[2][0] = "O";

        assertFalse(checkWin.checkWin3x3());
    }
    @Test
    public void checkWinRows10x10test() {

        Board gameBoard = new Board();
        CheckWin checkWin = new CheckWin(gameBoard);
        gameBoard.setBoard(10);

        for (int i = 0;i < gameBoard.board.length ;i++) {
            gameBoard.setBoard(10);
            for (int k = 0; k < gameBoard.board.length ; k++) {
                gameBoard.board[i][k] = "X";
            }
            assertFalse(checkWin.checkWin10x10());
        }
    }
    @Test
    public void checkWinColumns10x10test() {

        Board gameBoard = new Board();
        CheckWin checkWin = new CheckWin(gameBoard);
        gameBoard.setBoard(10);

        for (int i = 0;i < gameBoard.board.length ;i++) {
            gameBoard.setBoard(10);
            for (int k = 0; k < gameBoard.board.length ; k++) {
                gameBoard.board[k][i] = "X";
            }
            assertFalse(checkWin.checkWin10x10());
        }
    }
}
