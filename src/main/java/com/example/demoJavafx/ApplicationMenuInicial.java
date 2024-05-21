package com.example.demoJavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMenuInicial extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("MenuInicial.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1835, 1032);

        stage.setTitle("Práctica Final Ivan Y Ana");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}