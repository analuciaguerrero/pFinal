package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demoJavafx.game.Game.tablero;

public class Tablero {
    protected int fila; //Número de filas
    protected int columna; //Número de columnas
    protected ListaEnlazada<ListaEnlazada<Celda>> celdas; //Matriz de celdas
    protected static final int maxEstudiantesPorCelda = 3;
    protected static final int maxRecursosPorCelda = 3;
    private static final Logger log = LogManager.getLogger(Tablero.class);

    public Tablero(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        celdas = new ListaEnlazada<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Crear las celdas y agregarlas al tablero
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                celdas.add(new Celda(i, j));
            }
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

    public void crearTableroAleatorio() {
        Integer numeroCuadrados = tablero.getSquares().getNumeroElementos();
        if (numeroCuadrados == 1) {
            addEstudiante(tablero.getSquare(0));
            int tipoRecurso = generarEnteroAleatorio((int) 2.0, (int) 7.0);
            addRecursos(tablero.getSquare(0), (double) tipoRecurso);
        } else if (numeroCuadrados == 2) {
            int numerocuadrado = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado));
            int numerocuadrado2 = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado2));
            int tipoRecurso = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), (double) tipoRecurso);
            int tipoRecurso2 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado2), (double) tipoRecurso2);
            int tipoRecurso3 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), (double) tipoRecurso3);
        } else {
            int numIndividuos = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 2);
            int numAgua = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numComida = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numMontana = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numBiblioteca = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numTesoro = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numPozo = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            for (int i = 0; i < numIndividuos; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addIndividuo(tablero.getSquare(idSquareAleatorio));
            }
            for (int i = 0; i < numAgua; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 2.0);
            }
            for (int i = 0; i < numComida; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 3.0);
            }
            for (int i = 0; i < numMontana; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 4.0);
            }
            for (int i = 0; i < numBiblioteca; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 5.0);
            }
            for (int i = 0; i < numTesoro; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 6.0);
            }
            for (int i = 0; i < numPozo; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 7.0);
            }
        }
    }

    private int generarEnteroAleatorio(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}

