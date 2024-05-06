package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPersonalizacionController {

    @FXML
    private ComboBox<String> ComboBoxDificultad;

    @FXML
    private Button ButtonCrear;

    @FXML
    private Button ButtonVolver;

    @FXML
    private Button ButtonSalir;

    @FXML
    private void handleButtonCrear() {
        // Assuming you want to hide the current window when creating a new one
        Stage stage = (Stage) ButtonCrear.getScene().getWindow();
        stage.hide(); // Hide the current window

        // Show the new window or scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonVolver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEntrada.fxml"));
            Parent root = loader.load();
            MenuEntradaController controller = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) ButtonVolver.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonSalir() {
        System.exit(0);
    }

    private void closeWindow() {
        Stage stage = (Stage) ButtonVolver.getScene().getWindow();
        stage.close();
    }
}
