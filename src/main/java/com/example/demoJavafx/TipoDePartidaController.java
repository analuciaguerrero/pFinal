package com.example.demoJavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TipoDePartidaController implements Initializable {

    private Stage seleccionarPartidaStage; // Referencia al escenario de SeleccionarPartida.fxml

    @FXML
    private Button btnSeguirPartida;

    @FXML
    private Button btnNuevaPartida;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aquí puedes inicializar elementos adicionales si es necesario
    }

    // Define los métodos para manejar eventos de los botones u otra lógica de la interfaz de usuario
    @FXML
    private void onSeguirPartidaButtonClick() {
        if (seleccionarPartidaStage == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("SeleccionarPartida.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 650.0, 450.0);
                seleccionarPartidaStage = new Stage();
                seleccionarPartidaStage.setScene(scene);
                seleccionarPartidaStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            seleccionarPartidaStage.toFront(); // Si ya está abierta, llevarla al frente
        }
    }

    @FXML
    private void onNuevaPartidaButtonClick() {
        // Lógica para el botón "Jugar nueva partida"
    }
}
