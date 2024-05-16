package com.example.demoJavafx.estructurasDeDatos.ListaEnlazada;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaEnlazada<TipoDelDato> {
    private static final Logger log = LogManager.getLogger();
    private ElementoLE<TipoDelDato> primero;
    private ElementoLE<TipoDelDato>[] datos;

    public ElementoLE<TipoDelDato> getEl() {
        return primero;
    }

    public ListaEnlazada(ElementoLE<TipoDelDato> primero) {
        this.primero = primero;
    }
    public ListaEnlazada() {
        this.primero = null;
    }


    public boolean isVacia() {
        return this.primero == null;
    }

    public void vaciar() {
        this.primero = null;
    }
    private void add(ElementoLE<TipoDelDato> el) {
        if (isVacia()) {
            this.primero = el;
        } else {
            this.primero = new ElementoLE<>(el.getData(), this.primero);
        }

    }

    public void add(String s) {
        TipoDelDato o = (TipoDelDato) s;
        ElementoLE<TipoDelDato> a = new ElementoLE<>(o);
        add(a);
    }

    public void add(Object o) {
        TipoDelDato a = (TipoDelDato) o;
        ElementoLE<TipoDelDato> e = new ElementoLE<>(a);
        add(e);
    }

    private void insert(ElementoLE<TipoDelDato> el, int posicion) {
        if (primero == null) {
            add(el.getData());
        } else {
            int contador;
            ElementoLE<TipoDelDato> temporal = primero;
            for (contador = 0; contador < posicion - 1; contador++) {
                temporal = temporal.getSiguiente();
            }
            el.setSiguiente(temporal.getSiguiente());
            temporal.setSiguiente(el);
        }
    }

    public void insert(String s, int posicion) {
        TipoDelDato aux = (TipoDelDato) s ;
        ElementoLE<TipoDelDato> a = new ElementoLE<>(aux);
        insert(a, posicion);
    }

    public void insert(Object o, int posicion) {
        TipoDelDato aux = (TipoDelDato) o;
        ElementoLE<TipoDelDato> a = new ElementoLE<>(aux);
        insert(a, posicion);
    }

    public void delete(int posicion) {

        if (posicion == 0) {
            primero = primero.getSiguiente();
        } else {
            int contador = 0;
            ElementoLE<TipoDelDato> temporal = primero;
            while (contador < posicion - 1) {
                temporal = temporal.getSiguiente();
                contador++;
            }
            temporal.setSiguiente(temporal.getSiguiente().getSiguiente());
        }

    }
    public void del (TipoDelDato el) {
        Integer indiceAEliminar = null;
        for (int i=0; i != getNumeroElementos(); i++) {
            if (getElemento(i).getData() == el) {
                indiceAEliminar = i;
            }
        }
        if (indiceAEliminar == null) {
            log.warn("Se ha tratado de eliminar un elemento que no pertenece a la lista");
        } else {
            delete(indiceAEliminar);
        }
    }

    public int getNumeroElementos() {
        int contador = 0;
        if (!isVacia()) {
            ElementoLE<TipoDelDato> cabeza = this.primero;
            while (cabeza.getSiguiente() != null) {
                cabeza = cabeza.getSiguiente();
                contador++;
            }
            contador++;
        }
        return contador;
    }

    public int getPosicion(ElementoLE<TipoDelDato> el) {
        int posicion = 0;
        if (!isVacia()) {
            ElementoLE<TipoDelDato> cabeza = this.primero;
            while (!cabeza.getData().equals(el.getData()) && posicion < getNumeroElementos()) {
                cabeza = cabeza.getSiguiente();
                posicion++;
            }
            return posicion;
        }
        return -1;
    }

    public ElementoLE<TipoDelDato> getPrimero() {
        return this.primero;
    }

    public ElementoLE<TipoDelDato> getUltimo() {
        ElementoLE<TipoDelDato> cabeza = this.primero;
        if (!isVacia()) {
            int contador = 0;
            while (contador < getNumeroElementos() - 1) {
                cabeza = cabeza.getSiguiente();
                contador++;
            }
        }
        return cabeza;
    }

    public ElementoLE<TipoDelDato> getSiguiente(ElementoLE<TipoDelDato> el) {
        if (el.getData() != null) {
            int posicion = getPosicion(el);
            return getElemento(posicion + 1);
        } else {
            return null;
        }
    }

    public ElementoLE<TipoDelDato> getElemento(int pos) {
        ElementoLE<TipoDelDato> cabeza;
        int contador = 0;
        if (this.primero != null) {
            cabeza = this.primero;
            while (contador < pos) {
                cabeza = cabeza.getSiguiente();
                contador++;
                if (cabeza == null) {
                    return null;
                }
            }
            return cabeza;
        } else {
            return null;
        }

    }
    public void setElemento(int posicion, TipoDelDato elemento) {
        ElementoLE<TipoDelDato> e = new ElementoLE<>(elemento);
        datos[posicion] = e;
    }

    public ListaEnlazada<TipoDelDato> invertir(ElementoLE<TipoDelDato> aux,ListaEnlazada<TipoDelDato> lista  ) {
        lista.add(aux.getData());
        if(aux.getSiguiente()!=null){
            invertir(aux.getSiguiente(),lista);
        }
        return lista;
    }
    public ListaEnlazada<TipoDelDato> invertir(){
        ListaEnlazada<TipoDelDato> lista = new ListaEnlazada<>();
        return this.invertir(this.primero,lista);
    }
    public int suma(ElementoLE<Integer> el){
        int res = el.getData();
        if(el.getSiguiente() == null){
            return res;
        }else{
            return  el.getData() + suma(el.getSiguiente());
        }
    }
    public int suma(){
        ElementoLE<Integer> el = (ElementoLE<Integer>) this.primero;
        return suma(el);
    }
    public void insertarFinal(ElementoLE<TipoDelDato> nuevoElemento) {
        if (isVacia()) {
            primero = nuevoElemento;
            return;
        }
        ElementoLE<TipoDelDato> actual = primero;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevoElemento);
    }
}
