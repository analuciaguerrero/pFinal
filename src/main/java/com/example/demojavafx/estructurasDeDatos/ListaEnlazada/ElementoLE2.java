package com.example.demojavafx.estructurasDeDatos.ListaEnlazada;

public class ElementoLE2<TipoDelDato> {
    private TipoDelDato data;
    private ElementoLE2<TipoDelDato> siguiente;

    // Siempre hay que poner el constructor
    public ElementoLE2(TipoDelDato o) {
        this.setData(o);
    }

    private void insertAfter(ElementoLE2<TipoDelDato> el) {
        this.siguiente = el;
    }

    public ElementoLE2<TipoDelDato> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(ElementoLE2<TipoDelDato> siguiente) {
        this.insertAfter(siguiente);
    }

    public TipoDelDato getData() {
        return this.data;
    }

    public void setData(TipoDelDato data) {
        this.data = data;
    }
}

