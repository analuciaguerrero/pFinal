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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SeleccionarPartidaController implements Initializable {
    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);
    private DatosJuego datosJuego;

    @FXML
    private Button btnNuevaPartida;

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

    public DatosJuego getDatosJuego() {
        return datosJuego;
    }

    public void setDatosJuego(DatosJuego datosJuego) {
        this.datosJuego = datosJuego;
    }

    @FXML
    protected void onBotonCargarFicheroClick (ActionEvent event) throws IOException {
        if (!listaDeFicheros.getSelectionModel().isEmpty()) {
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();
            String archivo = listaDeFicheros.getSelectionModel().getSelectedItem();
            DatosJuego datosJuego = DatosJuego.cargarArchivo(archivo);
            assert datosJuego != null;
            ZombieStudentsLife juego = new ZombieStudentsLife(datosJuego, false);
            TableroController controlador = new TableroController(datosJuego, juego);
            controlador.crearTablero(juego.getTablero());
        }
    }
    @FXML
    protected void onBottonNuevoClick(ActionEvent event) throws IOException{

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
    protected void onBotonVolverMenuClick (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SeleccionarPartida.fxml")));
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
    protected void onBotonCargarPartidaClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CargarJuego.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
