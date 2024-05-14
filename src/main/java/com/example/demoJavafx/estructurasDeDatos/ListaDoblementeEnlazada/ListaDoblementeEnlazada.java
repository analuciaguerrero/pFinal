package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaDoblementeEnlazada<Tipo>{
    private static final Logger log = LogManager.getLogger();
    private ElementoLDE<Tipo> primero;
    private ElementoLDE<Tipo> ultimo;

    public ListaDoblementeEnlazada() {
        this.primero = null;
        this.ultimo = null;
    }

    public Boolean isVacia() {
        return this.primero == null;
    }

    public void vaciar() {
        this.primero = null;
        this.ultimo = null;
    }

    protected int add(ElementoLDE<Tipo> el) {
        int pos = 1;
        if (isVacia()) {
            this.primero = el;
            this.ultimo = el;
            return 0;
        } else {
            ElementoLDE<Tipo> actual = this.primero;
            while (actual != this.ultimo) {
                actual = actual.getSiguiente();
                pos += 1;
            }
            el.insertarmeEn(this.ultimo);
            this.ultimo = el;
            return pos;
        }
    }

    public void add(Tipo o) {
        ElementoLDE<Tipo> e = new ElementoLDE<>();
        e.setData(o);
        add(e);
    }

    public void insert(Tipo o, int posicion) {
        ElementoLDE<Tipo> objeto = new ElementoLDE<>();
        objeto.setData(o);
        if (posicion == 0) {
            objeto.setSiguiente(this.primero);
            if (this.primero != null) {
                this.primero.setAnterior(objeto);
            } else {
                this.ultimo = objeto;
            }
            this.primero = objeto;
        } else {
            ElementoLDE<Tipo> anterior = getElemento(posicion - 1);
            if (anterior == null) {
                log.error("No existen tantos elementos en la lista para insertar en la posición {}", posicion);
            } else {
                objeto.insertarmeEn(anterior);
            }
        }
    }
    public int del(int posicion) {
        if (posicion == 0) {
            this.primero = this.primero.getSiguiente();
            if (!isVacia()){
                this.primero.setAnterior(null);
            }
        } else {
            ElementoLDE<Tipo> actual = this.primero;
            for (int i = 0; i != posicion ; i++) {
                actual = actual.getSiguiente();
                if (actual == this.ultimo) {
                    this.ultimo = actual.getAnterior();
                    return this.getNumeroElementos();
                }
            }
            actual.getSiguiente().setAnterior(actual.getAnterior());
            actual.getAnterior().setSiguiente(actual.getSiguiente());
        }
        return this.getNumeroElementos();
    }
    public void del (Tipo elemento) {
        Integer indiceAEliminar = null;
        for (int i=0; i != getNumeroElementos(); i++) {
            if (getElemento(i).getData() == elemento) {
                indiceAEliminar = i;
            }
        }
        if (indiceAEliminar == null) {
            log.warn("El elemento que se quería eliminar no pertenecía a la lista");
        } else {
            del(indiceAEliminar);
        }
    }

    public int getNumeroElementos() {
        if (isVacia()) {
            return 0;
        } else {
            int elms = 1;
            ElementoLDE<Tipo> actual = this.primero;
            while (actual != this.ultimo) {
                elms += 1;
                actual = actual.getSiguiente();
            }
            return elms;
        }
    }

    public int getPosicion(ElementoLDE<Tipo> e) {
        int pos = 0;
        ElementoLDE<Tipo> actual = this.primero;
        while (actual != e) {
            if (actual == null) {
                log.error("El elemento no pertenece a la lista");
                return -1;
            }
            actual = actual.getSiguiente();
            pos += 1;
        }
        return pos;
    }

    public ElementoLDE<Tipo> getPrimero() {
        if (isVacia()){
            return null;
        } else {
            return this.primero;
        }
    }

    public ElementoLDE<Tipo> getUltimo () {
        if (isVacia()){
            return null;
        } else {
            return this.ultimo;
        }
    }

    public ElementoLDE<Tipo> getSiguiente (ElementoLDE<Tipo> el){
        return el.getSiguiente();
    }

    public ElementoLDE<Tipo> getElemento(int posicion) {
        if (isVacia()) {
            log.error("La lista está vacía, no contiene elementos");
            return null;
        } else {
            ElementoLDE<Tipo> actual = this.primero;
            for (int i = 0; i != posicion; i++) {
                actual = actual.getSiguiente();
            }
            return actual;
        }
    }
    public void setElemento (int posicion, Tipo elemento) {
        ElementoLDE<Tipo> elementoActual = this.primero;
        for (int i = 0; i != posicion; i++) {
            elementoActual = elementoActual.getSiguiente();
        }
        elementoActual.setData(elemento);
    }
    public String toString(){
        String salida = "";
        return "["+ toStringAux(this.primero,salida)+"]";
    }
    public String toStringAux(ElementoLDE<Tipo> n, String salida){
        if((n != null)&&(n != this.ultimo)){
            salida= salida + n.getData() + ", "+toStringAux(n.getSiguiente(),salida);
        } else if (n== this.ultimo){
            salida = salida + n.getData();
        }
        return salida;
    }
}
