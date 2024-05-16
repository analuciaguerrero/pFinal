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
    private DatosJuego dato;
    private ZombieStudentsLife zombieStudentsLife;

    @FXML
    private Label turnoLabel = new Label();
    private static final Logger log = LogManager.getLogger(TableroController.class);

    public TableroController() {}
    public TableroController(DatosJuego dato, ZombieStudentsLife zombieStudentsLife) {
        this.zombieStudentsLife = zombieStudentsLife;
        this.dato = dato;
    }
    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }

    public ZombieStudentsLife getZombieStudentsLife() {
        return zombieStudentsLife;
    }

    public void setZombieStudentsLife(ZombieStudentsLife zombieStudentsLife) {
        this.zombieStudentsLife = zombieStudentsLife;
    }
    private void avanzarZombieStudentsLife (boolean unTurno, Celda celda) {
        if (Window.getWindows().size() < 3) {
            if (Window.getWindows().size() > 1) {
                Stage ventanaCasilla = (Stage) Window.getWindows().get(1);
                ventanaCasilla.close();
            }
            Tablero tab = celda.getTablero();
            ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(dato, tab);
            turnoLabel.textProperty().bind(zombieStudentsLife.getPropiedad().getTurnoProperty().asString("Turno: %d"));
            zombieStudentsLife.getPropiedad().actualizarTurnoProperty();
            zombieStudentsLife.start(unTurno);
        }
    }
    @FXML
    protected void onBottonPausarClick (ActionEvent event) {
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        dato.setPausado(true);
    }
    @FXML
    protected void onBottonPasarDeTurnoClick (ActionEvent event) {
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        if (dato.isPausado()) {
            avanzarZombieStudentsLife(true, celda);
        }
    }

    @FXML
    protected void onBottonReanudarClick (ActionEvent event) {
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        if (getDato().isPausado()) {
            dato.setPausado(false);
            avanzarZombieStudentsLife(false, celda);
        }
    }

    @FXML
    protected void onBottonConfiguracionToClick (ActionEvent event) throws IOException{
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        dato.setPausado(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajustes.fxml"));
        Parent root = loader.load();
        MenuAjustesController controller = loader.getController();
        controller.setControllerValues(dato);
        Stage stage = new Stage();
        Stage ventana = ((Stage) Window.getWindows().getFirst());
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
    protected void onBottonCerrarClick () {
        ((Stage) Window.getWindows().getFirst()).close();
    }

    @FXML
    protected void onBottonPantallaCompletaToClick () {
        Stage pantalla = ((Stage) Window.getWindows().getFirst());
        pantalla.setFullScreen(!pantalla.isFullScreen());
    }

    @FXML
    protected void onBottonMinimizarClick () {
        ((Stage) Window.getWindows().getFirst()).setIconified(true);
    }

    @FXML
    protected void onBottonMenuPrincipalClick (ActionEvent event) {
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        if (!getDato().isSave()) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.initOwner(Stage.getWindows().getFirst());
            confirmacion.setTitle("Volver al menú principal");
            confirmacion.setHeaderText("Estás a punto de volver al menú principal, perderás el progreso");
            confirmacion.setContentText("¿Quieres guardar la partida antes de salir?");
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

    private void volverAlMenuPrincipal (ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("TipoDePartida.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ZombieStudentsLife");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            log.error("No se ha encontrado la vista del menú principal");
        }
    }

    @FXML
    protected void onBottonGuardarPartidaClick (ActionEvent event) {
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        guardarPartida(event);
    }

    @FXML
    protected void onBottonGuardarComoClick (ActionEvent event) {
        Celda celda = ((Celda) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        dato = celda.getDatos();
        guardarComo(event);
    }
    protected void guardarPartida (ActionEvent event) {
        if (dato.isPausado()) {
            if (dato.getRutaArchivo() == null) {
                guardarComo(event);
            } else {
                dato.guardarArchivo(dato.getRutaArchivo());
            }
        }
    }
    protected void guardarComo(ActionEvent event) {
        TextInputDialog getArchivoNombre = new TextInputDialog();
        getArchivoNombre.initOwner(Stage.getWindows().getFirst());
        getArchivoNombre.setTitle("Guardar partida");
        getArchivoNombre.setHeaderText("¿Cómo quieres llamar a tu partida?");
        getArchivoNombre.setContentText("Guardar como:");
        ButtonType botonGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        getArchivoNombre.getDialogPane().getButtonTypes().setAll(botonGuardar, botonCancelar);
        String nombreArchivo = getArchivoNombre.showAndWait().get();
        Label labelTurno = (Label) ((HBox) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(2)).getChildren().getFirst();
        String labelString = labelTurno.getText();
        int turnoActual = Integer.parseInt(labelString.replace("Turno: ", ""));
        dato.setTurnoActual(turnoActual);
        if (dato.getRutaArchivo() != null) {
            File archivoAntiguo = new File("archivos/" + dato.getRutaArchivo() + ".json");
            archivoAntiguo.delete();
        }
        dato.setRutaArchivo(nombreArchivo);
        dato.guardarArchivo(nombreArchivo);
    }

    protected void mostrarElementosCelda(Celda celda) {
        try {
            if (dato.isPausado()) {
                CeldaController celdaController = new CeldaController(dato, celda);
                Scene scene = new Scene(CeldaController.getNodo());
                Stage stage = new Stage();
                stage.setResizable(false);
                Stage ventana = ((Stage) Window.getWindows().getFirst());
                stage.initOwner(ventana);
                stage.setScene(scene);
                int numeroVentanasAbiertas = 0;
                for (Window window : Window.getWindows()) {
                    numeroVentanasAbiertas++;
                }
                if (numeroVentanasAbiertas > 1) {
                    Stage ventanaPreviaCelda = ((Stage) Window.getWindows().get(1));
                    String textoLabelVentana = ((Label) ((GridPane) ((VBox) ventanaPreviaCelda.getScene().getRoot()).getChildren().getFirst()).getChildren().getFirst()).getText();
                    log.debug("Se ha cerrado la ventana de la casilla " + textoLabelVentana.charAt(8) + ", " + textoLabelVentana.charAt(11));
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

    protected void crearTablero (Tablero tablero) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("XTablero.fxml"));
        GridPane gridTablero = this.crearGridTablero(tablero, root);
        ((AnchorPane) root.getChildrenUnmodifiable().get(1)).getChildren().add(gridTablero);
        AnchorPane.setTopAnchor(gridTablero, 0.0);
        AnchorPane.setRightAnchor(gridTablero, 0.0);
        AnchorPane.setBottomAnchor(gridTablero, 0.0);
        AnchorPane.setLeftAnchor(gridTablero, 0.0);
        turnoLabel = (Label) ((HBox) root.getChildrenUnmodifiable().get(2)).getChildren().getFirst();
        zombieStudentsLife.getPropiedad().actualizarTurnoProperty();
        turnoLabel.textProperty().bind(zombieStudentsLife.getPropiedad().getTurnoProperty().asString("Turno: %d"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.show();
        dato.setPausado(true);
        int numeroIndividuos = dato.getEstudiantes().getNumeroElementos();
        for (int k=0; k != numeroIndividuos; k++) {
            Estudiante estudiante = dato.getEstudiantes().getElemento(k).getData();
            Celda celda = tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM());
            celda.agregarEstudiante(estudiante, false);
            celda.getListaEstudiantes().add(estudiante);
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
}
