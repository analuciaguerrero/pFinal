package com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ListaDoblementeEnlazada {
    private ElementoLDE primero;
    private ElementoLDE ultimo;

    public ListaDoblementeEnlazada(ElementoLDE primero, ElementoLDE ultimo) {
        this.primero = primero;
        this.ultimo = ultimo;
        this.primero.setSiguiente(ultimo);
        this.ultimo.setAnterior(primero);
    }

    public ListaDoblementeEnlazada() {
    }


    public boolean isVacia() {
        return getNumeroElementos() == 0;
    }

    public void vaciar() {
        primero = null;
        ultimo = null;
    }

    private int add(ElementoLDE el) {
        if (getNumeroElementos() == 1) { // Si la lista tiene un solo elemento se declara el elemento a añadir como "ultimo"
            ultimo = el;
            primero.insertAfter(ultimo);
        } else if (isVacia()) {
            primero = el;
            primero.setAnterior(null);
            primero.setSiguiente(null);
            ultimo = el;
        } else {
            ultimo.insertAfter(el);
            ultimo = el;
        }
        return getNumeroElementos();
    }

    public int add(String s) {
        ElementoLDE sLDE = new ElementoLDE(s);
        add(sLDE); // Llamamos a la función add(ElementoLDE)
        return getNumeroElementos();
    }

    public int add(Object o) {
        ElementoLDE oLDE = new ElementoLDE(o);
        add(oLDE); // Llamamos a la función add(ElementoLDE)
        return getNumeroElementos();

    }

    private void insert(ElementoLDE el, int pos) { // Si se mete un número de posición no válido no se hace nada
        if (0 <= pos && pos <= getNumeroElementos()) { // Comprobar que el numero de posición introducido es válido
            if (pos == 0) {
                if (this.isVacia()) {
                    add(el);
                } else {
                    primero.anterior = el;
                    el.siguiente = primero;
                    primero = el;
                }
            } else if (pos == getNumeroElementos()) {
                add(el);
            } else {
                ElementoLDE elementoDeDespués = primero;
                while (pos > 0) {
                    elementoDeDespués = elementoDeDespués.siguiente;
                    pos--;
                }
                elementoDeDespués.insertBefore(el);
            }
        }
    }

    public void insert(String s, int posicion) {
        ElementoLDE sLDE = new ElementoLDE(s);
        insert(sLDE, posicion);
    }

    public void insert(Object o, int posicion) {
        ElementoLDE oLDE = new ElementoLDE(o);
        insert(oLDE, posicion);
    }

    public int del(int pos) {
        if (0 <= pos && pos < getNumeroElementos()) {
            if (pos == 0) {
                primero = primero.siguiente;
                primero.anterior = null;
            } else if (pos == (getNumeroElementos() - 1)) {
                ElementoLDE borrado = ultimo;
                ultimo = borrado.anterior;
                ultimo.anterior = borrado.anterior.anterior;
                ultimo.siguiente = null;
            } else {
                int contador = pos;
                ElementoLDE inicial = primero;
                while (contador > 0) {
                    inicial = inicial.siguiente;
                    contador--;
                }
                inicial.anterior.siguiente = inicial.siguiente;
                inicial.siguiente.anterior = inicial.anterior;
            }
        }
        return getNumeroElementos();
    }

    public int getNumeroElementos() {
        int contador = 0;
        ElementoLDE actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public ElementoLDE getElemento(int pos) {  // Si la posición no es válida devuelve null
        if (pos >= 0 && pos < getNumeroElementos()) {
            ElementoLDE ElementoADevolver = primero;
            while (pos > 0) {
                ElementoADevolver = ElementoADevolver.siguiente;
                pos--;
            }
            return ElementoADevolver;
        } else {
            return null;
        }
    }

    public ElementoLDE getPrimero() {
        return primero;
    }

    public ElementoLDE getUltimo() {
        return ultimo;
    }
}
