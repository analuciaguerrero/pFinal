package com.example.demoJavafx;

import com.example.demoJavafx.excepciones.NoHayFicherosIniciales;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeleccionarPartidaController implements Initializable {
    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);

    @FXML
    private ListView<String> listaDeFicheros = new ListView<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listaDeFicheros.getItems().addAll(getNombreFicheros());
        } catch (NoHayFicherosIniciales e) {
            log.info("No se han encontrado ficheros de partida para cargar.");
        }
    }

    @FXML
    protected void onBotonCargarFicheroClick (ActionEvent event) throws IOException {
        if (!listaDeFicheros.getSelectionModel().isEmpty()) {
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();
            String archivo = listaDeFicheros.getSelectionModel().getSelectedItem();
            DatosJuego datosJuego = DatosJuego.cargarArchivo(archivo);
            ZombieStudentsLife juego = new ZombieStudentsLife(datosJuego, false);
            TableroController controlador = new TableroController(datosJuego, juego);
            controlador.crearTablero(juego.getTablero());
        }
    }

    @FXML
    protected void onBotonVolverMenuClick (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SeleccionarPartida.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private String[] getNombreFicheros () throws NoHayFicherosIniciales{
        File carpetaDeArchivos = new File("archivos");
        File[] archivos = carpetaDeArchivos.listFiles();
        if (archivos != null) {
            String[] nombresDeArchivos = new String[archivos.length];
            for (int i = 0; i != archivos.length; i++) {
                nombresDeArchivos[i] = archivos[i].getName();
            }
            return nombresDeArchivos;
        }
        throw new NoHayFicherosIniciales();
    }
    @FXML
    protected void onBotonNuevoClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Ajustes.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onBotonCargarPartidaClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CargarJuego.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
