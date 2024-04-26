package com.example.demojavafx.estructurasDeDatos.OtrasEstructuras;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ElementoLE2;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada2;

public class Cola<Dato> {
    private ListaEnlazada2<Dato> datos;

    public Cola() {
        datos = new ListaEnlazada2<>();
    }

    public void push(Dato Elemento) {
        datos.add(new ElementoLE2<>(Elemento));
    }

    public Dato pull() {
        Dato borrado = datos.getElemento(0).getData();
        datos.del(0);
        return borrado;
    }

    public boolean esVacia() {
        return datos.isVacia();
    }
}

