package com.tictactoegui;

public class GameProcess {
    boolean gameInProgress = true;
    Board board;
    char playerType;

    public GameProcess(Board board,char playerType) {
        this.board = board;
        this.playerType = playerType;
    }
    public void startGame() {
        MoveProcess moveProcess = new MoveProcess(board);
        board.showBoard();
        while (gameInProgress) {
            moveProcess.playerFieldNumberInput();
            winCheck(gameInProgress);
            if(playerType == 'K' && gameInProgress) {
                moveProcess.cpuMove();
                winCheck(gameInProgress);
            }
            System.out.println(gameInProgress);
        }
    }
    private boolean winCheck(boolean gameInProgress) {
        WinCheck winCheck = new WinCheck(board);
        if(board.board.length == 3) {
            gameInProgress = winCheck.winCheck3x3();
        } else if (board.board.length == 10) {
            gameInProgress = winCheck.winCheck10x10();
        }
        return setGameInProgress(gameInProgress);
    }

    public boolean setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
        return gameInProgress;
    }
}
