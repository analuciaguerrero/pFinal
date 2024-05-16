package com.example.demoJavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuInicialController {

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonAjustes;

    @FXML
    private Button buttonCargar;

    @FXML
    private void goNewPlay() {
        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEntrada.fxml"));
            Parent root = fxmlLoader.load();

            // Obtener el Stage de la escena actual
            Stage currentStage = (Stage) buttonPlay.getScene().getWindow();

            // Crear una nueva escena con el contenedor como raíz
            Scene newScene1 = new Scene(root, 1835, 1032);

            // Establecer la nueva escena en el Stage actual
            currentStage.setTitle("Práctica Final Ivan Y Ana");
            currentStage.setScene(newScene1);
            currentStage.show(); // Mostrar la nueva ventana

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSettings(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("Personalizacion.fxml"));

        try {
            Scene scene = new Scene((Parent)fxmlLoader.load(), 840.0, 803.0);
            stage.setTitle("Ajustes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception var4) {
            Exception e = var4;
            e.printStackTrace();
        }
    }


    @FXML
    private void exitGame() {
        // Salir del juego
        System.exit(0);
    }
}