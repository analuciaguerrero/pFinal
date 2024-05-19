package com.example.demoJavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TipoDePartidaController implements Initializable {

    private Stage seleccionarPartidaStage; // Referencia al escenario de SeleccionarPartida.fxml
    private Stage tableroStage;

    private DatosJuego datosJuego;

    public void setDatosJuego(DatosJuego datosJuego) {
        this.datosJuego = datosJuego;
    }

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
                FXMLLoader loader = new FXMLLoader(ApplicationMenuInicial.class.getResource("SeleccionarPartida.fxml"));
                Parent root = loader.load();
                SeleccionarPartidaController controller = loader.getController();

                // Crear un StackPane y agregar el AnchorPane al centro
                StackPane stackPane = new StackPane(root);
                stackPane.setAlignment(Pos.CENTER);

                seleccionarPartidaStage = new Stage();
                Scene scene = new Scene(stackPane, 650.0, 450.0);
                seleccionarPartidaStage.setScene(scene);
                seleccionarPartidaStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            seleccionarPartidaStage.show();
        }
    }

    @FXML
    private void onNuevaPartidaButtonClick() { // Lógica para el botón "Jugar nueva partida"
        try {
            FXMLLoader loader = new FXMLLoader(ApplicationMenuInicial.class.getResource("Tablero.fxml"));
            Parent root = loader.load();

            TableroController controller = new TableroController();
            loader.setController(controller);
            controller.setDatosJuego(datosJuego);


            // Crear un StackPane y agregar el AnchorPane al centro
            StackPane stackPane = new StackPane(root);
            stackPane.setAlignment(Pos.CENTER);

            if (tableroStage != null) {
                tableroStage.close();
            }

            tableroStage = new Stage();
            Scene scene = new Scene(stackPane, 840.0, 803.0);
            tableroStage.setScene(scene);
            tableroStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
