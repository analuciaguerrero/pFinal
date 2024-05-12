package com.example.demoJavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonalizacionController {

    @FXML private Slider estudiantesProbClonado;
    @FXML private Spinner<Integer> estudiantesTurnosRestantesSpinner;
    @FXML private Slider estudiantesProbReproduccion;
    @FXML private Slider estudiantesProbMuerte;

    @FXML private Slider aguaProbabilidadSlider;
    @FXML private Spinner<Integer> aguaTurnosRestantesSpinner;
    @FXML private Slider aguaAumentoVidaSlider;

    @FXML private Slider bibliotecaProbabilidadSlider;
    @FXML private Spinner<Integer> bibliotecaTurnosRestantesSpinner;
    @FXML private Slider bibliotecaAumentoClonSlider;

    @FXML private Slider comidaProbabilidadSlider;
    @FXML private Spinner<Integer> comidaTurnosRestantesSpinner;
    @FXML private Slider comidaAumentoVidaSlider;

    @FXML private Slider montanaProbabilidadSlider;
    @FXML private Spinner<Integer> montanaTurnosRestantesSpinner;
    @FXML private Slider montanaDisminucionVidaSlider;

    @FXML private Slider pozoProbabilidadSlider;
    @FXML private Spinner<Integer> pozoTurnosRestantesSpinner;

    @FXML private Slider tesoroProbabilidadSlider;
    @FXML private Spinner<Integer> tesoroTurnosRestantesSpinner;
    @FXML private Slider tesoroAumentoRepSlider;

    @FXML private Slider altoSlider;
    @FXML private Slider anchoSlider;

    @FXML private Label labelValorSliderestudiantesProbClonado;
    @FXML private Label labelValorSliderestudiantesProbReproduccion;
    @FXML private Label labelValorSliderestudiantesProbMuerte;
    @FXML private Label labelValorSlideraguaProbabilidad;
    @FXML private Label labelValorSpinneraguaTurnosRestantes;
    @FXML private Label labelValorSlideraguaAumentoVida;
    @FXML private Label labelValorSliderbibliotecaProbabilidad;
    @FXML private Label labelValorSpinnerbibliotecaTurnosRestantes;
    @FXML private Label labelValorSliderbibliotecaAumentoVida;
    @FXML private Label labelValorSlidercomidaProbabilidad;
    @FXML private Label labelValorSpinnercomidaTurnosRestantes;
    @FXML private Label labelValorSlidercomidaAumentoVida;
    @FXML private Label labelValorSlidermontanaProbabilidad;
    @FXML private Label labelValorSpinnermontanaTurnosRestantes;
    @FXML private Label labelValorSlidermontanaAumentoVida;
    @FXML private Label labelValorSliderpozoProbabilidad;
    @FXML private Label labelValorSpinnerpozoTurnosRestantes;
    @FXML private Label labelValorSliderpozoAumentoVida;
    @FXML private Label labelValorSlidertesoroProbabilidad;
    @FXML private Label labelValorSpinnertesoroTurnosRestantes;
    @FXML private Label labelValorSlidertesoroAumentoVida;
    @FXML private Label labelValorSliderAlto;
    @FXML private Label labelValorSliderAncho;

    @FXML private VBox vBox;
    @FXML private AnchorPane anchorPane;

    @FXML private Button buttonReestablecer;
    @FXML private Button buttonGuardar;
    @FXML private Button buttonVolver;

    @FXML
    void initialize() {
        initializeSliders();
        setupEventHandlers();
    }

    private void initializeSliders() {
        estudiantesProbClonado.setValue(0);
        estudiantesProbReproduccion.setValue(0);
        estudiantesProbMuerte.setValue(0);
        aguaProbabilidadSlider.setValue(0);
        aguaAumentoVidaSlider.setValue(0);
        comidaProbabilidadSlider.setValue(0);
        comidaAumentoVidaSlider.setValue(0);
        montanaProbabilidadSlider.setValue(0);
        montanaDisminucionVidaSlider.setValue(0);
        bibliotecaProbabilidadSlider.setValue(0);
        bibliotecaAumentoClonSlider.setValue(0);
        pozoProbabilidadSlider.setValue(0);
        tesoroProbabilidadSlider.setValue(0);
        tesoroAumentoRepSlider.setValue(0);
        altoSlider.setValue(1);
        anchoSlider.setValue(1);
    }

    private void setupEventHandlers() {
        estudiantesProbClonado.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderestudiantesProbClonado.setText(String.valueOf(newValue.intValue()));
        });

        estudiantesProbReproduccion.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderestudiantesProbReproduccion.setText(String.valueOf(newValue.intValue()));
        });

        estudiantesProbMuerte.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderestudiantesProbMuerte.setText(String.valueOf(newValue.intValue()));
        });

        aguaProbabilidadSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlideraguaProbabilidad.setText(String.valueOf(newValue.intValue()));
        });

        aguaTurnosRestantesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSpinneraguaTurnosRestantes.setText(String.valueOf(newValue));
        });

        aguaAumentoVidaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlideraguaAumentoVida.setText(String.valueOf(newValue.intValue()));
        });

        bibliotecaProbabilidadSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderbibliotecaProbabilidad.setText(String.valueOf(newValue.intValue()));
        });

        bibliotecaTurnosRestantesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSpinnerbibliotecaTurnosRestantes.setText(String.valueOf(newValue));
        });

        bibliotecaAumentoClonSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderbibliotecaAumentoVida.setText(String.valueOf(newValue.intValue()));
        });

        // Event handlers for other controls
        comidaProbabilidadSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlidercomidaProbabilidad.setText(String.valueOf(newValue.intValue()));
        });

        comidaTurnosRestantesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSpinnercomidaTurnosRestantes.setText(String.valueOf(newValue));
        });

        comidaAumentoVidaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlidercomidaAumentoVida.setText(String.valueOf(newValue.intValue()));
        });

        // Event handlers for other controls
        montanaProbabilidadSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlidermontanaProbabilidad.setText(String.valueOf(newValue.intValue()));
        });

        montanaTurnosRestantesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSpinnermontanaTurnosRestantes.setText(String.valueOf(newValue));
        });

        montanaDisminucionVidaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlidermontanaAumentoVida.setText(String.valueOf(newValue.intValue()));
        });

        // Event handlers for other controls
        pozoProbabilidadSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderpozoProbabilidad.setText(String.valueOf(newValue.intValue()));
        });

        pozoTurnosRestantesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSpinnerpozoTurnosRestantes.setText(String.valueOf(newValue));
        });

        // Event handlers for other controls
        tesoroProbabilidadSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlidertesoroProbabilidad.setText(String.valueOf(newValue.intValue()));
        });

        tesoroTurnosRestantesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSpinnertesoroTurnosRestantes.setText(String.valueOf(newValue));
        });

        tesoroAumentoRepSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlidertesoroAumentoVida.setText(String.valueOf(newValue.intValue()));
        });

        // Event handlers for other controls
        altoSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderAlto.setText(String.valueOf(newValue.intValue()));
        });

        anchoSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSliderAncho.setText(String.valueOf(newValue.intValue()));
        });
    }

    @FXML
    protected void guardarConfiguracion() {
        int estudiantesClonado = (int) estudiantesProbClonado.getValue();
        int estudiantesTurnosRestantes = (int) estudiantesTurnosRestantesSpinner.getValue();
        int estudiantesReproduccion = (int) estudiantesProbReproduccion.getValue();
        int estudiantesMuerte = (int) estudiantesProbMuerte.getValue();

        int aguaTurnosRestantes = aguaTurnosRestantesSpinner.getValue();
        int aguaAumentoVida = (int) aguaAumentoVidaSlider.getValue();
        float aguaProbabilidad = (float) aguaProbabilidadSlider.getValue();

        int bibliotecaTurnosRestantes = bibliotecaTurnosRestantesSpinner.getValue();
        float bibliotecaAumentoVida = (float) bibliotecaAumentoClonSlider.getValue();
        float bibliotecaProbabilidad = (float) bibliotecaProbabilidadSlider.getValue();

        int comidaTurnosRestantes = comidaTurnosRestantesSpinner.getValue();
        int comidaAumentoVida = (int) comidaAumentoVidaSlider.getValue();
        float comidaProbabilidad = (float) comidaProbabilidadSlider.getValue();

        int montañaTurnosRestantes = montanaTurnosRestantesSpinner.getValue();
        int montañaAumentoVida = (int) montanaDisminucionVidaSlider.getValue();
        float montañaProbabilidad = (float) montanaProbabilidadSlider.getValue();

        int pozoTurnosRestantes = pozoTurnosRestantesSpinner.getValue();
        float pozoProbabilidad = (float) pozoProbabilidadSlider.getValue();

        int tesoroTurnosRestantes = tesoroTurnosRestantesSpinner.getValue();
        int tesoroAumentoVida = (int) tesoroAumentoRepSlider.getValue();
        float tesoroProbabilidad = (float) tesoroProbabilidadSlider.getValue();

        int alto = (int) altoSlider.getValue();
        int ancho = (int) anchoSlider.getValue();

        // Aquí podrías guardar la configuración en un archivo XML, en una base de datos, etc.
        System.out.println("Configuración guardada:");
        System.out.println("Estudiantes - Turnos Restantes: " + estudiantesTurnosRestantes + ", ProbClonado: " + estudiantesClonado + ", ProbReproduccion: " + estudiantesReproduccion + ", PropMuerte: " + estudiantesMuerte);
        System.out.println("Agua - Turnos Restantes: " + aguaTurnosRestantes + ", Aumento de Vida: " + aguaAumentoVida + ", Probabilidad: " + aguaProbabilidad);
        System.out.println("Biblioteca - Turnos Restantes: " + bibliotecaTurnosRestantes + ", Aumento de Vida: " + bibliotecaAumentoVida + ", Probabilidad: " + bibliotecaProbabilidad);
        System.out.println("Comida - Turnos Restantes: " + comidaTurnosRestantes + ", Aumento de Vida: " + comidaAumentoVida + ", Probabilidad: " + comidaProbabilidad);
        System.out.println("Montaña - Turnos Restantes: " + montañaTurnosRestantes + ", Aumento de Vida: " + montañaAumentoVida + ", Probabilidad: " + montañaProbabilidad);
        System.out.println("Pozo - Turnos Restantes: " + pozoTurnosRestantes + ", Probabilidad: " + pozoProbabilidad);
        System.out.println("Tesoro - Turnos Restantes: " + tesoroTurnosRestantes + ", Aumento de Vida: " + tesoroAumentoVida + ", Probabilidad: " + tesoroProbabilidad);
        System.out.println("Tamaño del Tablero: " + alto + "x" + ancho);
    }


    @FXML
    private void reiniciarConfiguracion() {
        estudiantesProbClonado.setValue(0);
        estudiantesProbReproduccion.setValue(0);
        estudiantesProbMuerte.setValue(0);
        aguaProbabilidadSlider.setValue(0);
        aguaAumentoVidaSlider.setValue(0);
        comidaProbabilidadSlider.setValue(0);
        comidaAumentoVidaSlider.setValue(0);
        montanaProbabilidadSlider.setValue(0);
        montanaDisminucionVidaSlider.setValue(0);
        bibliotecaProbabilidadSlider.setValue(0);
        bibliotecaAumentoClonSlider.setValue(0);
        pozoProbabilidadSlider.setValue(0);
        tesoroProbabilidadSlider.setValue(0);
        tesoroAumentoRepSlider.setValue(0);
        altoSlider.setValue(1);
        anchoSlider.setValue(1);
    }

    @FXML
    private void cerrarVentana() {
        try {
            // Load the initial menu FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInicial.fxml"));
            Parent root = loader.load();
            Menu1InicialController controller = loader.getController();

            // Show the initial menu stage
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
}
