package com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada;

public class ListaDoblementeEnlazada2<T>{
    private ElementoLDE2<T> primero;
    private ElementoLDE2<T> ultimo;

    public ListaDoblementeEnlazada2() {
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

    protected int add(ElementoLDE2<T> el) {
        int pos = 1;
        if (isVacia()) {
            this.primero = el;
            this.ultimo = el;
            return 0;
        } else {
            ElementoLDE2<T> actual = this.primero;
            while (actual != this.ultimo) {
                actual = actual.getSiguiente();
                pos += 1;
            }
            el.insertarmeEn(this.ultimo);
            this.ultimo = el;
            return pos;
        }
    }

    public void add(T o) {
        ElementoLDE2<T> e = new ElementoLDE2<T>();
        e.setData(o);
        add(e);
    }

    public void insert(T o, int posicion) {
        ElementoLDE2<T> objeto = new ElementoLDE2<T>();
        objeto.setData(o);
        if (posicion == 0){
            objeto.setSiguiente(this.primero);
            if (this.primero != null) {
                this.primero.setAnterior(objeto);
            } else {
                this.ultimo = objeto;
            }
            this.primero = objeto;
        } else {
            if (getElemento(posicion - 1) == null){
                System.out.println("No existen tantos elementos en la lista");
            } else {
                objeto.insertarmeEn(getElemento(posicion - 1));
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
            ElementoLDE2<T> actual = this.primero;
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

    public int getNumeroElementos() {
        if (isVacia()) {
            return 0;
        } else {
            int elms = 1;
            ElementoLDE2<T> actual = this.primero;
            while (actual != this.ultimo) {
                elms += 1;
                actual = actual.getSiguiente();
            }
            return elms;
        }
    }

    public int getPosicion (ElementoLDE2<T> e){
        int pos = 0;
        ElementoLDE2<T> actual = this.primero;
        while (actual != e) {
            if (actual == null){
                System.out.println("El elemento no pertenece a la lista");
                return -1;
            }
            actual = actual.getSiguiente();
            pos += 1;
        }
        return pos;
    }

    public ElementoLDE2<T> getPrimero() {
        if (isVacia()){
            return null;
        } else {
            return this.primero;
        }
    }

    public ElementoLDE2<T> getUltimo () {
        if (isVacia()){
            return null;
        } else {
            return this.ultimo;
        }
    }

    public ElementoLDE2<T> getSiguiente (ElementoLDE2<T> el){
        return el.getSiguiente();
    }

    public ElementoLDE2<T> getElemento ( int posicion){
        if (isVacia()){
            System.out.println("La lista esta vacia, no contiene elementos");
            return null;
        } else {
            ElementoLDE2<T> actual = this.primero;
            for (int i = 0; i != posicion; i++) {
                actual = actual.getSiguiente();
            }
            return actual;
        }
    }
    public String toString(){
        String salida = "";
        return "["+ toStringAux(this.primero,salida)+"]";
    }
    public String toStringAux(ElementoLDE2<T> n, String salida){
        if((n != null)&&(n != this.ultimo)){
            salida= salida + n.getData() + ", "+toStringAux(n.getSiguiente(),salida);
        } else if (n== this.ultimo){
            salida = salida + n.getData();
        }
        return salida;
    }
}
