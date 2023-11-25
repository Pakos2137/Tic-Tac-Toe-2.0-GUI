package com.tictactoegui;

public class Board {
    private int number = 0;
    String[][] board;

    public void setBoard3x3() {
        for (int i = 0; i < 4; i++) {
            board = new String[i][0];
            for (int k = 0; k < 4; k++) {
                board = new String[i][k];
            }
        }
        for (int i = 0; i < 3; i++) {
            number++;
            board[i][0] = String.valueOf(number);
            for (int k = 0; k < 3; k++) {
                board[i][k] = String.valueOf(number);
                number++;
            }
            number--;
        }
    }

    public void setBoard10x10() {
        for (int i = 0; i < 11; i++) {
            board = new String[i][0];
            for (int k = 0; k < 11; k++) {
                board = new String[i][k];
            }
        }
        number = -1;
        for (int i = 0; i < 10; i++) {
            number++;
            board[i][0] = String.valueOf(number);
            for (int k = 0; k < 10; k++) {
                board[i][k] = String.valueOf(number);
                number++;
            }
            number--;
        }
    }

    public void showBoard() {
        switch (board.length) {
            case 3: {
                for (int row = 0; row < this.board.length; row++) {
                    System.out.print("|");
                    for (int column = 0; column < this.board[row].length; column++) {
                        System.out.print(this.board[row][column] + "|");
                    }
                    System.out.println("");
                }
                break;
            }
            case 10: {
                for (int row = 0; row < this.board.length; row++) {
                    System.out.print("|");
                    for (int column = 0; column < this.board[row].length; column++) {
                        if (row == 0) {
                            System.out.print(this.board[row][column] + " |");
                        } else {
                            System.out.print(this.board[row][column] + "|");
                        }
                    }
                    System.out.println("");
                }
                break;
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }
}