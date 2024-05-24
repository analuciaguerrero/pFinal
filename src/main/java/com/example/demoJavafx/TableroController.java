package com.example.demoJavafx;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.Nodo;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Mapa;
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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class TableroController {
    private DatosJuego datos;
    private ZombieStudentsLife zombieStudentsLife;

    @FXML
    private Label turnoLabel = new Label();

    private static final Logger log = LogManager.getLogger();

    public DatosJuego getDatos() {
        return datos;
    }

    public void setDatos(DatosJuego datos) {
        this.datos = datos;
    }

    public ZombieStudentsLife getZombieStudentsLife() {
        return zombieStudentsLife;
    }

    public void setZombieStudentsLife(ZombieStudentsLife zombieStudentsLife) {
        this.zombieStudentsLife = zombieStudentsLife;
    }

    public TableroController(DatosJuego datos, ZombieStudentsLife zombieStudentsLife) {
        this.datos = datos;
        this.zombieStudentsLife = zombieStudentsLife;
    }

    public TableroController() {
    }

    private void avanzarZombieStudentsLife(boolean unTurno, Celda celda) {
        if (Window.getWindows().size() < 3) {
            if (Window.getWindows().size() > 1) {
                Stage ventanaCelda = (Stage) Window.getWindows().get(1);
                ventanaCelda.close();
            }
            Tablero tablero = celda.getTablero();
            ZombieStudentsLife juego = new ZombieStudentsLife(datos, tablero);
            turnoLabel.textProperty().bind(juego.getBucle().getTurnoProperty().asString("Turno: %d"));
            juego.getBucle().actualizarTurnoProperty();
            juego.start(unTurno);
        }
    }

    @FXML
    protected void onBottonPausarClick(ActionEvent event) {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        datos.setPausado(true);
    }

    @FXML
    protected void onBottonPasarDeTurnoClick(ActionEvent event) {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        if (datos.isPausado()) {
            avanzarZombieStudentsLife(true, celda);
        }
    }

    @FXML
    protected void onBottonReanudarClick(ActionEvent event) {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        if (getDatos().isPausado()) {
            datos.setPausado(false);
            avanzarZombieStudentsLife(false, celda);
        }
    }

    @FXML
    protected void onBottonConfiguracionToClick(ActionEvent event) throws IOException {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        datos.setPausado(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Personalizacion2.fxml"));
        Parent root = loader.load();
        XPersonalizacionController controller = loader.getController();
        controller.setControllerValues(datos);
        Stage stage = new Stage();
        Stage ventanaTablero = ((Stage) Stage.getWindows().get(0));
        stage.initOwner(ventanaTablero);
        stage.setResizable(false);
        if (Stage.getWindows().size() > 1) {
            Stage ventanaCelda = (Stage) Stage.getWindows().get(1);
            ventanaCelda.close();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onBottonCerrarClick() {
        ((Stage) Stage.getWindows().get(0)).close();
    }

    @FXML
    protected void onBottonPantallaCompletaToClick() {
        Stage pantalla = ((Stage) Stage.getWindows().get(0));
        pantalla.setFullScreen(!pantalla.isFullScreen());
    }

    @FXML
    protected void onBottonMinimizarClick() {
        ((Stage) Stage.getWindows().get(0)).setIconified(true);
    }

    @FXML
    protected void onBottonMenuInicialClick(ActionEvent event) {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        if (!getDatos().isSave() && datos.isPausado()) {
            if (Stage.getWindows().size() > 1) ((Stage) Stage.getWindows().get(1)).close();
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.initOwner(Stage.getWindows().get(0));
            confirmacion.setTitle("Volver al menú inicial");
            confirmacion.setHeaderText("Estás a punto de volver al menú inicial y perderás todo tu progreso guardado");
            confirmacion.setContentText("¿Deseas guardar la partida antes de salir?");
            ButtonType botonGuardar = new ButtonType("Guardar");
            ButtonType botonNoGuardar = new ButtonType("No guardar");
            ButtonType botonCancelar = new ButtonType("Cancelar");
            confirmacion.getButtonTypes().setAll(botonGuardar, botonNoGuardar, botonCancelar);
            confirmacion.showAndWait().ifPresent(answ -> {
                if (answ == botonGuardar) {
                    guardarPartida(event);
                    volverAlMenuInicial(event);
                } else if (answ == botonNoGuardar) {
                    volverAlMenuInicial(event);
                } else {
                    confirmacion.close();
                }
            });
        } else {
            volverAlMenuInicial(event);
        }
    }

    private void volverAlMenuInicial(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("MenuInicial.fxml"));
            Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
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
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        guardarPartida(event);
    }

    @FXML
    protected void onBottonGuardarComoClick(ActionEvent event) {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
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
        String nombreArchivo = getArchivoNombre.showAndWait().orElse(null);
        Label labelTurno = (Label) ((AnchorPane) ((Parent) event.getSource()).getScene().getRoot()).getChildrenUnmodifiable().get(2);
        String labelString = labelTurno.getText();
        int turnoActual = Integer.parseInt(labelString.replace("Turno: ", ""));
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
                Scene scene = new Scene(celdaController.getRoot());
                Stage stage = new Stage();
                stage.setResizable(false);
                Stage ventana = ((Stage) Stage.getWindows().get(0));
                stage.initOwner(ventana);
                stage.setScene(scene);
                int numeroVentanasAbiertas = Stage.getWindows().size();
                if (numeroVentanasAbiertas > 1) {
                    Stage ventanaPreviaCelda = ((Stage) Stage.getWindows().get(1));
                    String textoLabelVentana = ((Label) ((GridPane) ((VBox) ventanaPreviaCelda.getScene().getRoot()).getChildren().get(0)).getChildren().get(0)).getText();
                    log.debug("Se ha cerrado la ventana de la celda " + textoLabelVentana.charAt(8) + ", " + textoLabelVentana.charAt(11));
                    ventanaPreviaCelda.close();
                }
                stage.show();
                log.debug("Se ha pulsado la celda" + celda);
            }
        } catch (IOException e) {
            log.error("No se ha encontrado la vista de elementosCelda");
        }
    }

    private GridPane crearGridTablero(Tablero tablero, Parent root) {
        int filas = tablero.getNumFilas();
        int columnas = tablero.getNumColumnas();
        GridPane gridTablero = new GridPane();
        gridTablero.setHgap(1);
        gridTablero.setVgap(1);
        for (int i = 0; i != filas; i++) {
            for (int j = 0; j != columnas; j++) {
                Celda celda = tablero.getCelda(i, j);
                celda.setPrefHeight(((AnchorPane) root.getChildrenUnmodifiable().get(1)).getPrefHeight() / columnas);
                celda.setPrefWidth(((AnchorPane) root.getChildrenUnmodifiable().get(1)).getPrefWidth() / filas);
                celda.getBotonCelda().setOnAction(event -> mostrarElementosCelda(celda));
                gridTablero.add(celda, i, j);
            }
        }
        return gridTablero;
    }

    protected void crearTablero(Tablero tablero) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
        Parent root = loader.load();
        GridPane gridTablero = this.crearGridTablero(tablero, root);
        ((AnchorPane) root.getChildrenUnmodifiable().get(1)).getChildren().add(gridTablero);
        AnchorPane.setTopAnchor(gridTablero, 0.0);
        AnchorPane.setRightAnchor(gridTablero, 0.0);
        AnchorPane.setBottomAnchor(gridTablero, 0.0);
        AnchorPane.setLeftAnchor(gridTablero, 0.0);
        turnoLabel = (Label) ((AnchorPane) root.getChildrenUnmodifiable().get(2)).getChildren().get(0);
        zombieStudentsLife.getBucle().actualizarTurnoProperty();
        turnoLabel.textProperty().bind(zombieStudentsLife.getBucle().getTurnoProperty().asString("Turno: %d"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.show();
        datos.setPausado(true);
        int numEstudiantes = datos.getEstudiantes().getNumeroElementos();
        for (int k = 0; k != numEstudiantes; k++) {
            Estudiante estudiante = datos.getEstudiantes().getElemento(k).getData();
            Celda celda = tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM());
            celda.agregarEstudiante(estudiante, false);
            celda.getListaEstudiantes().add(estudiante);
        }
        int numRecursos = datos.getRecursos().getNumeroElementos();
        for (int m = 0; m != numRecursos; m++) {
            Recursos rec = datos.getRecursos().getElemento(m).getData();
            Celda celda = tablero.getCelda(rec.getPosicionN(), rec.getPosicionM());
            celda.agregarRecurso(rec, false);
            celda.getListaRecursos().add(rec);
        }
    }

    private static TreeItem<Estudiante> crearArbolGenealogico(BST<Estudiante> arbol) {
        TreeItem<Estudiante> raiz = new TreeItem<>(arbol.getRaiz().getDato());
        if (arbol.getAltura() > 1) {
            crearArbolGenealogicoPrinc(arbol.getRaiz().getDerecha(), raiz);
            crearArbolGenealogicoPrinc(arbol.getRaiz().getIzquierda(), raiz);
        }
        return raiz;
    }

    private static void crearArbolGenealogicoPrinc(Nodo<Estudiante> nodo, TreeItem<Estudiante> raiz) {
        TreeItem<Estudiante> it = new TreeItem<>(nodo.getDato());
        raiz.getChildren().add(it);
        if (nodo.getDerecha() != null & nodo.getIzquierda() != null) {
            crearArbolGenealogicoPrinc(nodo.getDerecha(), it);
            crearArbolGenealogicoPrinc(nodo.getIzquierda(), it);
        }
    }

    @FXML
    protected void onBottonFinalizarPartidaClick(ActionEvent event) {
        Celda celda = obtenerCeldaDeEvento(event);
        datos = celda.getDatos();
        if (!getDatos().isSave() && datos.isPausado()) {
            if (Stage.getWindows().size() > 1) ((Stage) Stage.getWindows().get(1)).close();
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
            conf.initOwner(Stage.getWindows().get(0));
            conf.setTitle("Finalizar partida");
            conf.setHeaderText("Estás a punto de salir de la partida, perderás el progreso");
            conf.setContentText("¿Quieres guardar la partida antes de salir?");
            ButtonType botonGuardar = new ButtonType("Guardar");
            ButtonType botonNoGuardar = new ButtonType("No guardar");
            ButtonType botonCancelar = new ButtonType("Cancelar");
            conf.getButtonTypes().setAll(botonGuardar, botonNoGuardar, botonCancelar);
            conf.showAndWait().ifPresent(ans -> {
                if (ans == botonGuardar) {
                    guardarPartida(event);
                    terminarPartida(datos);
                } else if (ans == botonNoGuardar) {
                    terminarPartida(datos);
                } else {
                    conf.close();
                }
            });
        } else {
            terminarPartida(datos);
        }
    }

    public static void terminarPartida(DatosJuego dato) {
        ZombieStudentsLife juego = new ZombieStudentsLife(dato, true);
        juego.finalizarPartida();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("PantallaFinal.fxml"));
            Parent root = fxmlLoader.load();
            HBox paneGan = (HBox) ((AnchorPane) ((ScrollPane) ((VBox) root).getChildren().get(2)).getContent()).getChildren().getFirst();
            HBox.setHgrow(paneGan, Priority.ALWAYS);
            AnchorPane.setTopAnchor(paneGan, 0.0);
            AnchorPane.setBottomAnchor(paneGan, 0.0);
            AnchorPane.setLeftAnchor(paneGan, 0.0);
            AnchorPane.setRightAnchor(paneGan, 0.0);
            Mapa<Estudiante, BST<Estudiante>> arbolGenealogico = juego.getArbolGenealogico();
            int numGan = dato.getEstudiantes().getNumeroElementos();
            for (int i = 0; i != numGan; i++) {
                Estudiante est = dato.getEstudiantes().getElemento(i).getData();
                BST<Estudiante> arbol = arbolGenealogico.get(est);
                TreeView<Estudiante> vistaGan = new TreeView<>();
                TreeItem<Estudiante> raiz = new TreeItem<>();
                raiz.getChildren().add(crearArbolGenealogico(arbol));
                vistaGan.setRoot(raiz);
                vistaGan.setShowRoot(false);
                vistaGan.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                vistaGan.setCellFactory(_ -> new TreeCell<>() {
                    @Override
                    protected void updateItem(Estudiante estudiante, boolean vacio) {
                        super.updateItem(estudiante, vacio);
                        if (vacio) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            if (estudiante != null) {
                                setText(String.format("Estudiante %d", estudiante.getId()));
                                setFont(new Font("Bookman Old Style", 18));
                            }
                        }
                    }
                });
                paneGan.getChildren().add(vistaGan);
                HBox.setHgrow(vistaGan, Priority.ALWAYS);
            }
            while (!Stage.getWindows().isEmpty()) {
                ((Stage) Stage.getWindows().get(0)).close();
            }
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setIconified(false);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            log.error("No se ha podido encontrar el menú inicial");
        }
    }

    @FXML
    protected void onBottonSalirClick(ActionEvent event) {
        volverAlMenuInicial(event);
    }

    private Celda obtenerCeldaDeEvento(ActionEvent event) {
        return (Celda) ((GridPane) ((AnchorPane) ((Parent) event.getSource()).getScene().getRoot()).getChildren().get(1)).getChildren().get(0);
    }

    public void inicializar(DatosJuego datos, ZombieStudentsLife zombieStudentsLife) {
        this.datos = datos;
        this.zombieStudentsLife = zombieStudentsLife;
    }
}