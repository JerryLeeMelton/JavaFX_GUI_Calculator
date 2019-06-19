//Jerry Melton
//CSIS139 JAVA (Sat 12:50pm)
//13 - GUI Calculator App

package com.jerryleemelton.javafxcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        stage.setTitle("JavaFX Calculator");
        stage.setScene(new Scene(root, 240, 402));
        stage.setResizable(false);

        stage.show();
    }

}