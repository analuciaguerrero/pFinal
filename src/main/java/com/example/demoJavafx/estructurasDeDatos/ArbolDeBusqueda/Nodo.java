package com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda;

public class Nodo<TipoDelDato>{
    private Nodo<TipoDelDato> derecha;
    private Nodo<TipoDelDato> izquierda;
    private TipoDelDato dato;


    public Nodo(Nodo<TipoDelDato> derecha, Nodo<TipoDelDato> izquierda, TipoDelDato dato) {
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.dato = dato;
    }

    public Nodo(TipoDelDato dato) {
        this.dato = dato;
    }
    public Nodo(){}

    public Nodo<TipoDelDato> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<TipoDelDato> derecha) {
        this.derecha = derecha;
    }

    public Nodo<TipoDelDato> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<TipoDelDato> izquierda) {
        this.izquierda = izquierda;
    }

    public TipoDelDato getDato() {
        return dato;
    }

    public void setDato(TipoDelDato dato) {
        this.dato = dato;
    }
}
