package com.example.demoJavafx;
import com.example.demoJavafx.zombieStudentsLife.*;
import com.example.demoJavafx.tablero.*;
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
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class MenuAjustesController implements Initializable {
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
    private Spinner<Integer> ProbAparRecursoSpinner = new Spinner<>();
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
    public MenuAjustesController() {}
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
        SpinnerValueFactory<Integer> ProbAparRecursoVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
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
        addFiltroSpinner(ProbAparRecursoSpinner);
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
    public void setControllerValues(DatosJuego dato) {
        this.dato = dato;
        this.propiedades = new DatosJuegoProperties(dato);
        initializeController();
    }
    @FXML
    protected void onBottonGuardarClick(ActionEvent event) throws IOException {
        if (Window.getWindows().getFirst() != ((Node) event.getSource()).getScene().getWindow()) {
            Stage ventana = (Stage) Window.getWindows().getFirst();
            Celda celda = ((Celda) ((GridPane) ((AnchorPane) ventana.getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
            dato = celda.getDatos();
            propiedades.setDato(dato);
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        if (!dato.isPausado()) {
            confirmacion.initOwner(Stage.getWindows().getFirst());
            confirmacion.setTitle("Crear nueva partida");
            confirmacion.setHeaderText("Estás a punto de guardar los ajustes y crear una nueva partida");
            confirmacion.setContentText("¿Estás seguro de que quieres continuar?");
        } else {
            confirmacion.initOwner(Stage.getWindows().get(1));
            confirmacion.setTitle("Continuar con el juego");
            confirmacion.setHeaderText("Estás a punto de guardar los ajustes para continuar la partida");
            confirmacion.setContentText("¿Estás seguro de que quieres continuar?");
        }
        if(confirmacion.showAndWait().get() == ButtonType.OK) {
            continuarZombieStudentsLife(event);
        }
    }

    private void continuarZombieStudentsLife(ActionEvent event) throws IOException {
        if (!dato.isPausado()) {
            propiedades.commit();
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();
            comenzarNuevoJuego();
            log.debug("Se ha creado un nuevo juego");
        } else {
            propiedades.commit();

            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();

            log.debug("Se ha guardado la configuración");
        }
    }
    private void comenzarNuevoJuego () throws IOException {
        dato.setTurnoActual(0);
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(dato);
        TableroController controlador = new TableroController(dato, zombieStudentsLife);
        controlador.crearTablero(zombieStudentsLife.getTablero());
        dato.setPausado(true);
    }
    @FXML
    protected void onBottonReiniciarClick() {
        propiedades.rollback(tabActual);
        log.debug("Los valores por defecto han sido reestablecidos");
    }
    @FXML
    protected void onBottonVolverClick (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TipoDePartida.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
