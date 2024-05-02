package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuInicialController {

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonCargar;

    @FXML
    private void goNewPlay() {
        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEntrada.fxml"));
            Pane root = fxmlLoader.load();

            // Crear un ImageView con la imagen de fondo
            Image backgroundImage = new Image("file:/home/ana/IdeaProjects/pFinal/src/main/java/com/example/demojavafx/imagenes/fondo.jpg");
            ImageView backgroundImageView = new ImageView(backgroundImage);

            // Añadir la imagen de fondo al contenedor principal
            root.getChildren().add(backgroundImageView);

            // Obtener el Stage de la escena actual
            Stage currentStage = (Stage) buttonPlay.getScene().getWindow();

            // Crear una nueva escena con el contenedor como raíz
            Scene newScene = new Scene(root, 620, 440);

            // Establecer la nueva escena en el Stage actual
            currentStage.setScene(newScene);

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