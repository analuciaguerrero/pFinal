package com.example.demoJavafx;

import com.example.demoJavafx.usuario.Jugador;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuEntradaController {

    @FXML
    private Label labelNombre;

    @FXML
    private Button buttonContinuar;

    @FXML
    private Button buttonSalir;

    @FXML
    private Button buttonVolver;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private ComboBox<String> comboBoxAcceso;

    private HashMap<String, Jugador> jugadores = new HashMap<>();

    private Jugador jugador;

    private DatosJuego datosJuego;

    public void setDatosJuego(DatosJuego datosJuego) {
        this.datosJuego = datosJuego;
    }


    FileOutputStream ranking;

    /**
     * Creates new form MenuEntradaController
     */
    public MenuEntradaController() {
    }

    /**
     * Method responsible for loading the ranking from the .dat file
     */

    /**
     * Get the desired player
     *
     * @return The desired player
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Get the players registered in the game
     *
     * @return Players registered in the game
     */
    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public DatosJuego getDatosJuego() {
        return datosJuego;
    }
    public void setDatosJuego(){
        this.datosJuego = datosJuego;
    }

    @FXML
    void initialize() {
        // Configurar elementos iniciales del ComboBox
        comboBoxAcceso.setItems(FXCollections.observableArrayList("Registrarse", "Acceder"));
        comboBoxAcceso.setValue("Registrarse");
        ComboBox1ActionPerformed(null); // Set initial visibility based on default selection
        comboBoxAcceso.setOnAction(this::ComboBox1ActionPerformed);
    }

    @FXML
    void handleSalir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handleVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInicial.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) buttonVolver.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void comprobarAcceso() {
        String dni = textFieldDNI.getText().toUpperCase();
        if (!jugadores.containsKey(dni)) {
            mostrarAlerta("Este jugador no está registrado");
        } else {
            jugador = jugadores.get(dni);
        }
    }

    public void comprobarRegistro() {
        String dni = textFieldDNI.getText().toUpperCase();
        if (jugadores.containsKey(dni)) {
            mostrarAlerta("Este jugador ya está registrado");
        } else {
            jugador = new Jugador(dni);
            jugador.setNombre(textFieldNombre.getText());
            jugadores.put(dni, jugador);
        }
    }


    private void abrirTipoDePartida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionarPartida.fxml"));
            Parent root = loader.load();

            SeleccionarPartidaController controller = loader.getController();
            controller.setDatosJuego(datosJuego);

            // Crear un StackPane y agregar el AnchorPane al centro
            StackPane stackPane = new StackPane(root);
            stackPane.setAlignment(Pos.CENTER);

            Stage stage = new Stage();
            Scene scene = new Scene(stackPane, 650.0, 450.0);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ComboBox1ActionPerformed(ActionEvent event) {
        String accion = comboBoxAcceso.getValue(); // Obtenemos la acción seleccionada en el ComboBox
        if (accion.equals("Acceder")) { // Si es Acceder
            // Ocultamos la etiqueta y el campo de nombre
            labelNombre.setVisible(false);
            textFieldNombre.setVisible(false);
        } else { // Si es Registrarse
            // Mostramos la etiqueta y el campo de nombre
            labelNombre.setVisible(true);
            textFieldNombre.setVisible(true);
        }
    }

    @FXML
    void handleContinuar(ActionEvent event) {
        if (comboBoxAcceso.getValue().equals("Acceder")) {
            comprobarAcceso();
        } else {
            comprobarRegistro();
        }
        abrirTipoDePartida();
    }

    @FXML
    private void handleIniciar(ActionEvent event) {
        try {
            // Cargar la nueva pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva pantalla y pasarle los datos necesarios
            TableroController controller = loader.getController();
            DatosJuego dato = new DatosJuego();

            // Establecer la nueva escena en la ventana actual
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERTA");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}