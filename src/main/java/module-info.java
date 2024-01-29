module com.tictactoegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tictactoegui to javafx.fxml;
    exports com.tictactoegui;
    exports com.tictactoegui.controllers;
    opens com.tictactoegui.controllers to javafx.fxml;
    exports com.tictactoegui.backend;
    opens com.tictactoegui.backend to javafx.fxml;
}