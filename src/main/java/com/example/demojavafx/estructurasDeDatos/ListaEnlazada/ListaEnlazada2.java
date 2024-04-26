package com.example.demojavafx.estructurasDeDatos.ListaEnlazada;

public class ListaEnlazada2<TipoDelDato> {
    private ElementoLE2<TipoDelDato> primero;

    public ListaEnlazada2() {
        this.primero = null;
    }

    public boolean isVacia() {
        return getNumeroElementos() == 0;
    }

    public int add(ElementoLE2<TipoDelDato> el) {
        if (!isVacia()) {
            ElementoLE2<TipoDelDato> inicial = primero;
            while (inicial.getSiguiente() != null) {
                inicial = inicial.getSiguiente();
            }
            inicial.setSiguiente(el);
        } else {
            primero = el;
        }
        return getNumeroElementos();
    }

    public int del(int pos) {
        ElementoLE2<TipoDelDato> inicial = primero;
        int posicionPedida = pos;
        if (pos >= 0 && pos < getNumeroElementos()) {
            if (pos == 0) {
                primero = primero.getSiguiente();
            } else {
                pos--;
                while (pos > 0) {
                    inicial = inicial.getSiguiente();
                    pos--;
                }
                if (posicionPedida == (getNumeroElementos() - 1)) {
                    inicial.setSiguiente(null);
                } else {
                    inicial.setSiguiente(inicial.getSiguiente().getSiguiente());
                }
            }
        }
        return getNumeroElementos();
    }

    public int getNumeroElementos() {
        if (primero == null) {
            return 0;
        } else {
            int contador = 1;
            ElementoLE2<TipoDelDato> inicial = primero;
            while (inicial.getSiguiente() != null) {
                contador++;
                inicial = inicial.getSiguiente();
            }
            return contador;
        }
    }

    public ElementoLE2<TipoDelDato> getPrimero() {
        return primero;
    }

    public ElementoLE2<TipoDelDato> getUltimo() {
        if (!isVacia()) {
            ElementoLE2<TipoDelDato> inicial = primero;
            while (inicial.getSiguiente() != null) {
                inicial = inicial.getSiguiente();
            }
            return inicial;
        } else { // Si es una lista vacía devuelve null
            return null;
        }
    }

    public ElementoLE2<TipoDelDato> getElemento(int pos) {
        if (pos < 0 || pos >= (getNumeroElementos())) {
            return null;
        } else {
            ElementoLE2<TipoDelDato> elemento = primero;
            while (pos > 0) {
                elemento = elemento.getSiguiente();
                pos--;
            }
            return elemento;
        }
    }

    public String toString() {
        String listaS = "[";
        if (this.primero != null) {
            ElementoLE2<TipoDelDato> actual = this.primero;
            listaS += actual.getData();
            if (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
                while (actual.getSiguiente() != null) {
                    listaS += ", " + actual.getData();
                    actual = actual.getSiguiente();
                }
                listaS += ", " + actual.getData();
            }
        }
        listaS += "]";
        return listaS;
    }
}

