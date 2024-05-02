package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.demojavafx.usuario.Jugador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuEntradaController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> comboBoxAccion;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelDNI;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private Button buttonContinuar;

    @FXML
    private Button buttonSalir;

    @FXML
    private Button buttonVolver;

    private HashMap<String, Jugador> jugadores = new HashMap<>();

    private Jugador jugador;

    @FXML
    void initialize() {
        loadRanking();
        comboBoxAccion.getItems().addAll("Registrarse", "Acceder");
        comboBoxAccion.setOnAction(event -> {
            String accion = comboBoxAccion.getValue();
            if (accion != null && accion.equals("Acceder")) {
                labelNombre.setVisible(false);
                textFieldNombre.setVisible(false);
            } else {
                labelNombre.setVisible(true);
                textFieldNombre.setVisible(true);
            }
        });

        buttonContinuar.setOnAction(event -> {
            String accion = comboBoxAccion.getValue();
            if (accion != null) {
                if (accion.equals("Acceder")) {
                    comprobarAcceso();
                } else {
                    comprobarRegistro();
                }
            }
        });

        buttonSalir.setOnAction(event -> {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        });

        buttonVolver.setOnAction(event -> {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
            // Código para volver a la ventana anterior si es necesario
            // Ejemplo: Menu1InicialController m = new Menu1InicialController();
            // m.mostrarVentana();
        });
    }

    private void loadRanking() {
        try (FileInputStream fileRanking = new FileInputStream("ranking.dat");
             ObjectInputStream streamRanking = new ObjectInputStream(fileRanking)) {
            jugadores = (HashMap<String, Jugador>) streamRanking.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu2EntradaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu2EntradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comprobarAcceso() {
        String dni = textFieldDNI.getText().toUpperCase();
        if (!jugadores.containsKey(dni)) {
            // Mostrar mensaje de jugador no registrado
        } else {
            jugador = jugadores.get(dni);
            // Abrir la siguiente ventana (Menu3PersonalizacionController)
        }
    }

    private void comprobarRegistro() {
        String dni = textFieldDNI.getText().toUpperCase();
        if (jugadores.containsKey(dni)) {
            // Mostrar mensaje de jugador ya registrado
        } else {
            jugador = new Jugador(dni);
            jugador.setNombre(textFieldNombre.getText());
            jugadores.put(dni, jugador);
            // Abrir la siguiente ventana (Menu3PersonalizacionController)
        }
    }
}
