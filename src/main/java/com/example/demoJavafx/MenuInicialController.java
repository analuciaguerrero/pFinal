package com.example.demoJavafx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuInicialController {

    @FXML
    private Button buttonPlay;

    @FXML
    private void goNewPlay(ActionEvent event) throws IOException {
        // Cargar el archivo FXML del MenuEntrada
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEntrada.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) buttonPlay.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Menu de Entrada");
        stage.show();
    }
    @FXML
    private void exitGame() {
        // Salir del juego
        System.exit(0);
    }
}