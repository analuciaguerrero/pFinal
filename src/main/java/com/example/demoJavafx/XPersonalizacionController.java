package com.example.demoJavafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XPersonalizacionController {

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

    @FXML private Slider filasSlider;
    @FXML private Slider columnasSlider;

    @FXML private Text labelValorSliderestudiantesProbClonado;
    @FXML private Text labelValorSliderestudiantesProbReproduccion;
    @FXML private Text labelValorSliderestudiantesProbMuerte;
    @FXML private Text labelValorSlideraguaProbabilidad;
    @FXML private Text labelValorSlideraguaAumentoVida;
    @FXML private Text labelValorSliderbibliotecaProbabilidad;
    @FXML private Text labelValorSliderbibliotecaAumentoClon;
    @FXML private Text labelValorSlidercomidaProbabilidad;
    @FXML private Text labelValorSlidercomidaAumentoVida;
    @FXML private Text labelValorSlidermontanaProbabilidad;
    @FXML private Text labelValorSlidermontanaDisminucionVida;
    @FXML private Text labelValorSliderpozoProbabilidad;
    @FXML private Text labelValorSlidertesoroProbabilidad;
    @FXML private Text labelValorSlidertesoroAumentoRep;
    @FXML private Text labelValorSliderFilas;
    @FXML private Text labelValorSliderColumnas;

    protected IntegerProperty ProbClonado = new SimpleIntegerProperty(0);
    protected IntegerProperty ProbReproduccion = new SimpleIntegerProperty(0);
    protected IntegerProperty ProbMuerte = new SimpleIntegerProperty(0);
    protected IntegerProperty aguaAumentoVida = new SimpleIntegerProperty(0);
    protected IntegerProperty aguaProbabilidad = new SimpleIntegerProperty(0);
    protected IntegerProperty bibliotecaProbabilidad = new SimpleIntegerProperty(0);
    protected IntegerProperty bibliotecaAumentoClon = new SimpleIntegerProperty(0);
    protected IntegerProperty comidaProbabilidad = new SimpleIntegerProperty(0);
    protected IntegerProperty comidaAumentoVida = new SimpleIntegerProperty(0);
    protected IntegerProperty montanaProbabilidad = new SimpleIntegerProperty(0);
    protected IntegerProperty montanaDisminucionVida = new SimpleIntegerProperty(0);
    protected IntegerProperty pozoProbabilidad = new SimpleIntegerProperty(0);
    protected IntegerProperty tesoroProbabilidad = new SimpleIntegerProperty(0);
    protected IntegerProperty tesoroAumentoRep = new SimpleIntegerProperty(0);
    protected IntegerProperty filas = new SimpleIntegerProperty(1);
    protected IntegerProperty columnas = new SimpleIntegerProperty(1);

    @FXML private Button buttonReestablecer;
    @FXML private Button buttonGuardar;

    private static final Logger log = LogManager.getLogger(XPersonalizacionController.class);

    @FXML
    void initialize() {
        // Enlazar sliders y labels para estudiantes
        initializeSliderAndLabel(estudiantesProbClonado, labelValorSliderestudiantesProbClonado, ProbClonado);
        initializeSliderAndLabel(estudiantesProbReproduccion, labelValorSliderestudiantesProbReproduccion, ProbReproduccion);
        initializeSliderAndLabel(estudiantesProbMuerte, labelValorSliderestudiantesProbMuerte, ProbMuerte);

        // Enlazar sliders y labels para agua
        initializeSliderAndLabel(aguaProbabilidadSlider, labelValorSlideraguaProbabilidad, aguaProbabilidad);
        initializeSliderAndLabel(aguaAumentoVidaSlider, labelValorSlideraguaAumentoVida, aguaAumentoVida);

        // Enlazar sliders y labels para biblioteca
        initializeSliderAndLabel(bibliotecaProbabilidadSlider, labelValorSliderbibliotecaProbabilidad, bibliotecaProbabilidad);
        initializeSliderAndLabel(bibliotecaAumentoClonSlider, labelValorSliderbibliotecaAumentoClon, bibliotecaAumentoClon);

        // Enlazar sliders y labels para comida
        initializeSliderAndLabel(comidaProbabilidadSlider, labelValorSlidercomidaProbabilidad, comidaProbabilidad);
        initializeSliderAndLabel(comidaAumentoVidaSlider, labelValorSlidercomidaAumentoVida, comidaAumentoVida);

        // Enlazar sliders y labels para montaña
        initializeSliderAndLabel(montanaProbabilidadSlider, labelValorSlidermontanaProbabilidad, montanaProbabilidad);
        initializeSliderAndLabel(montanaDisminucionVidaSlider, labelValorSlidermontanaDisminucionVida, montanaDisminucionVida);

        // Enlazar sliders y labels para pozo
        initializeSliderAndLabel(pozoProbabilidadSlider, labelValorSliderpozoProbabilidad, pozoProbabilidad);

        // Enlazar sliders y labels para tesoro
        initializeSliderAndLabel(tesoroProbabilidadSlider, labelValorSlidertesoroProbabilidad, tesoroProbabilidad);
        initializeSliderAndLabel(tesoroAumentoRepSlider, labelValorSlidertesoroAumentoRep, tesoroAumentoRep);

        // Enlazar sliders y labels para tamaño del tablero
        initializeSliderAndLabel(filasSlider, labelValorSliderFilas, filas);
        initializeSliderAndLabel(columnasSlider, labelValorSliderColumnas, columnas);

    }

    private void initializeSliderAndLabel(Slider slider, Text label, IntegerProperty valorEstablecido) {
        // Enlazar slider y label
        slider.valueProperty().bindBidirectional(valorEstablecido);
        label.textProperty().bind(valorEstablecido.asString());

        // Configurar event handler para el slider
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(String.valueOf(newValue.intValue()));
            valorEstablecido.set(newValue.intValue());
        });
    }

    @FXML
    protected void guardarConfiguracion() {
        int estudiantesClonado = (int) estudiantesProbClonado.getValue();
        int estudiantesTurnosRestantes = estudiantesTurnosRestantesSpinner.getValue();
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

        int alto = (int) filasSlider.getValue();
        int ancho = (int) columnasSlider.getValue();

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
        filasSlider.setValue(1);
        columnasSlider.setValue(1);
    }
}
