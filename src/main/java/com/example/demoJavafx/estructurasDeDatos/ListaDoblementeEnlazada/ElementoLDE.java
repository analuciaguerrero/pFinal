package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ElementoLDE<Tipo> {
    private Tipo data;
    private ElementoLDE<Tipo> siguiente;
    private ElementoLDE<Tipo> anterior;

    protected void insertarmeEn(ElementoLDE<Tipo> el) {
        this.siguiente = el.siguiente;
        this.anterior = el;
        el.siguiente = this;
        if (this.siguiente != null)
            this.siguiente.anterior = this;
    }

    protected ElementoLDE<Tipo> getSiguiente() {
        return siguiente;
    }

    protected ElementoLDE<Tipo> getAnterior() {
        return anterior;
    }

    protected void setSiguiente(ElementoLDE<Tipo> el) {
        this.siguiente = el;
    }

    protected void setAnterior(ElementoLDE<Tipo> el) {
        this.anterior = el;
    }


    public Tipo getData() {
        return this.data;
    }

    public void setData(Tipo dato) {
        this.data = dato;
    }
}
