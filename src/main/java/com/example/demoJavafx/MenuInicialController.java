package com.example.demoJavafx;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
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
    private void goNewPlay(ActionEvent event) throws IOException {
        // Cargar el archivo FXML del MenuEntrada
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEntrada.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) buttonPlay.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goToSettings(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("Personalizacion.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 840.0, 803.0);
        stage.setTitle("Ajustes");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void exitGame() {
        // Salir del juego
        System.exit(0);
    }
    @FXML
    void initialize() {
        // Si necesitas inicializar algo, agrégalo aquí
        // Por ejemplo, deshabilitar el botón de cargar si no hay partidas guardadas
        buttonCargar.setDisable(false); // Modificar esta lógica según sea necesario
    }
}