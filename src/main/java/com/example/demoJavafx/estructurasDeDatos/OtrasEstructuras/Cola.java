package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;

public class Cola<TipoDeDatos> {
    private ListaEnlazada<TipoDeDatos> datos = new ListaEnlazada<>();
    public Boolean isVacia () {
        return datos.isVacia();
    }

    public ListaEnlazada<TipoDeDatos> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<TipoDeDatos> datos) {
        this.datos = datos;
    }

    public void add (TipoDeDatos e) {
        datos.add(e);
    }
    public TipoDeDatos poll () {
        TipoDeDatos primero = datos.getPrimero().getData();
        datos.delete(0);
        return primero;
    }
    public TipoDeDatos peek () {
        return datos.getPrimero().getData();
    }
    public int getNumeroDatos() {
        return datos.getNumeroElementos();
    }
    @Override
    public String toString() {
        return datos.toString();
    }
}


