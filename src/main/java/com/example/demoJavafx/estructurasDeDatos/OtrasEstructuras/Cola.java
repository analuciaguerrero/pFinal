package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;

public class Cola<Dato> {
    private ListaEnlazada<Dato> datos;

    public Cola() {
        datos = new ListaEnlazada<>();
    }
    public ListaEnlazada<Dato> getDatos() {
        return datos;
    }
    public void push(Dato Elemento) {
        datos.add(new ElementoLE<>(Elemento));
    }

    public Dato pull() {
        Dato borrado = datos.getElemento(0).getData();
        datos.delete(0);
        return borrado;
    }
    public boolean esVacia() {
        return datos.isVacia();
    }
}


