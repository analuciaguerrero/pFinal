package com.example.demoJavafx;

import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TipoDePartidaController {
    private DatosJuego dato;
    @FXML
    private Button buttonNuevaPartida;

    @FXML
    private Button buttonCargarPartida;

    @FXML
    private void initialize() {
        // Inicializa componentes si es necesario
    }

    @FXML
    private void handleNuevaPartida(ActionEvent event) {
        cargarTablero(event, new DatosJuego());
    }

    @FXML
    private void handleCargarPartida(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Partida");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("archivos", "Partida1.json"));

        // Set the initial directory to the 'archivos' folder in the project's root directory
        File initialDirectory = new File(System.getProperty("user.dir") + "/archivos");
        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (file != null) {
            DatosJuego datosJuego = DatosJuego.cargarDesdeArchivo(file.getAbsolutePath());
            if (datosJuego != null) {
                cargarTablero(event, datosJuego);
            } else {
                mostrarAlerta("Error al cargar la partida");
            }
        }
    }

    private void cargarTablero(ActionEvent event, DatosJuego datosJuego) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tablero.fxml"));
            Parent root = loader.load();

            TableroController controller = loader.getController();
            ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego);
            controller.setDatosJuego(datosJuego);
            controller.setZombieStudentsLife(zombieStudentsLife);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DatosJuego getDatosJuego() {
        return dato;
    }

    public void setDatosJuego(DatosJuego dato) {
        this.dato = dato;
    }

    private DatosJuego cargarDatosPartida() {
        // Lógica para cargar los datos de la partida seleccionada
        // Esto es solo un ejemplo y debería ajustarse a tu implementación
        return new DatosJuego();
    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERTA");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
