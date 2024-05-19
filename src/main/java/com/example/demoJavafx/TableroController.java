package com.example.demoJavafx;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class TableroController {
    private DatosJuego datos;
    private ZombieStudentsLife zombieStudentsLife;
    private Tablero tablero = new Tablero(datos);

    @FXML
    private javafx.scene.text.Text turnoLabel;
    @FXML
    private GridPane gridPane;

    private static final Logger log = LogManager.getLogger();

    public void setDatosJuego(DatosJuego datos){
        this.datos = datos;
        this.tablero = new Tablero (datos);
        this.zombieStudentsLife = new ZombieStudentsLife(datos, tablero);
    }

    public TableroController() {}

    public TableroController(DatosJuego datos) {
        this.datos = datos;
        this.tablero = new Tablero(datos);
        this.zombieStudentsLife = new ZombieStudentsLife(datos, tablero);
    }

    @FXML
    protected void initialize() {
        actualizarGridPane();
    }

    private void actualizarGridPane() {
        gridPane.getChildren().clear();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Celda celda = tablero.getCelda(i, j);
                Button btnCelda = new Button();
                btnCelda.setPrefSize(100, 100);
                btnCelda.setOnAction(event -> mostrarElementosCelda(celda));
                gridPane.add(btnCelda, j, i);
            }
        }
    }

    private void avanzarZombieStudentsLife(boolean unTurno, Celda celda) {
        if (Window.getWindows().size() < 3) {
            if (Window.getWindows().size() > 1) {
                Stage ventanaCasilla = (Stage) Window.getWindows().get(1);
                ventanaCasilla.close();
            }
            ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datos, tablero);
            turnoLabel.setText(String.valueOf(zombieStudentsLife.getPropiedad().getTurnoProperty().asString("Turno: %d")));
            zombieStudentsLife.getPropiedad().actualizarTurnoProperty();
            zombieStudentsLife.start(unTurno);
        }
    }

    @FXML
    protected void onBottonPausarClick(ActionEvent event) {
        datos.setPausado(true);
    }

    @FXML
    protected void onBottonPasarDeTurnoClick(ActionEvent event) {
        if (datos.isPausado()) {
            avanzarZombieStudentsLife(true, tablero.getCelda(0, 0)); // Assuming the top-left cell to start
        }
    }

    @FXML
    protected void onBottonReanudarClick(ActionEvent event) {
        if (datos.isPausado()) {
            datos.setPausado(false);
            avanzarZombieStudentsLife(false, tablero.getCelda(0, 0)); // Assuming the top-left cell to start
        }
    }

    @FXML
    protected void onBottonConfiguracionToClick(ActionEvent event) throws IOException {
        datos.setPausado(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajustes.fxml"));
        Parent root = loader.load();
        MenuAjustesController controller = loader.getController();
        controller.setControllerValues(datos);
        Stage stage = new Stage();
        Stage ventana = ((Stage) Window.getWindows().get(0));
        stage.initOwner(ventana);
        stage.setResizable(false);
        if (Window.getWindows().size() > 1) {
            Stage ventana2 = (Stage) Window.getWindows().get(1);
            ventana2.close();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onBottonCerrarClick() {
        ((Stage) Window.getWindows().get(0)).close();
    }

    @FXML
    protected void onBottonPantallaCompletaToClick() {
        Stage pantalla = ((Stage) Window.getWindows().get(0));
        pantalla.setFullScreen(!pantalla.isFullScreen());
    }

    @FXML
    protected void onBottonMinimizarClick() {
        ((Stage) Window.getWindows().get(0)).setIconified(true);
    }

    @FXML
    protected void onBottonMenuPrincipalClick(ActionEvent event) {
        if (!datos.isSave()) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.initOwner(Stage.getWindows().get(0));
            confirmacion.setTitle("Volver al menú inicial");
            confirmacion.setHeaderText("Estás a punto de volver al menú inicial y perderás todo tu progreso guardado");
            confirmacion.setContentText("¿Deseas guardar la partida antes de salir?");
            ButtonType botonGuardar = new ButtonType("Guardar");
            ButtonType botonNoGuardar = new ButtonType("No guardar");
            ButtonType botonCancelar = new ButtonType("Cancelar");
            confirmacion.getButtonTypes().setAll(botonGuardar, botonNoGuardar, botonCancelar);
            confirmacion.showAndWait().ifPresent(respuesta -> {
                if (respuesta == botonGuardar) {
                    guardarPartida(event);
                    volverAlMenuPrincipal(event);
                } else if (respuesta == botonNoGuardar) {
                    volverAlMenuPrincipal(event);
                } else {
                    confirmacion.close();
                }
            });
        } else {
            volverAlMenuPrincipal(event);
        }
    }

    private void volverAlMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("TipoDePartida.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ZombieStudentsLife");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            log.error("No se ha encontrado la vista del menú inicial");
        }
    }

    @FXML
    protected void onBottonGuardarPartidaClick(ActionEvent event) {
        guardarPartida(event);
    }

    @FXML
    protected void speedGame(ActionEvent event) {
        // Implement the functionality for speeding up the game
    }

    @FXML
    protected void onBottonGuardarComoClick(ActionEvent event) {
        guardarComo(event);
    }

    protected void guardarPartida(ActionEvent event) {
        if (datos.isPausado()) {
            if (datos.getRutaArchivo() == null) {
                guardarComo(event);
            } else {
                datos.guardarArchivo(datos.getRutaArchivo());
            }
        }
    }

    protected void guardarComo(ActionEvent event) {
        TextInputDialog getArchivoNombre = new TextInputDialog();
        getArchivoNombre.initOwner(Stage.getWindows().get(0));
        getArchivoNombre.setTitle("Guardar partida");
        getArchivoNombre.setHeaderText("Nombre de la partida:");
        getArchivoNombre.setContentText("Guardar como:");
        ButtonType botonGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        getArchivoNombre.getDialogPane().getButtonTypes().setAll(botonGuardar, botonCancelar);
        String nombreArchivo = getArchivoNombre.showAndWait().orElse("");
        int turnoActual = Integer.parseInt(turnoLabel.getText().replace("Turno: ", ""));
        datos.setTurnoActual(turnoActual);
        if (datos.getRutaArchivo() != null) {
            File archivoAntiguo = new File("archivos/" + datos.getRutaArchivo() + ".json");
            archivoAntiguo.delete();
        }
        datos.setRutaArchivo(nombreArchivo);
        datos.guardarArchivo(nombreArchivo);
    }

    protected void mostrarElementosCelda(Celda celda) {
        try {
            if (datos.isPausado()) {
                CeldaController celdaController = new CeldaController(datos, celda);
                Scene scene = new Scene(CeldaController.getNodo());
                Stage stage = new Stage();
                stage.setResizable(false);
                Stage ventana = ((Stage) Window.getWindows().get(0));
                stage.initOwner(ventana);
                stage.setScene(scene);
                int numeroVentanasAbiertas = 0;
                for (Window window : Window.getWindows()) {
                    numeroVentanasAbiertas++;
                }
                if (numeroVentanasAbiertas > 1) {
                    Stage ventanaPreviaCelda = ((Stage) Window.getWindows().get(1));
                    String textoLabelVentana = ((Label) ((GridPane) ((VBox) ventanaPreviaCelda.getScene().getRoot()).getChildren().get(0)).getChildren().get(0)).getText();
                    log.debug("Se ha cerrado la ventana de la celda " + textoLabelVentana.charAt(8) + ", " + textoLabelVentana.charAt(11));
                    ventanaPreviaCelda.close();
                }
                Bounds limitesCasilla = celda.getBoundsInLocal();
                Bounds limitesEscenaCasilla = celda.localToScene(limitesCasilla);
                Screen pantalla = Screen.getPrimary();
                Rectangle2D limitesPantalla = pantalla.getBounds();
                if (limitesEscenaCasilla.getMinX() < limitesPantalla.getWidth() / 2) {
                    stage.setX(limitesEscenaCasilla.getMinX());
                } else {
                    stage.setX(limitesEscenaCasilla.getMinX() - 220 - celda.getPrefWidth());
                }
                if (limitesEscenaCasilla.getMinY() < limitesPantalla.getHeight() / 2) {
                    stage.setY(limitesEscenaCasilla.getMinY() - 20);
                } else {
                    stage.setY(limitesEscenaCasilla.getMinY() - 240);
                }
                stage.show();
                log.debug("Se ha pulsado la celda" + celda);
            }
        } catch (IOException e) {
            log.error("No se ha encontrado la vista de elementosCelda");
        }
    }

    private GridPane crearGridTablero(Tablero tablero, Parent root) {
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();
        GridPane gridTablero = new GridPane();
        gridTablero.setHgap(1);
        gridTablero.setVgap(1);
        for (int i=0; i != filas; i++) {
            for (int j=0; j != columnas; j++) {
                Celda celda = tablero.getCelda(i, j);
                celda.setPrefHeight(((AnchorPane) root.getChildrenUnmodifiable().get(1)).getPrefHeight()/columnas);
                celda.setPrefWidth(((AnchorPane) root.getChildrenUnmodifiable().get(1)).getPrefWidth()/filas);
                ((Button) celda.getChildren().getFirst()).setOnAction(_ ->this.mostrarElementosCelda(celda));
                gridTablero.add(celda, i, j);
            }
        }
        return gridTablero;
    }

    protected void crearTablero(Tablero tablero) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tablero.fxml"));
        GridPane gridTablero = this.crearGridTablero(tablero, root);
        ((AnchorPane) root.getChildrenUnmodifiable().get(1)).getChildren().add(gridTablero);
        AnchorPane.setTopAnchor(gridTablero, 0.0);
        AnchorPane.setRightAnchor(gridTablero, 0.0);
        AnchorPane.setBottomAnchor(gridTablero, 0.0);
        AnchorPane.setLeftAnchor(gridTablero, 0.0);
        turnoLabel = (javafx.scene.text.Text) ((HBox) root.getChildrenUnmodifiable().get(2)).getChildren().getFirst();
        zombieStudentsLife.getPropiedad().actualizarTurnoProperty();
        turnoLabel.setText(String.valueOf(zombieStudentsLife.getPropiedad().getTurnoProperty().asString("Turno: %d")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.show();
        datos.setPausado(true);
        int numeroEstudiantes = datos.getEstudiantes().getNumeroElementos();
        for (int k=0; k != numeroEstudiantes; k++) {
            Estudiante estudiante = datos.getEstudiantes().getElemento(k).getData();
            Celda celda = tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM());
            celda.agregarEstudiante(estudiante, false);
            celda.getListaEstudiantes().add(estudiante);
        }
    }
}