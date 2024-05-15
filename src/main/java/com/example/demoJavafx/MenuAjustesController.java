package com.example.demoJavafx;

import com.example.demoJavafx.bucleDeControl.Tablero ;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

import static com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife.tablero;

public class MenuAjustesController {

    @FXML private Stage primaryStage; // Referencia al Stage principal

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

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private static final Logger log = LogManager.getLogger(PersonalizacionController.class);

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
    void next(MouseEvent event) throws IOException {
        DatosCompartidos.setVidaInicial(String.valueOf((int)estudiantesTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setProbReproduccion(String.valueOf((int)estudiantesProbReproduccion.getValue()));
        DatosCompartidos.setProbClonacion(String.valueOf((int)estudiantesProbClonado.getValue()));

        DatosCompartidos.setAguaEfecto(String.valueOf((int)aguaAumentoVidaSlider.getValue()));
        DatosCompartidos.setComidaEfecto(String.valueOf((int)comidaAumentoVidaSlider.getValue()));
        DatosCompartidos.setMontanaEfecto(String.valueOf((int)montanaDisminucionVidaSlider.getValue()));
        DatosCompartidos.setBibliotecaEfecto(String.valueOf((int)bibliotecaAumentoClonSlider.getValue()));
        DatosCompartidos.setTesoroEfecto(String.valueOf((int)tesoroAumentoRepSlider.getValue()));

        DatosCompartidos.setAguaVida(String.valueOf((int)aguaProbabilidadSlider.getValue()));
        DatosCompartidos.setComidaVida(String.valueOf((int)comidaProbabilidadSlider.getValue()));
        DatosCompartidos.setMontanaVida(String.valueOf((int)montanaProbabilidadSlider.getValue()));
        DatosCompartidos.setBibliotecaVida(String.valueOf((int)bibliotecaProbabilidadSlider.getValue()));
        DatosCompartidos.setTesoroVida(String.valueOf((int)tesoroProbabilidadSlider.getValue()));
        DatosCompartidos.setPozoVida(String.valueOf((int)pozoProbabilidadSlider.getValue()));

        DatosCompartidos.setAguaAparicion(String.valueOf((int)aguaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setComidaAparicion(String.valueOf((int)comidaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setMontanaAparicion(String.valueOf((int)montanaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setBibliotecaAparicion(String.valueOf((int)bibliotecaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setTesoroAparicion(String.valueOf((int)tesoroTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setPozoAparicion(String.valueOf((int)pozoTurnosRestantesSpinner.getValue()));

        DatosCompartidos.setAltoMatriz(String.valueOf((int) filasSlider.getValue()));
        DatosCompartidos.setAnchoMatriz(String.valueOf((int) columnasSlider.getValue()));
        tablero.makeBoard(tablero.tableroJuego, tablero.theme);

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazRecursosVida.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();

        log.info("Parametrización de las propiedades iniciales de los individuos correcta");
    }

    @FXML
    void nextAleatorio(MouseEvent event) {
        DatosCompartidos.setAguaAparicion(String.valueOf((int)aguaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setComidaAparicion(String.valueOf((int)comidaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setMontanaAparicion(String.valueOf((int)montanaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setBibliotecaAparicion(String.valueOf((int)bibliotecaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setTesoroAparicion(String.valueOf((int)tesoroTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setPozoAparicion(String.valueOf((int)pozoTurnosRestantesSpinner.getValue()));

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        DatosCompartidos.getGame().crearTableroAleatorio();
        DatosCompartidos.getGame().actualizarTablero();
        log.info("Parametrización de la probabilidad de aparición de los recursos correcta");
        log.info("Creación del tablero aleatorio correcta");
    }

    @FXML
    void nextClear(MouseEvent event) {
        DatosCompartidos.setAguaAparicion(String.valueOf((int)aguaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setComidaAparicion(String.valueOf((int)comidaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setMontanaAparicion(String.valueOf((int)montanaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setBibliotecaAparicion(String.valueOf((int)bibliotecaTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setTesoroAparicion(String.valueOf((int)tesoroTurnosRestantesSpinner.getValue()));
        DatosCompartidos.setPozoAparicion(String.valueOf((int)pozoTurnosRestantesSpinner.getValue()));

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        log.info("Parametrización de la probabilidad de aparición de los recursos correcta");
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
