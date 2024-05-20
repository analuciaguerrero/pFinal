package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estudiante.Estudiante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Tablero {
    private int fila;
    private int columna;
    private DatosJuego dato;
    private ListaSimple<ListaSimple<Celda>> celdas; //Matriz de celdas mediante lista enlazada
    private static final Logger log = LogManager.getLogger("com.example");

    public Tablero(DatosJuego dato) {
        this.dato = dato;
        this.fila = dato.getFilasDelTablero();
        this.columna = dato.getColumnasDelTablero();
        celdas = new ListaSimple<>();
        inicializarTablero();
    }
    public Tablero(int fila, int columna, DatosJuego dato) {
        this.fila = fila;
        this.columna = columna;
        this.dato = dato;
        celdas = new ListaSimple<>();
        for (int i = 0; i != fila; i++) {
            celdas.insert(new ListaSimple<>(columna), i);
            for (int j = 0; j != columna; j++) {
                celdas.getElemento(i).getData().insert(new Celda(i, j, dato, this), j);
            }
        }
    }

    private void inicializarTablero() {
        // Crear las celdas y agregarlas al tablero
        for (int i = 0; i < fila; i++) {
            ListaSimple<Celda> filaCeldas = new ListaSimple<>();
            for (int j = 0; j < columna; j++) {
                filaCeldas.add(new Celda(i, j));
            }
            celdas.add(filaCeldas);
        }
    }

    // Obtener una celda específica del tablero
    public Celda getCelda(int fila, int columna) {
        return celdas.getElemento(fila).getData().getElemento(columna).getData();
    }

    public void setCelda(int fila, int columna) {
        celdas.getElemento(fila).getData().getElemento(columna).getData();
    }

    public int getFilas() {
        return fila;
    }

    public void setFilas(int fila) {
        this.fila = fila;
    }

    public int getColumnas() {
        return columna;
    }

    public void setColumnas(int columna) {
        this.columna = columna;
    }

    public ListaSimple<ListaSimple<Celda>> getCeldas() {
        return celdas;
    }

    public void setCeldas(ListaSimple<ListaSimple<Celda>> celdas) {
        this.celdas = celdas;
    }
    public int getNumFilas(){
        return celdas.getNumeroElementos();
    }
    public int getNumColumnas(){
        return celdas.getPrimero().getData().getNumeroElementos();
    }
    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }

    public void crearTableroAleatorio() {
        if (fila <= 0 || columna <= 0) {
            log.error("Número de filas o columnas no válido.");
            return;
        }
        int numeroCuadrados = fila * columna;
        Random rand = new Random();

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                Celda celda = getCelda(i, j);

                // Agregar estudiante aleatorio
                Estudiante estudianteAleatorio = dato.obtenerEstudianteAleatorio();
                if (estudianteAleatorio != null) {
                    celda.agregarEstudiante(estudianteAleatorio, true);
                    log.info("Se ha agregado un estudiante aleatorio en la celda (" + i + ", " + j + ")");
                } else {
                    log.warn("No se pudo agregar un estudiante aleatorio en la celda (" + i + ", " + j + ")");
                }

                // Agregar recurso aleatorio
                Recursos tipoRecursoAleatorio = dato.obtenerRecursoAleatorio();
                if (tipoRecursoAleatorio != null) {
                    celda.agregarRecurso(tipoRecursoAleatorio, true);
                    log.info("Se ha agregado un recurso aleatorio en la celda (" + i + ", " + j + ")");
                } else {
                    log.warn("No se pudo agregar un recurso aleatorio en la celda (" + i + ", " + j + ")");
                }
            }
        }
    }
}
