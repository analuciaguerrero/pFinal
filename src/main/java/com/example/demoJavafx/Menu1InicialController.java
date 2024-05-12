package com.example.demoJavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu1InicialController {

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
            AnchorPane root = fxmlLoader.load();

            // Obtener el Stage de la escena actual
            Stage currentStage = (Stage) buttonPlay.getScene().getWindow();

            // Crear una nueva escena con el contenedor como raíz
            Scene newScene1 = new Scene(root, 1835, 1032);

            // Establecer la nueva escena en el Stage actual
            currentStage.setScene(newScene1);
            currentStage.show(); // Mostrar la nueva ventana

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSettings(ActionEvent event) {
        try {
            // Load the FXML file for the previous XML layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Personalizacion.fxml"));
            VBox root = loader.load();


            // Create a new Scene with the loaded root node
            Scene scene = new Scene(root);

            // Get the Stage from the button's Scene
            Stage stage = (Stage) buttonAjustes.getScene().getWindow();

            // Set the new Scene to the Stage
            stage.setScene(scene);
            stage.show();
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