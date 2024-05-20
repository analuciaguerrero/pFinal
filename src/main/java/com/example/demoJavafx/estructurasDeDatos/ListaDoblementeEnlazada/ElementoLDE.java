package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ElementoLDE<TipoDeDatos> {
    private TipoDeDatos data;
    private ElementoLDE<TipoDeDatos> siguiente;
    private ElementoLDE<TipoDeDatos> anterior;
    public ElementoLDE(ElementoLDE anterior) {
        this.anterior = anterior;
    }
    public ElementoLDE(ElementoLDE anterior, ElementoLDE siguiente,TipoDeDatos data) {
        this.data = data;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
    public ElementoLDE(TipoDeDatos data) {
        this.data = data;
    }
    public ElementoLDE(){}

    public void insertarmeEn(ElementoLDE<TipoDeDatos> el) {
        this.siguiente = el.siguiente;
        this.anterior = el;
        el.siguiente = this;
        if (this.siguiente != null)
            this.siguiente.anterior = this;
    }

    public ElementoLDE<TipoDeDatos> getSiguiente() {
        return siguiente;
    }

    public ElementoLDE<TipoDeDatos> getAnterior() {
        return anterior;
    }

    public void setSiguiente(ElementoLDE<TipoDeDatos> el) {
        this.siguiente = el;
    }

    public void setAnterior(ElementoLDE<TipoDeDatos> el) {
        this.anterior = el;
    }


    public TipoDeDatos getData() {
        return this.data;
    }

    public void setData(TipoDeDatos dato) {
        this.data = dato;
    }
}
