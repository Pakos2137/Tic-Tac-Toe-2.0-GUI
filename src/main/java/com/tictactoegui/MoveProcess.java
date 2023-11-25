package com.tictactoegui;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MoveProcess {
    String actualMove = "X";
    Board board;
    public MoveProcess(Board board) {
        this.board = board;
    }
    public void playerFieldNumberInput() {
        System.out.println("Wybierz pole:");
        Scanner fieldNumberScan = new Scanner(System.in);
        try {
            int fieldNumber = fieldNumberScan.nextInt();
            fieldEditor(fieldNumber);
        } catch (Exception e) {
            System.out.println("Wybierz poprawną liczbę");
        }
    }
    public void fieldEditor(int fieldNumber) {
        int row = 0;
        int column = 0;
        if(board.board.length == 3) {
            int number = fieldNumber -1;
            row = number/3;
            column = number % 3;
        }
        if(board.board.length == 10) {
            int number = fieldNumber;
            row = number/10;
            column = number%10;
        }
        String boardValue = board.board[row][column];
        if (Objects.equals(String.valueOf(boardValue), String.valueOf(fieldNumber))) {
            board.board[row][column] = String.valueOf(actualMove);
            changeActualMove();
            board.showBoard();
        } else {
            System.out.println("To Pole zostało już wybrane:");
        }
    }
    public void cpuMove() {
        Random random = new Random();
        int row = 0;
        int column = 0;

        int randomNumber = random.nextInt((board.board.length * board.board.length));
        System.out.println(randomNumber);

        if(board.board.length == 3) {
            int number = randomNumber++;
            row = number/3;
            column = number % 3;
        }
        if(board.board.length == 10) {
            int number = randomNumber;
            row = number/10;
            column = number%10;
        }
        String boardValue = board.board[row][column];
        if (Objects.equals(String.valueOf(boardValue), String.valueOf(randomNumber))) {
            board.board[row][column] = String.valueOf(actualMove);
            changeActualMove();
            board.showBoard();
        } else {
            cpuMove();
        }

    }

    private void changeActualMove() {
        if(Objects.equals(getActualMove(), "X")) {
            setActualMove("O");
        } else {
            setActualMove("X");
        }
    }

    public String getActualMove() {
        return actualMove;
    }

    public void setActualMove(String actualMove) {
        this.actualMove = actualMove;
    }
}
