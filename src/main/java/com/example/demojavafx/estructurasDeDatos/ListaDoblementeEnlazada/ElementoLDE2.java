package com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ElementoLDE2<T> {
    private T data;
    private ElementoLDE2<T> siguiente;
    private ElementoLDE2<T> anterior;

    protected void insertarmeEn(ElementoLDE2<T> el) {
        this.siguiente = el.siguiente;
        this.anterior = el;
        el.siguiente = this;
        if (this.siguiente != null)
            this.siguiente.anterior = this;
    }

    protected ElementoLDE2<T> getSiguiente() {
        return siguiente;
    }

    protected ElementoLDE2<T> getAnterior() {
        return anterior;
    }

    protected void setSiguiente(ElementoLDE2<T> el) {
        this.siguiente = el;
    }

    protected void setAnterior(ElementoLDE2<T> el) {
        this.anterior = el;
    }


    public T getData() {
        return this.data;
    }

    public void setData(T d) {
        this.data = d;
    }
}
