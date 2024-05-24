package com.example.demoJavafx;

import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class XPersonalizacionController implements Initializable {

    Tab tabActual;
    @FXML
    TabPane tabPaneConfiguracion = new TabPane();
    @FXML
    private Spinner<Integer> TurnosVidaInicialesSpinner = new Spinner<>();
    @FXML
    private Slider ProbReproEstudianteSlider = new Slider();
    @FXML
    private Slider ProbClonEstudianteSlider = new Slider();
    @FXML
    private Slider ProbMejoraNormalSlider = new Slider();

    @FXML
    private Slider ProbMejoraAvanzadoSlider = new Slider();

    @FXML
    private Spinner<Double> ProbAparRecursoSpinner = new Spinner<>();
    @FXML
    private Slider ProbAparAguaSlider = new Slider();
    @FXML
    private Slider ProbAparComidaSlider = new Slider();
    @FXML
    private Slider ProbAparMontañaSlider = new Slider();
    @FXML
    private Slider ProbAparTesoroSlider = new Slider();
    @FXML
    private Slider ProbAparBibliotecaSlider = new Slider();
    @FXML
    private Slider ProbAparPozoSlider = new Slider();
    @FXML
    private Spinner<Integer> TurnosInicialesRecursoSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> AumentoTurnosAguaSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> AumentoTurnosComidaSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> ReduccionTurnosMontañaSpinner = new Spinner<>();
    @FXML
    private Slider AumentoProbReproSlider = new Slider();
    @FXML
    private Slider AumentoProbClonSlider = new Slider();
    @FXML
    private Spinner<Integer> FilasDelTableroSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> ColumnasDelTableroSpinner = new Spinner<>();

    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);

    private DatosJuego dato = new DatosJuego(10, 50, 20, 50,20, 10,10,30,25,15, 10,15,20,2,10, 2, 35, 20, 10, 10, 0);
    private DatosJuegoProperties propiedades = new DatosJuegoProperties(dato);

    public XPersonalizacionController() {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPaneConfiguracion.getSelectionModel().selectedItemProperty().addListener((_, _, newTab) -> {
            log.debug("Se ha detectado un cambio en el tabPane");
            if (newTab != null) tabActual = newTab;
        });
        initializeController();
    }
    private void initializeController () {
        initializeSpinners();
        if (propiedades != null) {
            this.actualizarDato();
        }
    }
    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }

    protected void initializeSpinners() {
        SpinnerValueFactory<Integer> TurnosVidaInicialesVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000);
        SpinnerValueFactory<Double> ProbAparRecursoVF = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100);
        SpinnerValueFactory<Integer> TurnosInicialesRecursoVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000);
        SpinnerValueFactory<Integer> IncrementoAguaVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        SpinnerValueFactory<Integer> IncrementoComidaVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        SpinnerValueFactory<Integer> IncrementoMontañaVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        SpinnerValueFactory<Integer> FilasTableroVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        SpinnerValueFactory<Integer> ColumnasTableroVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        TurnosVidaInicialesSpinner.setValueFactory(TurnosVidaInicialesVF);
        ProbAparRecursoSpinner.setValueFactory(ProbAparRecursoVF);
        TurnosInicialesRecursoSpinner.setValueFactory(TurnosInicialesRecursoVF);
        AumentoTurnosAguaSpinner.setValueFactory(IncrementoAguaVF);
        AumentoTurnosComidaSpinner.setValueFactory(IncrementoComidaVF);
        ReduccionTurnosMontañaSpinner.setValueFactory(IncrementoMontañaVF);
        FilasDelTableroSpinner.setValueFactory(FilasTableroVF);
        ColumnasDelTableroSpinner.setValueFactory(ColumnasTableroVF);
        addFiltroSpinner(TurnosVidaInicialesSpinner);
        addFiltroSpinner2(ProbAparRecursoSpinner);
        addFiltroSpinner(TurnosInicialesRecursoSpinner);
        addFiltroSpinner(AumentoTurnosAguaSpinner);
        addFiltroSpinner(AumentoTurnosComidaSpinner);
        addFiltroSpinner(ReduccionTurnosMontañaSpinner);
        addFiltroSpinner(TurnosVidaInicialesSpinner);
        addFiltroSpinner(FilasDelTableroSpinner);
        addFiltroSpinner(ColumnasDelTableroSpinner);
    }
    protected void actualizarDato() {
        ProbReproEstudianteSlider.valueProperty().bindBidirectional(propiedades.ProbReproEstudianteProperty());
        ProbClonEstudianteSlider.valueProperty().bindBidirectional(propiedades.ProbClonEstudianteProperty());
        ProbMejoraNormalSlider.valueProperty().bindBidirectional(propiedades.ProbMejorarANormalProperty());
        ProbMejoraAvanzadoSlider.valueProperty().bindBidirectional(propiedades.ProbMejorarAAvanzadoProperty());
        ProbAparAguaSlider.valueProperty().bindBidirectional(propiedades.ProbAguaProperty());
        ProbAparComidaSlider.valueProperty().bindBidirectional(propiedades.ProbComidaProperty());
        ProbAparMontañaSlider.valueProperty().bindBidirectional(propiedades.ProbMontañaProperty());
        ProbAparTesoroSlider.valueProperty().bindBidirectional(propiedades.ProbTesoroProperty());
        ProbAparBibliotecaSlider.valueProperty().bindBidirectional(propiedades.ProbBibliotecaProperty());
        ProbAparPozoSlider.valueProperty().bindBidirectional(propiedades.ProbPozoProperty());
        AumentoProbReproSlider.valueProperty().bindBidirectional(propiedades.AumentoProbReproProperty());
        AumentoProbClonSlider.valueProperty().bindBidirectional(propiedades.AumentoProbClonProperty());
        TurnosVidaInicialesSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.TurnosVidaInicialesProperty());
        ProbAparRecursoSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.ProbRecursoProperty());
        TurnosInicialesRecursoSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.TurnosInicialesRecursoProperty());
        AumentoTurnosAguaSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.AumentoTurnosAguaProperty());
        AumentoTurnosComidaSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.AumentoTurnosComidaProperty());
        ReduccionTurnosMontañaSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.ReduccionTurnosMontañaProperty());
        FilasDelTableroSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.FilasDelTableroProperty());
        ColumnasDelTableroSpinner.getValueFactory().valueProperty().bindBidirectional(propiedades.ColumnasDelTableroProperty());
    }

    private void addFiltroSpinner (Spinner<Integer> spinner) {
        spinner.getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
    }
    private void addFiltroSpinner2 (Spinner<Double> spinner) {
        spinner.getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9.]")) {
                event.consume();
            }
        });
    }
    public void setControllerValues(DatosJuego dato) {
        this.dato = dato;
        this.propiedades = new DatosJuegoProperties(dato);
        initializeController();
    }
    @FXML
    protected void onBottonGuardarClick(ActionEvent event) throws IOException {
        try {
            if (Window.getWindows().isEmpty()) {
                System.err.println("No hay ventanas abiertas.");
                return;
            }

            Stage ventana = (Stage) Window.getWindows().getFirst();
            System.out.println("Ventana obtenida: " + ventana);

            // Verificar que la escena no sea nula
            if (ventana.getScene() == null || ventana.getScene().getRoot() == null) {
                System.err.println("La escena o la raíz de la escena es nula.");
                return;
            }

            // Verificar que haya al menos dos hijos en el root
            ObservableList<Node> rootChildren = ventana.getScene().getRoot().getChildrenUnmodifiable();
            if (rootChildren.size() > 1) {
                AnchorPane anchorPane = (AnchorPane) rootChildren.get(1);
                System.out.println("AnchorPane obtenida: " + anchorPane);

                // Verificar que el AnchorPane tenga al menos un hijo
                ObservableList<Node> anchorPaneChildren = anchorPane.getChildrenUnmodifiable();
                if (!anchorPaneChildren.isEmpty()) {
                    GridPane gridPane = (GridPane) anchorPaneChildren.get(0);
                    System.out.println("GridPane obtenida: " + gridPane);

                    // Verificar que el GridPane tenga al menos un hijo
                    ObservableList<Node> gridPaneChildren = gridPane.getChildren();
                    if (!gridPaneChildren.isEmpty()) {
                        Celda celda = (Celda) gridPaneChildren.get(0);
                        System.out.println("Celda obtenida: " + celda);

                        dato = celda.getDatos();
                        if (dato == null) {
                            System.err.println("Los datos obtenidos de la celda son nulos.");
                            return;
                        }
                        System.out.println("Dato obtenido de la celda: " + dato);

                        propiedades.setDato(dato);
                    } else {
                        System.err.println("El GridPane no tiene hijos.");
                    }
                } else {
                    System.err.println("El AnchorPane no tiene hijos.");
                }
            } else {
                System.err.println("El root no tiene suficientes hijos.");
            }

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            if (!dato.isPausado()) {
                confirmacion.initOwner(Stage.getWindows().getFirst());
                confirmacion.setTitle("Iniciar un juego nuevo");
                confirmacion.setHeaderText("Estás a punto de guardar los ajustes y empezar una nueva partida");
                confirmacion.setContentText("¿Seguro que quieres continuar?");
            } else {
                confirmacion.initOwner(Stage.getWindows().get(1));
                confirmacion.setTitle("Continuar");
                confirmacion.setHeaderText("Estás a punto de guardar los ajustes para continuar la partida");
                confirmacion.setContentText("¿Seguro que quieres continuar?");
            }

            if (confirmacion.showAndWait().get() == ButtonType.OK) {
                continuarZombieStudentsLife(event);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            System.err.println("Error de conversión de tipo: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.err.println("Error de índice fuera de rango: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    private void continuarZombieStudentsLife(ActionEvent event) throws IOException {
        propiedades.commit();
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        if (!dato.isPausado()) {
            comenzarNuevoJuego();
            log.debug("Ha sido iniciada una partida nueva");
        } else {
            log.debug("Se han guardado los ajustes");
        }
    }

    private void comenzarNuevoJuego() throws IOException {
        dato.setTurnoActual(0);
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(dato, false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
        Parent root = loader.load();

        // Configurar el controlador del tablero
        TableroController controlador = loader.getController();
        controlador.inicializar(dato, zombieStudentsLife);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tablero");
        stage.show();

        dato.setPausado(true);
    }
    @FXML
    protected void onBottonReiniciarClick() {
        propiedades.rollback(tabActual);
        log.debug("Los valores por defecto han sido reestablecidos");
    }
    @FXML
    protected void onBottonVolverClick (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SeleccionarPartida.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
