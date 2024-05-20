package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

public class ListaSimple<TipoDedatos> {

    private ElementoLS<TipoDedatos>[] datos;

    private int maximo;

    public ListaSimple() {
    }

    public ListaSimple(int maximo) {
        this.datos = new ElementoLS[maximo];
        this.maximo = maximo;
    }
    public boolean isVacia() {
        return this.datos == null;
    }
    public void vaciar() {
        this.datos = new ElementoLS[maximo];
    }
    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }
    protected ElementoLS<TipoDedatos>[] getDatos() {
        return datos;
    }

    protected void setDatos(ElementoLS<TipoDedatos>[] datos) {
        this.datos = datos;
    }
    public ElementoLS<TipoDedatos> getPrimero() {
        return this.datos[0];
    }

    public ElementoLS<TipoDedatos> getUltimo() {
        int pos = getNumeroElementos();
        return this.datos[pos - 1];
    }
    public ElementoLS<TipoDedatos> getElemento(int posicion) {
        return this.datos[posicion];
    }

    public void setElemento(int posicion, TipoDedatos elemento) {
        ElementoLS<TipoDedatos> a = new ElementoLS<>(elemento);
        datos[posicion] = a;
    }
    public int getNumeroElementos() {
        int elementos = 0;
        for (int i = 0; this.datos[i] != null; i++) {
            elementos += 1;
            if (i + 1 == maximo) return elementos;
        }
        return elementos;
    }
    protected ElementoLS<TipoDedatos> getSiguiente(ElementoLS<TipoDedatos> el) {
        int pos = getPosicion(el);
        return this.datos[pos + 1];
    }
    public int getPosicion(ElementoLS<TipoDedatos> el) {
        int pos = 0;
        for (int i = 0; this.datos[i].getData() != el.getData(); i++) {
            pos += 1;
        }
        return pos;
    }
    public int add(ElementoLS<TipoDedatos> el) {
        int pos = 0;
        for (int i = 0; this.datos[i] != null; i++) {
            pos += 1;
        }
        this.datos[pos] = el;
        return pos;
    }

    public void add(TipoDedatos o) {
        ElementoLS<TipoDedatos> e = new ElementoLS<>();
        e.setData(o);
        add(e);
    }

    public void insert(TipoDedatos o, int posicion) {
        ElementoLS<TipoDedatos> e = new ElementoLS<>();
        e.setData(o);
        ElementoLS<TipoDedatos> actual = this.datos[posicion];
        for (int i = posicion; this.datos[i] != null; i++) {
            ElementoLS<TipoDedatos> siguiente = this.datos[i + 1];
            this.datos[i + 1] = actual;
            actual = siguiente;
        }
        this.datos[posicion] = e;
    }

    public int del(int posicion) {
        this.datos[posicion] = null;
        int ultimo = 0;
        for (int i = posicion + 1; this.datos[i] != null; i++) {
            this.datos[i - 1] = this.datos[i];
            ultimo += 1;
        }
        return ultimo + posicion - 2;
    }
}