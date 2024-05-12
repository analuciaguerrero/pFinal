package com.example.demoJavafx.zombieStudentsLife;

public class Matriz {
    private int filas;
    private int columnas;
    private boolean ocupada;
    private Celda matriz[][];
    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new Celda[filas][columnas];
        this.ocupada = false;
        inicializarMatriz();
    }
    private void inicializarMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new Celda();
            }
        }
    }
    public int getFilas(){
        return filas;
    }
    public void setFilas(int filas){
        this.filas = filas;
    }
    public int getColumnas(){
        return columnas;
    }
    public void setColumnas(int columnas){
        this.columnas = columnas;
    }
    public boolean estaOcupada(){
        return ocupada;
    }
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }
    public Celda getCelda(int fila, int columna) {
        return matriz[fila][columna];
    }
    public void setCelda(int fila, int columna, Celda celda) {
        matriz[fila][columna] = celda;
    }
}
