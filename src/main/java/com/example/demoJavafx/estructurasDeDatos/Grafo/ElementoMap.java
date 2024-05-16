package com.example.demoJavafx.estructurasDeDatos.Grafo;

public class ElementoMap<T,E> {
    private T clave;
    private E dato;

    public ElementoMap(T clave, E dato) {
        this.clave = clave;
        this.dato = dato;
    }

    public T getClave() {
        return clave;
    }

    public void setClave(T clave) {
        this.clave = clave;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
}
