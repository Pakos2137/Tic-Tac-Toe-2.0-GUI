package com.tictactoegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/tictactoegui/fxmlFiles/MainScene.fxml"));
        StackPane stackPane = fxmlLoader.load();
        Scene scene = new Scene(stackPane, 600, 700);
        mainStage.setTitle("Kółko i Krzyżyk");
        mainStage.setScene(scene);
        mainStage.show();
    }
}