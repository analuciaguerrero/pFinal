package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.EstudianteNoExistente;
import com.example.demoJavafx.excepciones.RecursoNoExistente;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Celda {
    private int posicionN;
    private int posicionM;
    private ListaEnlazada<Estudiante> listaEstudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Recursos> listaRecursos = new ListaEnlazada<>();
    private DatosJuego dato;
    private Tablero tablero;
    private Button botonCasilla = new Button();
    private GridPane gridElementos = new GridPane();
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
        botonCasilla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        AnchorPane.setTopAnchor(botonCasilla, 0.0);
        AnchorPane.setRightAnchor(botonCasilla, 0.0);
        AnchorPane.setBottomAnchor(botonCasilla, 0.0);
        AnchorPane.setLeftAnchor(botonCasilla, 0.0);
        AnchorPane.setTopAnchor(gridElementos, 0.0);
        AnchorPane.setRightAnchor(gridElementos, 0.0);
        AnchorPane.setBottomAnchor(gridElementos, 0.0);
        AnchorPane.setLeftAnchor(gridElementos, 0.0);
        gridElementos.setVgap(3);
        gridElementos.setHgap(8);
        gridElementos.setMouseTransparent(true);
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
    public Button getBotonCasilla() {
        return botonCasilla;
    }

    public GridPane getGridElementos() {
        return gridElementos;
    }
    public void agregarEstudiante(Estudiante estudiante) throws EstudianteNoExistente {
        try {
            if (listaEstudiantes.getNumeroElementos() < dato.getMaximoEstudiantesPorCelda()) {
                listaEstudiantes.add(estudiante);
            } else {
                throw new EstudianteNoExistente(listaEstudiantes.toString());
            }
        } catch (EstudianteNoExistente e) {
            log.error("Se ha intentado añadir más estudiantes de los permitidos");
        }
    }

    public void agregarRecurso(Recursos recurso) throws RecursoNoExistente {
        try {
            if (listaRecursos.getNumeroElementos() < dato.getMaximoRecursosPorCelda()) {
                listaRecursos.add(recurso);
            } else {
                throw new RecursoNoExistente(listaRecursos.toString());
            }
        } catch (RecursoNoExistente e) {
            log.error("Se ha intentado añadir más recursos de los permitidos");
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        int pos = listaEstudiantes.getPosicion(new ElementoLE<>(estudiante));
        if (pos != -1) {
            listaEstudiantes.delete(pos);
            dato.setNumEstudiantes(dato.getNumEstudiantes() - 1);
        } else {
            log.debug("Se ha querido eliminar un estudiante de una casilla en la que no estaba");
        }
    }

    public void eliminarRecurso(Recursos recurso) {
        int pos = listaRecursos.getPosicion(new ElementoLE<>(recurso));
        if (pos != -1) {
            listaRecursos.delete(pos);
            dato.setNumRecursos(dato.getNumRecursos() - 1);
        } else {
            log.debug("Se ha querido eliminar un recurso de una casilla en la que no estaba");
        }
    }
    public void crearCeldaAleatoria(DatosJuego dato) {
        try {
            Celda celdaAleatoria = dato.celdaAleatoria(dato.getFilasDelTablero(), dato.getColumnasDelTablero());
            if (celdaAleatoria != null) {
                int numEstudiantes = dato.generarEnteroAleatorio(1, dato.getMaximoEstudiantesPorCelda()); // Generar un número aleatorio de estudiantes entre 1 y el máximo permitido por celda
                for (int i = 0; i < numEstudiantes; i++) {
                    Estudiante estudianteAleatorio = dato.obtenerEstudianteAleatorio();
                    if (estudianteAleatorio != null) {
                        celdaAleatoria.agregarEstudiante(estudianteAleatorio);
                    }
                }
                int numRecursos = dato.generarEnteroAleatorio(1, dato.getMaximoRecursosPorCelda()); // Generar un número aleatorio de recursos entre 1 y el máximo permitido por celda
                for (int i = 0; i < numRecursos; i++) {
                    Recursos recursoAleatorio = dato.obtenerRecursoAleatorio();
                    if (recursoAleatorio != null) {
                        celdaAleatoria.agregarRecurso(recursoAleatorio);
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
    public void evaluarMejoras() {
        ElementoLE<Recursos> nodoRecurso = listaRecursos.getPrimero();
        while (nodoRecurso != null) {
            Recursos recurso = nodoRecurso.getData();
            ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
            while (nodoEstudiante != null) {
                Estudiante estudiante = nodoEstudiante.getData();
                Celda celda = tablero.getCelda(posicionN, posicionM);
                recurso.aplicarEfecto(estudiante, celda);
                log.info("Se aplicó el efecto del recurso " + recurso.toString() + " al estudiante " + estudiante.toString());
                nodoEstudiante = nodoEstudiante.getSiguiente();
            }
            nodoRecurso = nodoRecurso.getSiguiente();
        }
    }
    public void restablecerInterfazVisual() {
        gridElementos.getChildren().clear();
        gridElementos.getColumnConstraints().clear();
        gridElementos.getRowConstraints().clear();
        for (int i=0; i != listaEstudiantes.getNumeroElementos(); i++) {
            agregarEstudiante(listaEstudiantes.getElemento(i).getData());
        }
        for (int i=0; i != listaRecursos.getNumeroElementos(); i++) {
            agregarRecurso(listaRecursos.getElemento(i).getData());
        }
    }
}

