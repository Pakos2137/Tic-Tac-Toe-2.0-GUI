package com.tictactoegui.gameLogic;

import java.util.ArrayList;

public class CheckWin {
    private String currentLine;
    private int moveCounter = 0;
    Board gameBoard;
    private int boardSize;
    private boolean gameInProgress = true;
    ArrayList<String> linesList = new ArrayList<>();

    public CheckWin(Board board) {
        this.gameBoard = board;
    }
    public boolean checkWin3x3() {
        linesList.clear();
        conventRowsAndColumnsToList();
        //cross leftTop to rightDown
        for(int i = 0;i<boardSize;i++) {
                currentLine += gameBoard.board[i][i];
            }
        linesList.add(currentLine);
        currentLine = "";
        //cross rightTop to leftDown
        for(int i = 0;i<boardSize;i++) {
            currentLine += gameBoard.board[2-i][i];
        }
        linesList.add(currentLine);
        currentLine = "";
        checkForWin();
        return isGameInProgress();
    }
    public boolean checkWin10x10() {
        linesList.clear();
        conventRowsAndColumnsToList();
        crossLinesToList10x10();
        checkForWin();
        return isGameInProgress();
    }

    private void crossLinesToList10x10() {
        for (int i = 0; i < 10; i++) {
            String currentLine = "";
            for (int j = 0; j <= i; j++) {
                currentLine += gameBoard.board[j][i - j];
            }
            linesList.add(currentLine);
        }
        for (int i = 1; i < 10; i++) {
            String currentLine = "";
            for (int j = i; j < 10; j++) {
                currentLine += gameBoard.board[j][9 - (j - i)];
            }
            linesList.add(currentLine);
        }
        for (int i = 0; i < 10; i++) {
            String currentLine = "";
            for (int j = i; j < 10; j++) {
                currentLine += gameBoard.board[j][j - i];
            }
            linesList.add(currentLine);
        }
        for (int i = 9; i >= 0; i--) {
            String currentLine = "";
            for (int j = 0; j <= i; j++) {
                currentLine += gameBoard.board[j][9 - (i - j)];
            }
            linesList.add(currentLine);
        }
    }

    private void checkForWin() {
        moveCounter++;
        String XValue = null;
        String OValue = null;
        if (boardSize == 3) {
            XValue = "XXX";
            OValue = "OOO";
        } else if (boardSize == 10) {
            XValue = "XXXXX";
            OValue = "OOOOO";
        }
        for (String s : linesList) {
            if (s.contains(XValue)) {
                System.out.println("X wygrało");
                setGameInProgress(false);
            }
            if (s.contains(OValue)) {
                System.out.println("O wygrało");
                setGameInProgress(false);
            }
        }
        if(moveCounter >= boardSize * boardSize && isGameInProgress()) {
            System.out.println("remis");
            setGameInProgress(false);
        }
    }
    private void conventRowsAndColumnsToList() {
        boardSize = gameBoard.board.length;
        currentLine = "";
        //rows to list
        for(int i = 0;i<boardSize;i++) {
            for (int k = 0;k<boardSize;k++){
                currentLine += gameBoard.board[i][k];
            }
            linesList.add(currentLine);
            currentLine = "";
        }
        //columns to list
        for(int i = 0;i<boardSize;i++) {
            for (int k = 0;k<boardSize;k++){
                currentLine += gameBoard.board[k][i];
            }
            linesList.add(currentLine);
            currentLine = "";
        }
    }
    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }
}
