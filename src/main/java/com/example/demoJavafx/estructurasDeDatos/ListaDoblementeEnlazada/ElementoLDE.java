package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ElementoLDE<Tipo> {
    private Tipo data;
    private ElementoLDE<Tipo> siguiente;
    private ElementoLDE<Tipo> anterior;
    public ElementoLDE(ElementoLDE anterior) {
        this.anterior = anterior;
    }
    public ElementoLDE(ElementoLDE anterior, ElementoLDE siguiente,Tipo data) {
        this.data = data;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
    public ElementoLDE(Tipo data) {
        this.data = data;
    }
    public ElementoLDE(){}

    public void insertarmeEn(ElementoLDE<Tipo> el) {
        this.siguiente = el.siguiente;
        this.anterior = el;
        el.siguiente = this;
        if (this.siguiente != null)
            this.siguiente.anterior = this;
    }

    public ElementoLDE<Tipo> getSiguiente() {
        return siguiente;
    }

    public ElementoLDE<Tipo> getAnterior() {
        return anterior;
    }

    public void setSiguiente(ElementoLDE<Tipo> el) {
        this.siguiente = el;
    }

    public void setAnterior(ElementoLDE<Tipo> el) {
        this.anterior = el;
    }


    public Tipo getData() {
        return this.data;
    }

    public void setData(Tipo dato) {
        this.data = dato;
    }
}
