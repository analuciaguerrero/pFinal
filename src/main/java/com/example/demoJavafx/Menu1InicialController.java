package com.example.demoJavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu1InicialController {

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonCargar;

    @FXML
    private void goNewPlay() {
        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEntrada.fxml"));
            AnchorPane root = fxmlLoader.load();

            // Obtener el Stage de la escena actual
            Stage currentStage = (Stage) buttonPlay.getScene().getWindow();

            // Crear una nueva escena con el contenedor como raíz
            Scene newScene = new Scene(root, 620, 440);

            // Establecer la nueva escena en el Stage actual
            currentStage.setScene(newScene);
            currentStage.show(); // Mostrar la nueva ventana

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitGame() {
        // Salir del juego
        System.exit(0);
    }
}