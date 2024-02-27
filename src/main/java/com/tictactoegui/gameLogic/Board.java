package com.tictactoegui.gameLogic;

public class Board {
    String[][] board;

    public void setBoard(int size) {
        for (int i = 0; i < size +1; i++) {
            board = new String[i][0];
            for (int k = 0; k < size +1; k++) {
                board = new String[i][k];
            }
        }
    }
    public void showBoard() {
        for (int row = 0; row < this.board.length; row++) {
            System.out.print("|");
            for (int column = 0; column < this.board[row].length; column++) {
                System.out.print(this.board[row][column] + "|");
            }
            System.out.println("");
        }
    }
}