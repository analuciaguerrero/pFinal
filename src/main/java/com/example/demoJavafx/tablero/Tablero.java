package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estudiante.Estudiante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Tablero {
    protected int fila; //Número de filas
    protected int columna; //Número de columnas
    protected ListaSimple<ListaSimple<Celda>> celdas; //Matriz de celdas
    private DatosJuego dato;
    private static final Logger log = LogManager.getLogger(Tablero.class);

    public Tablero(DatosJuego datosJuego) {
        this.dato = datosJuego;
        this.fila = datosJuego.getFilasDelTablero();
        this.columna = datosJuego.getColumnasDelTablero();
        celdas = new ListaSimple<>();
        inicializarTablero();
    }
    public Tablero(int fila, int columna, DatosJuego dato) {
        this.fila = fila;
        this.columna = columna;
        this.dato = dato;
        celdas = new ListaSimple<>();
        for (int i = 0; i < fila; i++) {
            ListaSimple<Celda> filaCeldas = new ListaSimple<>();
            for (int j = 0; j < columna; j++) {
                filaCeldas.add(new Celda(i, j, dato, this));
            }
            celdas.add(filaCeldas);
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

    public void setCelda(int fila, int columna, Celda celda) {
        celdas.getElemento(fila).getData().setElemento(columna, celda);
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

    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }

    public void crearTableroAleatorio() {
        int numeroCuadrados = fila * columna;
        Random rand = new Random();

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                Celda celda = getCelda(i, j);

                // Agregar estudiante aleatorio
                Estudiante estudianteAleatorio = dato.obtenerEstudianteAleatorio();
                if (estudianteAleatorio != null) {
                    celda.agregarEstudiante(estudianteAleatorio);
                    log.info("Se ha agregado un estudiante aleatorio en la celda (" + i + ", " + j + ")");
                } else {
                    log.warn("No se pudo agregar un estudiante aleatorio en la celda (" + i + ", " + j + ")");
                }

                // Agregar recurso aleatorio
                Recursos tipoRecursoAleatorio = dato.obtenerRecursoAleatorio();
                if (tipoRecursoAleatorio != null) {
                    celda.agregarRecurso(tipoRecursoAleatorio);
                    log.info("Se ha agregado un recurso aleatorio en la celda (" + i + ", " + j + ")");
                } else {
                    log.warn("No se pudo agregar un recurso aleatorio en la celda (" + i + ", " + j + ")");
                }
            }
        }
    }
}
