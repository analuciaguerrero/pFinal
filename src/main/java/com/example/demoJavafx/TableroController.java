package com.example.demoJavafx;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            turnoLabel.textProperty().bind(zombieStudentsLife.getBucle().getTurnoProperty().asString("Turno: %d"));
            zombieStudentsLife.getBucle().updateTurnoProperty();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menuConfiguracionPausa-vista.fxml"));
        Parent root = loader.load();
        menuConfiguracionController controller = loader.getController();
        controller.setControllerValues(model);
        Stage stage = new Stage();
        Stage ventanaTablero = ((Stage) Window.getWindows().getFirst());
        stage.initOwner(ventanaTablero);
        stage.setResizable(false);
        if (Window.getWindows().size() > 1) {
            Stage ventanaCasila = (Stage) Window.getWindows().get(1);
            ventanaCasila.close();
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
            FXMLLoader fxmlLoader = new FXMLLoader(menuPrincipalApplication.class.getResource("menuPrincipal-vista.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Simulador de Vida");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            log.error("No se ha encontrado la vista del menú principal");
        }
    }

    @FXML
    protected void onBotonGuardarPartidaClick (ActionEvent event) {
        casillaTablero casilla00 = ((casillaTablero) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        model = casilla00.getModel();
        guardarPartida(event);
    }

    @FXML
    protected void onBotonGuardarComoClick (ActionEvent event) {
        casillaTablero casilla00 = ((casillaTablero) ((GridPane) ((AnchorPane) ((Node) event.getSource()).getScene().getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().getFirst()).getChildren().getFirst());
        model = casilla00.getModel();
        guardarComo(event);
    }

    protected void guardarPartida (ActionEvent event) {
        if (model.isPausado()) {
            if (model.getNombreArchivo() == null) {
                guardarComo(event);
            } else {
                model.guardar(model.getNombreArchivo());
            }
        }
    }

    protected void guardarComo (ActionEvent event) {
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
        model.setTurno(turnoActual);

        if (model.getNombreArchivo() != null) {
            File archivoAntiguo = new File("archivosDePartida/" + model.getNombreArchivo() + ".json");
            archivoAntiguo.delete();
        }

        model.setNombreArchivo(nombreArchivo);
        model.guardar(nombreArchivo);
    }

    protected void mostrarElementosCasilla (casillaTablero casilla) {
        try {
            if (model.isPausado()) {
                casillaController casillaController = new casillaController(model, casilla);

                Scene scene = new Scene(casillaController.getRoot());
                Stage stage = new Stage();
                stage.setResizable(false);

                Stage ventanaTablero = ((Stage) Window.getWindows().getFirst());
                stage.initOwner(ventanaTablero);

                stage.setScene(scene);

                int numeroVentanasAbiertas = 0;
                for (Window window : Window.getWindows()) {
                    numeroVentanasAbiertas++;
                }
                if (numeroVentanasAbiertas > 1) {
                    Stage ventanaPreviaCasilla = ((Stage) Window.getWindows().get(1));
                    String textoLabelVentana = ((Label) ((GridPane) ((VBox) ventanaPreviaCasilla.getScene().getRoot()).getChildren().getFirst()).getChildren().getFirst()).getText();
                    log.debug("Se ha cerrado la ventana de la casilla " + textoLabelVentana.charAt(8) + ", " + textoLabelVentana.charAt(11));
                    ventanaPreviaCasilla.close();
                }


                Bounds limitesCasilla = casilla.getBoundsInLocal();
                Bounds limitesEscenaCasilla = casilla.localToScene(limitesCasilla);
                Screen pantalla = Screen.getPrimary();
                Rectangle2D limitesPantalla = pantalla.getBounds();


                if (limitesEscenaCasilla.getMinX() < limitesPantalla.getWidth() / 2) {
                    stage.setX(limitesEscenaCasilla.getMinX());
                } else {
                    stage.setX(limitesEscenaCasilla.getMinX() - 220 - casilla.getPrefWidth());
                }
                if (limitesEscenaCasilla.getMinY() < limitesPantalla.getHeight() / 2) {
                    stage.setY(limitesEscenaCasilla.getMinY() - 20);
                } else {
                    stage.setY(limitesEscenaCasilla.getMinY() - 240);
                }

                stage.show();
                log.debug("Se ha pulsado la casilla" + casilla);
            }
        } catch (IOException e) {
            log.error("No se ha encontrado la vista de elementosCasilla");
        }
    }

    protected void crearTablero (tablero tablero) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tablero-vista.fxml"));
        GridPane gridTablero = this.crearGridTablero(tablero, root);

        ((AnchorPane) root.getChildrenUnmodifiable().get(1)).getChildren().add(gridTablero);
        AnchorPane.setTopAnchor(gridTablero, 0.0);
        AnchorPane.setRightAnchor(gridTablero, 0.0);
        AnchorPane.setBottomAnchor(gridTablero, 0.0);
        AnchorPane.setLeftAnchor(gridTablero, 0.0);

        turnoLabel = (Label) ((HBox) root.getChildrenUnmodifiable().get(2)).getChildren().getFirst();
        juegoActual.getBucle().updateTurnoProperty();
        turnoLabel.textProperty().bind(juegoActual.getBucle().getTurnoProperty().asString("Turno: %d"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.show();

        model.setPausado(true);
        int numeroIndividuos = model.getIndividuos().getNumeroElementos();
        for (int k=0; k != numeroIndividuos; k++) {
            individuo individuoActual = model.getIndividuos().getElemento(k).getData();
            casillaTablero casillaActual = tablero.getCasilla(individuoActual.getPosicion());
            casillaActual.addIndividuo(individuoActual, false);
            casillaActual.getIndividuos().add(individuoActual);
        }
    }

    private GridPane crearGridTablero(tablero tablero, Parent root) {
        int casillasN = tablero.getNumeroCasillasN();
        int casillasM = tablero.getNumeroCasillasM();
        GridPane gridTablero = new GridPane();
        gridTablero.setHgap(1);
        gridTablero.setVgap(1);
        for (int i=0; i != casillasN; i++) {
            for (int j=0; j != casillasM; j++) {
                casillaTablero casilla = tablero.getCasilla(i, j);
                casilla.setPrefHeight(((AnchorPane) root.getChildrenUnmodifiable().get(1)).getPrefHeight()/casillasM);
                casilla.setPrefWidth(((AnchorPane) root.getChildrenUnmodifiable().get(1)).getPrefWidth()/casillasN);
                ((Button) casilla.getChildren().getFirst()).setOnAction(_ ->this.mostrarElementosCasilla(casilla));
                gridTablero.add(casilla, i, j);
            }
        }

        return gridTablero;
    }
}
