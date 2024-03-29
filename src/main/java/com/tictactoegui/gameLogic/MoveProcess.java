package com.tictactoegui.gameLogic;

import java.util.Random;

public class MoveProcess {
    int[] cpuMoveList = new int[2];
    Board board;
    public MoveProcess(Board board) {
        this.board = board;
    }
    public void playerMoveProcess(int row,int col,String actualMove) {
        board.board[row][col] = String.valueOf(actualMove);
    }
    public void cpuMove(String actualMove) {
        Random random = new Random();
        int row;
        int column;

        int randomNumber = random.nextInt((board.board.length * board.board.length));
        int number = randomNumber;
        row = number/board.board.length;
        column = number % board.board.length;

        String boardValue = board.board[row][column];
        if (boardValue == null) {
            board.board[row][column] = String.valueOf(actualMove);
            cpuMoveList[0] = row;
            cpuMoveList[1] = column;
        } else {
            cpuMove(actualMove);
        }
    }
    public int[] getCpuMoveList() {
        return cpuMoveList;
    }
}
