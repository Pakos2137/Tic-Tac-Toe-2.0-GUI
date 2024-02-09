package com.tictactoegui.backend;

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
        moveCounter++;
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
        moveCounter++;
        linesList.clear();
        conventRowsAndColumnsToList();
        crossLinesToList10x10();
        checkForWin();
        return isGameInProgress();

    }

    private void crossLinesToList10x10() {
        int StartingRowIndex;
        int startingColumnIndex;
        startingColumnIndex = 0;
        StartingRowIndex = 0;

        for(int i = 0;i<10;i++) {
            int actualColumn = startingColumnIndex;
            for(int actualRow = 0;actualRow<startingColumnIndex+1;actualRow++) {
                currentLine += gameBoard.board[actualRow][actualColumn];
                actualColumn--;
            }
            startingColumnIndex++;

            linesList.add(currentLine);
            currentLine = "";
        }
        startingColumnIndex = 9;
        for(int i = 0;i<10;i++) {
            int actualColumn = startingColumnIndex;
            for(int actualRow = StartingRowIndex;actualRow<startingColumnIndex+1;actualRow++) {
                currentLine += gameBoard.board[actualRow][actualColumn];
                actualColumn--;
            }
            StartingRowIndex++;

            linesList.add(currentLine);
            currentLine = "";
        }
        startingColumnIndex = 0;
        StartingRowIndex = 9;
        for(int i = 0;i<10;i++) {
            int actualColumn = startingColumnIndex;
            for(int actualRow = 9;actualRow<startingColumnIndex+1;actualRow--) {
                currentLine += gameBoard.board[actualRow][actualColumn];
                actualColumn--;
            }
            startingColumnIndex++;

            linesList.add(currentLine);
            currentLine = "";
        }
    }

    private void checkForWin() {
        String XValue = null;
        String OValue = null;
        if (boardSize == 3) {
            XValue = "XXX";
            OValue = "OOO";
        } else if (boardSize == 10) {
            XValue = "XXXXX";
            OValue = "OOOOO";
        }
        for(int i = 0;i<linesList.size();i++)
        {
            if(linesList.get(i).contains(XValue)) {
                System.out.println("X wygrało");
                setGameInProgress(false);
            }
            if(linesList.get(i).contains(OValue)) {
                System.out.println("O wygrało");
                setGameInProgress(false);
            }
        }
        if(moveCounter >= boardSize * boardSize) {
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
}
