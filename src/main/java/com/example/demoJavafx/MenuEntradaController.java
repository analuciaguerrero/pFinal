package com.example.demoJavafx;

import com.example.demoJavafx.usuario.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    FileOutputStream ranking;

    /**
     * Creates new form MenuEntradaController
     */
    public MenuEntradaController() {
        loadRanking();
    }

    /**
     * Method responsible for loading the ranking from the .dat file
     */
    public void loadRanking() {
        FileInputStream fileRanking;
        ObjectInputStream streamRanking;
        try {
            fileRanking = new FileInputStream("ranking.dat");
            streamRanking = new ObjectInputStream(fileRanking);
            jugadores = (HashMap) streamRanking.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuEntradaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MenuEntradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    @FXML
    void initialize() {
        comboBoxAcceso.setValue("Registrarse");
        comboBoxAcceso.getValue().equals("Registrarse");
    }

    @FXML
    void handleContinuar(ActionEvent event) {
        if (comboBoxAcceso.getValue().equals("Acceder")) {
            comprobarAcceso();
        } else {
            comprobarRegistro();
        }
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
            MenuInicialController controller = loader.getController();

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
            abrirMenuPersonalizacion();
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
            abrirMenuPersonalizacion();
        }
    }

    private void abrirMenuPersonalizacion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ruta/del/archivo/fxml/Menu3Personalizacion.fxml"));
            Parent root = loader.load();
            MenuPersonalizacionController controller = loader.getController();
            // Here you can pass any necessary information to the customization window controller
            // For example:
            //controller.initData(jugador);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window if necessary
            Stage currentStage = (Stage) buttonContinuar.getScene().getWindow();
            currentStage.close();
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

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERTA");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}