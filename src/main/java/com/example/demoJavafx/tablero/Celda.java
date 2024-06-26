package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.EstudianteNoExistente;
import com.example.demoJavafx.excepciones.RecursoNoExistente;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demoJavafx.CeldaController;

import java.util.Random;

public class Celda extends AnchorPane {
    private int posicionN;
    private int posicionM;
    private ListaEnlazada<Estudiante> listaEstudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Recursos> listaRecursos = new ListaEnlazada<>();
    private DatosJuego dato;
    private Tablero tablero;
    private Button botonCelda = new Button();
    private GridPane gridElms = new GridPane();
    private static final Logger log = LogManager.getLogger(Celda.class);

    public Celda() {
        this.listaEstudiantes = new ListaEnlazada<>();
        this.listaRecursos = new ListaEnlazada<>();
    }
    public Celda(int posicionN, int posicionM) {
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        this.listaEstudiantes = new ListaEnlazada<>();
        this.listaRecursos = new ListaEnlazada<>();
    }
    public Celda(int posicionN, int posicionM, ListaEnlazada<Estudiante> listaEstudiantes, ListaEnlazada<Recursos> listaRecursos) {
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        this.listaEstudiantes = listaEstudiantes;
        this.listaRecursos = listaRecursos;
    }

    public Celda(ListaEnlazada<Estudiante> listaEstudiantes, ListaEnlazada<Recursos> listaRecursos) {
        this.listaEstudiantes = listaEstudiantes;
        this.listaRecursos = listaRecursos;
    }
    public Celda(int posicionN, int posicionM, DatosJuego dato, Tablero tablero) {
        this.posicionN = posicionN;
        this.posicionM = posicionM;
        this.dato = dato;
        this.tablero = tablero;
        botonCelda.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(botonCelda, 0.0);
        AnchorPane.setTopAnchor(botonCelda, 0.0);
        AnchorPane.setBottomAnchor(botonCelda, 0.0);
        AnchorPane.setRightAnchor(botonCelda, 0.0);
        AnchorPane.setLeftAnchor(gridElms, 0.0);
        AnchorPane.setRightAnchor(gridElms, 0.0);
        AnchorPane.setTopAnchor(gridElms, 0.0);
        AnchorPane.setBottomAnchor(gridElms, 0.0);
        gridElms.setVgap(3);
        gridElms.setHgap(8);
        gridElms.setMouseTransparent(true);
        getChildren().setAll(botonCelda, gridElms);
    }
    public int getPosicionN() {
        return posicionN;
    }

    public void setPosicionN(int posicionN) {
        this.posicionN = posicionN;
    }

    public int getPosicionM() {
        return posicionM;
    }

    public void setPosicionM(int posicionM) {
        this.posicionM = posicionM;
    }
    public int[] getPosicion () {
        return new int[]{posicionN, posicionM};
    }

