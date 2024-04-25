package com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ElementoLDE {
    protected ElementoLDE siguiente;
    protected ElementoLDE anterior;
    private Object data;

    public ElementoLDE(String s) {
        this.setData(s);
    }

    public ElementoLDE(Object o) {
        this.setData(o);
    }

    private ElementoLDE getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ElementoLDE siguiente) {
        this.siguiente = siguiente;
    }

    private ElementoLDE getAnterior() {
        return anterior;
    }

    public void setAnterior(ElementoLDE anterior) {
        this.anterior = anterior;
    }

    public Object getDato() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void insertBefore(ElementoLDE el) {
        el.anterior = this.anterior;
        el.siguiente = this;
        if (this.anterior != null) {
            this.anterior.siguiente = el;
        }
        this.anterior = el;
    }

    public void insertAfter(ElementoLDE el) {
        el.anterior = this;
        el.siguiente = this.siguiente;
        if (this.siguiente != null) {
            this.siguiente.anterior = el;
        }
        this.siguiente = el;
    }

    public void del() {
        if (this.getSiguiente() != null) {
            this.siguiente.anterior = this.anterior;
        }
        if (this.getAnterior() != null) {
            this.anterior.siguiente = this.siguiente;
        }
    }
}
