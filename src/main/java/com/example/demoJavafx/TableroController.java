package com.example.demoJavafx;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

import static com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife.tablero;

public class TableroController {

    @FXML
    private Slider altoSlider;

    @FXML
    private Text altoText;

    @FXML
    private Slider anchoSlider;

    @FXML
    private Text anchoText;

    @FXML
    private Button buttonSiguiente;
    private Stage primaryStage; // Referencia al Stage principal
    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(1);
    protected IntegerProperty medidaAlto = new SimpleIntegerProperty(1);

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private static final Logger log = LogManager.getLogger(TableroController.class);


    @FXML
    void next(MouseEvent event) throws IOException {
        DatosCompartidos.setAltoMatriz(String.valueOf((int)altoSlider.getValue()));
        DatosCompartidos.setAnchoMatriz(String.valueOf((int)anchoSlider.getValue()));
        tablero.makeBoard(tablero.tableroJuego, tablero.theme);

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazIndividuoPropiedades.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();

        log.info("Parametrización de las dimensiones del tablero correcta");
    }

    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }

    @FXML
    public void initialize(){
        anchoSlider.setValue(1);
        altoSlider.setValue(1);
        anchoText.setText("1");
        altoText.setText("1");
        initializeBindingSliders(anchoSlider,anchoText, medidaAncho);
        initializeBindingSliders(altoSlider,altoText, medidaAlto);
    }


    @FXML
    private GridPane tableroGridPane;

    public void crearTablero(int ancho, int largo) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                // Crear celda
                Rectangle celda = new Rectangle(50, 50);
                celda.setFill(Color.WHITE);
                celda.setStroke(Color.BLACK);

                // Añadir celda
                tableroGridPane.add(celda, i, j);
            }
        }
    }
}