    public ListaEnlazada<Recursos> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(ListaEnlazada<Recursos> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public ListaEnlazada<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ListaEnlazada<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    public DatosJuego getDatos() {
        return dato;
    }

    public void setDatos(DatosJuego dato) {
        this.dato = dato;
    }
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    public Button getBotonCelda() {
        return botonCelda;
    }

    public GridPane getGridElms() {
        return gridElms;
    }
    private ColumnConstraints columnaConPorcentajes (double porcentaje) {
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(porcentaje);
        return constraints;
    }

    private RowConstraints filaConPorcentajes (double porcentaje) {
        RowConstraints constraints = new RowConstraints();
        constraints.setPercentHeight(porcentaje);
        return constraints;
    }
    private void addIcEstudiante(ImageView vistaIc) {
        vistaIc.setPreserveRatio(true);
        vistaIc.setFitHeight(((GridPane) this.getParent()).getHeight() / (((GridPane) this.getParent()).getColumnCount() * 2));
        int numIcEstudiante = 0;
        for (Node node : gridElms.getChildren()) {
            if (GridPane.getRowIndex(node) == 0) {
                numIcEstudiante++;
            }
        }
        gridElms.add(vistaIc, numIcEstudiante, 0);
    }
    public void agregarEstudiante(Estudiante estudiante, boolean nuevoEstudiante) throws EstudianteNoExistente {
        try {
            if (nuevoEstudiante) estudiante.add(dato, this);
            ImageView vistaIc;
            switch (estudiante.getClass().getSimpleName()) {
                case "EstudianteBasico":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/estudianteBasico.jpeg").toExternalForm()));
                    break;
                case "EstudianteNormal":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/estudianteNormal.jpeg").toExternalForm()));
                    break;
                case "EstudianteAvanzado":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/estudianteAvanzado.jpeg").toExternalForm()));
                    break;
                default:
                    throw new EstudianteNoExistente();
            }
            addIcEstudiante(vistaIc);
        } catch (EstudianteNoExistente e) {
            log.error("Se ha intentado añadir un estudiante no existente");
        }
    }
    private void addIcRecurso (ImageView vistaIc) {
        vistaIc.setPreserveRatio(true);
        vistaIc.setFitWidth(((GridPane) this.getParent()).getWidth() / (((GridPane) this.getParent()).getColumnCount() * 2));
        vistaIc.setFitHeight(((GridPane) this.getParent()).getHeight() / (((GridPane) this.getParent()).getColumnCount() * 3));
        int numIcRecurso = 0;
        for (Node node : gridElms.getChildren()) {
            if (GridPane.getRowIndex(node) == 1) numIcRecurso++;
        }
        gridElms.add(vistaIc, numIcRecurso, 1);
    }
    public void agregarRecurso(Recursos recurso, boolean nuevoRecurso) throws RecursoNoExistente {
        try {
            if (nuevoRecurso) recurso.add(dato, this);
            ImageView vistaIc;
            switch (recurso.getClass().getSimpleName()) {
                case "Agua":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/agua.jpeg").toExternalForm()));
                    break;
                case "Comida":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/comida.jpeg").toExternalForm()));
                    break;
                case "Montaña":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/montana.jpeg").toExternalForm()));
                    break;
                case "Tesoro":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/tesoro.jpeg").toExternalForm()));
                    break;
                case "Biblioteca":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/biblioteca.jpeg").toExternalForm()));
                    break;
                case "Pozo":
                    vistaIc = new ImageView(new Image(CeldaController.class.getResource("imagenes/pozo.jpeg").toExternalForm()));
                    break;
                default:
                    throw new RecursoNoExistente();
            }
            addIcRecurso(vistaIc);
        } catch (RecursoNoExistente e) {
            log.error("Se ha intentado añadir un recurso que no existe");
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        for (int i = 0; i < listaEstudiantes.getNumeroElementos(); i++) {
            if (estudiante == listaEstudiantes.getElemento(i).getData()) {
                estudiante.morir(dato, this);
                restablecerInterfazVisual();
                return;
            }
        }
        log.debug("Se ha querido eliminar un estudiante de una casilla en la que no estaba");
    }

    public void eliminarRecurso(Recursos recurso) {
        for (int i = 0; i < listaRecursos.getNumeroElementos(); i++) {
            if (recurso == listaRecursos.getElemento(i).getData()) {
                recurso.del(dato, this);
                restablecerInterfazVisual();
                return;
            }
        }
        log.debug("Se ha querido eliminar un recurso de una casilla en la que no estaba");
    }
    public void crearCeldaAleatoria(DatosJuego dato) {
        try {
            Celda celdaAleatoria = dato.celdaAleatoria(dato.getFilasDelTablero(), dato.getColumnasDelTablero());
            if (celdaAleatoria != null) {
                int numEstudiantes = dato.generarEnteroAleatorio(1, dato.getMaximoEstudiantesPorCelda()); // Generar un número aleatorio de estudiantes entre 1 y el máximo permitido por celda
                for (int i = 0; i < numEstudiantes; i++) {
                    Estudiante estudianteAleatorio = dato.obtenerEstudianteAleatorio();
                    if (estudianteAleatorio != null) {
                        celdaAleatoria.agregarEstudiante(estudianteAleatorio, true);
                    }
                }
                int numRecursos = dato.generarEnteroAleatorio(1, dato.getMaximoRecursosPorCelda()); // Generar un número aleatorio de recursos entre 1 y el máximo permitido por celda
                for (int i = 0; i < numRecursos; i++) {
                    Recursos recursoAleatorio = dato.obtenerRecursoAleatorio();
                    if (recursoAleatorio != null) {
                        celdaAleatoria.agregarRecurso(recursoAleatorio, true);
                    }
                }
                log.info("Se ha creado una celda aleatoria en la posición: (" + celdaAleatoria.getPosicionN() + ", " + celdaAleatoria.getPosicionM() + ")");
            } else {
                log.warn("No se pudo crear una celda aleatoria porque el tablero está vacío.");
            }
        } catch (Exception e) {
            log.error("Error al crear una celda aleatoria: " + e.getMessage());
        }
    }

    public void eliminarEstudianteAleatorio() {
        ListaEnlazada<Estudiante> estudiantesDisponibles = new ListaEnlazada<>();
        // Agregar los estudiantes disponibles a la lista
        ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
        while (nodoEstudiante != null) {
            Estudiante estudiante = nodoEstudiante.getData();
            estudiantesDisponibles.add(estudiante);
            nodoEstudiante = nodoEstudiante.getSiguiente();
        }

        if (!estudiantesDisponibles.isVacia()) {
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(estudiantesDisponibles.getNumeroElementos());
            Estudiante estudianteAEliminar = estudiantesDisponibles.getElemento(indiceAleatorio).getData();
            // Eliminar el estudiante aleatorio
            listaEstudiantes.delete(listaEstudiantes.getPosicion(new ElementoLE<>(estudianteAEliminar)));
            dato.setNumEstudiantes(dato.getNumEstudiantes() - 1);
            log.info("Se ha eliminado aleatoriamente el estudiante: " + estudianteAEliminar.toString());
        } else {
            log.warn("No hay estudiantes disponibles en la lista para eliminar.");
        }
    }

    public void eliminarRecursoAleatorio() {
        ListaEnlazada<Recursos> recursosDisponibles = new ListaEnlazada<>();
        // Agregar los recursos disponibles a la lista
        ElementoLE<Recursos> nodoRecurso = listaRecursos.getPrimero();
        while (nodoRecurso != null) {
            Recursos recurso = nodoRecurso.getData();
            recursosDisponibles.add(recurso);
            nodoRecurso = nodoRecurso.getSiguiente();
        }

        if (!recursosDisponibles.isVacia()) {
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(recursosDisponibles.getNumeroElementos());
            Recursos recursoAEliminar = recursosDisponibles.getElemento(indiceAleatorio).getData();
            // Eliminar el recurso aleatorio
            listaRecursos.delete(listaRecursos.getPosicion(new ElementoLE<>(recursoAEliminar)));
            dato.setNumRecursos(dato.getNumRecursos() - 1);
            log.info("Se ha eliminado aleatoriamente el recurso: " + recursoAEliminar.toString());
        } else {
            log.warn("No hay recursos disponibles en la lista para eliminar.");
        }
    }
    public void restablecerInterfazVisual() {
        gridElms.getChildren().clear();
        gridElms.getColumnConstraints().clear();
        gridElms.getRowConstraints().clear();
        for (int i = 0; i < listaEstudiantes.getNumeroElementos(); i++) {
            agregarEstudiante(listaEstudiantes.getElemento(i).getData(), false);
        }
        for (int i = 0; i < listaRecursos.getNumeroElementos(); i++) {
            agregarRecurso(listaRecursos.getElemento(i).getData(), false);
        }
    }
}

