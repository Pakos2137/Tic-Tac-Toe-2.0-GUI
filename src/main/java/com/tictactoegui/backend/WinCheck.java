package com.tictactoegui.backend;

import java.util.ArrayList;
import java.util.Objects;

public class WinCheck {
    int rounds = 0;
    Board board;
    int boardSize;
    boolean gameInProgress = true;

    public WinCheck(Board board) {
        this.board = board;
    }
    ArrayList<String> linesList = new ArrayList<>();
    public boolean winCheck3x3() {
        rounds++;
        linesList.clear();
        boardSize = board.board.length;
        String line = "";
        //rows to list
        for(int i = 0;i<boardSize;i++) {
            for (int k = 0;k<boardSize;k++){
                line += board.board[i][k];
            }
            linesList.add(line);
            line = "";
        }
        //columns to list
        for(int i = 0;i<boardSize;i++) {
            for (int k = 0;k<boardSize;k++){
                line += board.board[k][i];
            }
            linesList.add(line);
            line = "";
        }
        //cross leftTop to rightDown
        for(int i = 0;i<boardSize;i++) {
                line += board.board[i][i];
            }
        linesList.add(line);
        line = "";
        //cross rightTop to leftDown
        for(int i = 0;i<boardSize;i++) {
            line += board.board[2-i][i];
        }
        linesList.add(line);
        line = "";
        System.out.println(linesList);
        //winCheck
        for(int i = 0;i<linesList.size();i++)
        {
            if(Objects.equals(linesList.get(i), "XXX")) {
                System.out.println("X wygrało");
                setGameInProgress(false);
            }
            if(Objects.equals(linesList.get(i), "OOO")) {
                System.out.println("O wygrało");
                setGameInProgress(false);
            }
        }
        if(rounds >= boardSize * boardSize) {
            setGameInProgress(false);
        }
        return isGameInProgress();



    }
    public boolean winCheck10x10() {
        boardSize = board.board.length;
        boolean gameInProgress;
        gameInProgress = true;
        String line = "";
        ArrayList<String> lines = new ArrayList<>();
        //rows to list
        for(int i = 0;i<boardSize;i++) {
            for (int k = 0;k<boardSize;k++){
                line += board.board[i][k];
            }
            linesList.add(line);
            line = "";
        }
        //columns to list
        for(int i = 0;i<boardSize;i++) {
            for (int k = 0;k<boardSize;k++){
                line += board.board[k][i];
            }
            linesList.add(line);
            line = "";
        }
        System.out.println(linesList);
        return gameInProgress;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }
}
