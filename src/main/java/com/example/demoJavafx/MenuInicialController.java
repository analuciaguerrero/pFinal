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
    private Button startButton;

    @FXML
    private void goNewPlay(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del Tablero
            TableroController controller = loader.getController();

            // Crear los datos de juego y pasar al controlador del Tablero
            DatosJuego datosJuego = new DatosJuego(); // Asegúrate de inicializar esto correctamente
            ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego); // Asegúrate de inicializar esto correctamente
            controller.setDato(datosJuego);
            controller.setZombieStudentsLife(zombieStudentsLife);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadGameScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoJavafx/Tablero.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(new Scene(root));
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