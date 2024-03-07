package com.tictactoegui.gameLogic;

public abstract class LoadingMethods {
    public String getReferenceToFXML(int boardSize) {
        String boardReference = switch (boardSize) {
            case 3 -> "/com/tictactoegui/fxmlFiles/GameScreen3x3.fxml";
            case 10 -> "/com/tictactoegui/fxmlFiles/GameScreen10x10.fxml";
            default -> null;
        };
        return boardReference;
    }
}
