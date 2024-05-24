package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Tablero {
    private DatosJuego dato;
    private ListaSimple<ListaSimple<Celda>> celdas; //Matriz de celdas mediante lista enlazada
    private static final Logger log = LogManager.getLogger("com.example");
    public Tablero(){}
    public Tablero(int filas, int columnas, DatosJuego dato) {
        celdas = new ListaSimple<>(filas);
        for (int i = 0; i < filas; i++) {
            ListaSimple<Celda> filaCeldas = new ListaSimple<>(columnas);
            celdas.setElemento(i, filaCeldas);
            for (int j = 0; j < columnas; j++) {
                Celda celda = new Celda(i, j, dato, this);
                filaCeldas.setElemento(j, celda);
            }
        }
    }


    // Obtener una celda específica del tablero
    public Celda getCelda(int[] posicion) {
        try {
            if (posicion.length != 2) throw new TamañoArrayInvalido();
            return celdas.getElemento(posicion[0]).getData().getElemento(posicion[1]).getData();
        } catch (TamañoArrayInvalido e) {
            log.error("Se ha tratado de obtener la posicion de una celda con array de tamaño distinto de 2");
            return null;
        }
    }
    public Celda getCelda (int fila, int columna) {
        return celdas.getElemento(fila).getData().getElemento(columna).getData();
    }

    public void setCelda(int fila, int columna, Celda celda) {
        celdas.getElemento(fila).getData().setElemento(columna, celda);
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

}
