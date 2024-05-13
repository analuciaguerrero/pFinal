package com.example.demoJavafx;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerMainStage {
    @FXML
    private Text turnoContador, vivosText, muertosText, playText, pauseText, //Información juego
            labelValorSliderestudiantesProbMuerte, labelValorSliderestudiantesProbReproduccion, labelValorSliderestudiantesProbClonado, //Ajustes User
            labelValorSlideraguaProbabilidad, labelValorSliderbibliotecaProbabilidad, labelValorSlidercomidaProbabilidad, labelValorSlidermontanaProbabilidad, labelValorSliderpozoProbabilidad, labelValorSlidertesoroProbabilidad, // Vida Recursos
            labelValorSlideraguaAumentoVida, labelValorSliderbibliotecaAumentoClon, labelValorSlidercomidaAumentoVida, labelValorSlidermontanaDisminucionVida, labelValorSlidertesoroAumentoRep, // Efecto Recursos
            aguaAparicionText, bibliotecaAparicionText, comidaAparicionText, montanaAparicionText, pozoAparicionText, tesoroAparicionText; // Aparicion Recursos
    @FXML
    private Slider estudiantesProbMuerte, estudiantesTurnosRestantesSpinner, estudiantesProbReproduccion, estudiantesProbClonado, //Ajustes User
            aguaProbabilidadSlider, bibliotecaProbabilidadSlider, comidaProbabilidadSlider, montanaProbabilidadSlider, pozoProbabilidadSlider, tesoroProbabilidadSlider, //Vida Recursos
            aguaAumentoVidaSlider, bibliotecaAumentoClonSlider, comidaAumentoVidaSlider, montanaDisminucionVidaSlider, tesoroAumentoRepSlider, //Efecto Recursos
            aguaTurnosRestantesSpinner, bibliotecaTurnosRestantesSpinner, comidaTurnosRestantesSpinner, montanaTurnosRestantesSpinner, pozoTurnosRestantesSpinner, tesoroTurnosRestantesSpinner; // Aparicion Recursos
    @FXML
    private Button buttonVelocidad, buttonPlay, buttonPause, buttonStop, clearTablero; //Botones tablero
    @FXML
    private RadioButton radioIndividuo, radioAgua, radioComida, radioMontana, radioBiblioteca, radioTesoro, radioPozo; //Añadir RadioButtons
    @FXML
    private ToggleGroup anadir;
    @FXML
    static MediaPlayer mediaPlayer;
    @FXML
    private BorderPane basePane;
    @FXML
    private GridPane centralGridPane, tableroJuego;
    @FXML
    private TabPane tabPaneParametros;
    @FXML
    private VBox infoVBox;
    @FXML
    private Stage stage;
    @FXML
    private Tab pauseTab, individuoTab, recursosParametrosTab, aparicionTab, anadirTab;
    protected boolean gameStopped = true;
    protected boolean gameON = false;
    private Timeline controlLoop;
    private Game game = DatosCompartidos.getGame();

    private static final Logger log = LogManager.getLogger(ControllerMainStage.class);

    ///////////////////////////////////BindingSliders////////////////////////////////////////////////////////////////////////////////
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
    protected IntegerProperty filas = new SimpleIntegerProperty(0);
    protected IntegerProperty columnas = new SimpleIntegerProperty(0);
    protected DoubleProperty velocidadJuegoProperty = new SimpleDoubleProperty(1.0);

    /////////////////////////////////////MouseEvents////////////////////////////////////////////////////

    @FXML
    void speedGame(MouseEvent event) {
        double nuevaVelocidad = 1;
        if (velocidadJuegoProperty.get() == 1){
            nuevaVelocidad = 1.5;
        } else if (velocidadJuegoProperty.get() == 1.5){
            nuevaVelocidad = 2;
        }else if (velocidadJuegoProperty.get() == 2){
            nuevaVelocidad = 1;
        }
        velocidadJuegoProperty.set(nuevaVelocidad);
    }
    @FXML
    void playGame(MouseEvent event) {
        DatosCompartidos.setGameIniciado(true);
        if (gameStopped) {
            gameStopped = false;
            this.buttonVelocidad.setDisable(false);

            tabPaneParametros.getSelectionModel().select(pauseTab);
            this.individuoTab.setDisable(true);
            this.recursosParametrosTab.setDisable(true);
            this.aparicionTab.setDisable(true);
            this.anadirTab.setDisable(true);
            this.pauseTab.setDisable(false);
            this.pauseText.setVisible(true);
            this.playText.setVisible(false);
            log.info("Reanudar el juego");
        }
    }
    @FXML
    void pauseGame(MouseEvent event) {
        if (!gameStopped) {
            gameStopped = true;
            this.buttonVelocidad.setDisable(true);

            this.individuoTab.setDisable(false);
            this.recursosParametrosTab.setDisable(false);
            this.aparicionTab.setDisable(false);
            this.anadirTab.setDisable(false);
            this.pauseTab.setDisable(true);
            this.pauseText.setVisible(false);
            this.playText.setVisible(true);
            log.info("Pausar el juego");
        }
    }
    @FXML
    void stopGame(MouseEvent event) {
        gameStopped = true;
        DatosCompartidos.setGameIniciado(false);
    }
    @FXML
    void aplicarUser(MouseEvent event){
        setSlidersValue(DatosCompartidos::setVidaInicial, estudiantesTurnosRestantesSpinner);
        setSlidersValue(DatosCompartidos::setProbReproduccion, estudiantesProbReproduccion);
        setSlidersValue(DatosCompartidos::setProbClonacion, estudiantesProbClonado);
    }
    @FXML
    void resetUser(MouseEvent event) {
        getDatosCompartidosValueSlider(DatosCompartidos::getVidaInicial, estudiantesTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbReproduccion, estudiantesProbReproduccion);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbClonacion, estudiantesProbClonado);
    }
    @FXML
    void aplicarRecursosVida(MouseEvent event){
        setSlidersValue(DatosCompartidos::setAguaVida, aguaProbabilidadSlider);
        setSlidersValue(DatosCompartidos::setComidaVida, comidaProbabilidadSlider);
        setSlidersValue(DatosCompartidos::setMontanaVida, montanaProbabilidadSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaVida, bibliotecaProbabilidadSlider);
        setSlidersValue(DatosCompartidos::setTesoroVida, tesoroProbabilidadSlider);
        setSlidersValue(DatosCompartidos::setPozoVida, pozoProbabilidadSlider);
        setSlidersValue(DatosCompartidos::setAguaEfecto, aguaAumentoVidaSlider);
        setSlidersValue(DatosCompartidos::setComidaEfecto, comidaAumentoVidaSlider);
        setSlidersValue(DatosCompartidos::setMontanaEfecto, montanaDisminucionVidaSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaEfecto, bibliotecaAumentoClonSlider);
        setSlidersValue(DatosCompartidos::setTesoroEfecto, tesoroAumentoRepSlider);
    }
    @FXML
    void resetRecursosVida(MouseEvent event){
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaVida, aguaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaVida, comidaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaVida, montanaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaVida, bibliotecaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroVida, tesoroProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoVida, pozoProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaEfecto, aguaAumentoVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaEfecto, comidaAumentoVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaEfecto, montanaDisminucionVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaEfecto, bibliotecaAumentoClonSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroEfecto, tesoroAumentoRepSlider);
    }
    @FXML
    void aplicarAparicionRecursos(MouseEvent event) {
        setSlidersValue(DatosCompartidos::setAguaAparicion, aguaTurnosRestantesSpinner);
        setSlidersValue(DatosCompartidos::setComidaAparicion, comidaTurnosRestantesSpinner);
        setSlidersValue(DatosCompartidos::setMontanaAparicion, montanaTurnosRestantesSpinner);
        setSlidersValue(DatosCompartidos::setBibliotecaAparicion, bibliotecaTurnosRestantesSpinner);
        setSlidersValue(DatosCompartidos::setTesoroAparicion, tesoroTurnosRestantesSpinner);
        setSlidersValue(DatosCompartidos::setPozoAparicion, pozoTurnosRestantesSpinner);
    }
    @FXML
    void resetAparicionRecursos(MouseEvent event) {
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaAparicion, aguaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaAparicion, comidaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaAparicion, montanaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaAparicion, bibliotecaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroAparicion, tesoroTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoAparicion, pozoTurnosRestantesSpinner);
    }

    // TENGO QUE PONER MODO OSCURO Y MODO CLARO
    @FXML
    void setEstiloFuego(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #ffc09f;");
        infoVBox.setStyle("-fx-background-color: #ffc09f;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Fuego");
        }
        log.info("Cambio de color de visualización: Fuego");
    }
    @FXML
    void setEstiloAgua(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #add8e6ff;");
        infoVBox.setStyle("-fx-background-color: #add8e6ff;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Agua");
        }
        log.info("Cambio de color de visualización: Agua");

    }
    @FXML
    void setEstiloNatura(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #adf7b6;");
        infoVBox.setStyle("-fx-background-color: #adf7b6;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Natura");
        }
        log.info("Cambio de color de visualización: Natura");

    }

    @FXML
    void setEstiloTierra(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #fcf5c7;");
        infoVBox.setStyle("-fx-background-color: #fcf5c7;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Tierra");
        }
        log.info("Cambio de color de visualización: Tierra");

    }

    @FXML
    void anadirElemento(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();
        ToggleGroup group = selectedRadioButton.getToggleGroup();

        if (group.getSelectedToggle() != null) {
            int valor = 0;

            if (selectedRadioButton.equals(radioIndividuo)) {
                valor = 1;
            } else if (selectedRadioButton.equals(radioAgua)) {
                valor = 2;
            } else if (selectedRadioButton.equals(radioComida)) {
                valor = 3;
            } else if (selectedRadioButton.equals(radioMontana)) {
                valor = 4;
            } else if (selectedRadioButton.equals(radioBiblioteca)) {
                valor = 5;
            } else if (selectedRadioButton.equals(radioTesoro)) {
                valor = 6;
            } else if (selectedRadioButton.equals(radioPozo)) {
                valor = 7;
            }

            DatosCompartidos.setAnadir(valor);
        }
    }

    @FXML
    void clear(MouseEvent event) {
        game.clearTablero(game.getTablero());
        game.actualizarTablero();
        DatosCompartidos.setGame(game);
    }

    @FXML
    void play(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void pause(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void eraseUnaVezLaVida(ActionEvent event) {
        mediaPlayer.stop();
        insertSong("EraseUnaVezLaVida.mp3");
    }

    @FXML
    void laBamba(ActionEvent event) {
        mediaPlayer.stop();
        insertSong("LaBamba.mp3");
    }

    ///////////////////////////////////Métodos de apoyo///////////////////////////////////////////
    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }
    protected void setSlidersValue(Consumer<String> setter, Slider slider){
        setter.accept(String.valueOf((int) slider.getValue()));
    }
    private void getDatosCompartidosValueSlider(Supplier<String> getter, Slider slider) {
        slider.setValue(Integer.parseInt(getter.get()));
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private void setImage (String path, ImageView imageView, GridPane gridPane){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        imageView.setImage(image);
        preserveRadio(imageView, gridPane);
    }
    protected void initializeMedia(){
//        insertImage(imageViewLogo, "IconLifeGame.png");
    }
    protected void insertImage(ImageView imageView, String resourceName) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(resourceName));
        imageView.setImage(image);
    }
    private void preserveRadio(ImageView image, GridPane gridPane) {
        AtomicReference<Double> maxWidth = new AtomicReference<>(Double.MAX_VALUE);
        AtomicReference<Double> maxHeight = new AtomicReference<>(Double.MAX_VALUE);

        // Escucha el cambio en el tamaño del GridPane y ajusta el tamaño máximo de la imagen
        gridPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue() / gridPane.getColumnCount() - 10; // Restamos un valor para dejar espacio para el borde, margen, etc.
            maxWidth.set(Math.min(maxWidth.get(), newWidth));
            image.setFitWidth(maxWidth.get());
        });

        gridPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue() / gridPane.getRowCount() - 10; // Restamos un valor para dejar espacio para el borde, margen, etc.
            maxHeight.set(Math.min(maxHeight.get(), newHeight));
            image.setFitHeight(maxHeight.get());
        });

        // Mantenemos la relación de aspecto
        image.setPreserveRatio(true);
    }
    protected static void initializeAudio(){
        insertSong("LaBamba.mp3");
    }
    protected static void insertSong(String resourceName) {
        String path = ControllerMainStage.class.getClassLoader().getResource(resourceName).toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
    public void updateAllSliders (){
        getDatosCompartidosValueSlider(DatosCompartidos::getVidaInicial, estudiantesTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbReproduccion, estudiantesProbReproduccion);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbClonacion, estudiantesProbClonado);

        getDatosCompartidosValueSlider(DatosCompartidos::getAguaVida, aguaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaVida, comidaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaVida, montanaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaVida, bibliotecaProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroVida, tesoroProbabilidadSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoVida, pozoProbabilidadSlider);

        getDatosCompartidosValueSlider(DatosCompartidos::getAguaEfecto, aguaAumentoVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaEfecto, comidaAumentoVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaEfecto, montanaDisminucionVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaEfecto, bibliotecaAumentoClonSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroEfecto, tesoroAumentoRepSlider);

        getDatosCompartidosValueSlider(DatosCompartidos::getAguaAparicion, aguaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaAparicion, comidaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaAparicion, montanaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaAparicion, bibliotecaTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroAparicion, tesoroTurnosRestantesSpinner);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoAparicion, pozoTurnosRestantesSpinner);
    }

    private void inicializarBucleControl() {
        controlLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (DatosCompartidos.isGameIniciado()) {
                if (!gameStopped) {
                    // Lógica para continuar el juego
                    int turno = Integer.parseInt(turnoContador.getText());
                    turno++;
                    DatosCompartidos.setTurnoJuego(turno);
                    turnoContador.setText(String.valueOf(turno));
                } else {
                    System.out.println("Juego pausado");
                }
            } else if (DatosCompartidos.getTurnoJuego() == 0) {
                System.out.println("El juego aun no ha iniciado");
            } else {
                System.out.println("Juego terminado");
                controlLoop.stop();
            }
        }));
        controlLoop.setCycleCount(Animation.INDEFINITE);
        controlLoop.rateProperty().bind(velocidadJuegoProperty);
        controlLoop.play();
    }

    ////////////////////////////////////////Initialize////////////////////////////////////////////
    @FXML
    public void initialize() {
        game = new Game(tableroJuego);
        DatosCompartidos.setGame(game);

        pauseTab.setDisable(true);
        this.pauseText.setVisible(false);
        this.playText.setVisible(true);
        this.buttonVelocidad.setDisable(true);

        initializeBindingSliders(estudiantesTurnosRestantesSpinner, labelValorSliderestudiantesProbMuerte, ProbMuerte);
        initializeBindingSliders(estudiantesProbReproduccion, labelValorSliderestudiantesProbReproduccion, ProbReproduccion);
        initializeBindingSliders(estudiantesProbClonado, labelValorSliderestudiantesProbClonado, ProbClonado);
        initializeBindingSliders(aguaProbabilidadSlider, labelValorSlideraguaProbabilidad, aguaProbabilidad);
        initializeBindingSliders(comidaProbabilidadSlider, labelValorSlidercomidaProbabilidad, comidaProbabilidad);
        initializeBindingSliders(montanaProbabilidadSlider, labelValorSlidermontanaProbabilidad, montanaProbabilidad);
        initializeBindingSliders(bibliotecaProbabilidadSlider, labelValorSliderbibliotecaProbabilidad, bibliotecaProbabilidad);
        initializeBindingSliders(tesoroProbabilidadSlider, labelValorSlidertesoroProbabilidad, tesoroProbabilidad);
        initializeBindingSliders(pozoProbabilidadSlider, labelValorSliderpozoProbabilidad, pozoProbabilidad);
        initializeBindingSliders(aguaAumentoVidaSlider, labelValorSlideraguaAumentoVida, aguaAumentoVida);
        initializeBindingSliders(comidaAumentoVidaSlider, labelValorSlidercomidaAumentoVida, comidaAumentoVida);
        initializeBindingSliders(montanaDisminucionVidaSlider, labelValorSlidermontanaDisminucionVida, montanaDisminucionVida);
        initializeBindingSliders(bibliotecaAumentoClonSlider, labelValorSliderbibliotecaAumentoClon, bibliotecaAumentoClon);
        initializeBindingSliders(tesoroAumentoRepSlider, labelValorSlidertesoroAumentoRep, tesoroAumentoRep);
        //initializeBindingSliders(aguaTurnosRestantesSpinner, aguaAparicionText, filas);
        //initializeBindingSliders(comidaTurnosRestantesSpinner, comidaAparicionText, columnas);
        //initializeBindingSliders(montanaTurnosRestantesSpinner, montanaAparicionText, medidaMontanaAparicion);
        //initializeBindingSliders(bibliotecaTurnosRestantesSpinner, bibliotecaAparicionText, medidaBibliotecaAparicion);
        //initializeBindingSliders(tesoroTurnosRestantesSpinner, tesoroAparicionText, medidaTesoroAparicion);
        //initializeBindingSliders(pozoTurnosRestantesSpinner, pozoAparicionText, medidaPozoAparicion);

        tabPaneParametros.getStylesheets().add(getClass().getResource("/EstiloFondo/estiloAgua.css").toExternalForm());
        infoVBox.getStylesheets().add(getClass().getResource("/EstiloFondo/estiloAgua.css").toExternalForm());

        tabPaneParametros.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                // Actualizar los sliders al cambiar de pestaña
                updateAllSliders();
                if (newTab == anadirTab) {
                    // Si es la pestaña de añadir, establecer el valor de DatosCompartidos como true
                    DatosCompartidos.setAnadirTab(true);
                } else {
                    // Si es cualquier otra pestaña, establecer el valor de DatosCompartidos como false
                    DatosCompartidos.setAnadirTab(false);
                    radioIndividuo.setSelected(false);
                    radioAgua.setSelected(false);
                    radioComida.setSelected(false);
                    radioMontana.setSelected(false);
                    radioBiblioteca.setSelected(false);
                    radioTesoro.setSelected(false);
                    radioPozo.setSelected(false);
                    DatosCompartidos.setAnadir(0);
                }
            }
        });

        inicializarBucleControl();

    }

}
