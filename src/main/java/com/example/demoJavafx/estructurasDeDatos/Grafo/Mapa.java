package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;

public class Mapa<T, E> {
    private ListaDoblementeEnlazada<ElementoMap<T, E>> datos = new ListaDoblementeEnlazada<>();
    public Mapa(ListaDoblementeEnlazada<ElementoMap<T,E>> el){
        datos = el;
    }
    public Mapa() {
    }

    public boolean isVacio() {
        return this.datos.getPrimero()==null;
    }

    public E get(T clave){
        if (!datos.isVacia()) {
            for (int i = 0; i < datos.getNumeroElementos(); i++) {
                ElementoMap<T, E> elementoActual = datos.getElemento(i).getData();
                if (elementoActual.getClave() == clave) return elementoActual.getDato();
            }
        }
        return null;
    }
    public ListaDoblementeEnlazada<T> SetClave(){
        ListaDoblementeEnlazada<T> listaClaves = new ListaDoblementeEnlazada<>();
        ElementoLDE<ElementoMap<T,E>> elementoActual = datos.getPrimero();
        while (elementoActual != null) {
            listaClaves.add(elementoActual.getData().getClave());
            elementoActual = elementoActual.getSiguiente();
        }
        return listaClaves;
    }
    public void put (T clave, E elemento) {
        for (int i=0; i != datos.getNumeroElementos(); i++){
            if (datos.getElemento(i).getData().getClave() == clave) {
                datos.getElemento(i).getData().setDato(elemento);
                return;
            }
        }
        this.datos.add(new ElementoMap<>(clave, elemento));
    }
    public void eliminar(T clave) {
        for (int i = 0; i != datos.getNumeroElementos(); i++) {
            ElementoMap<T, E> ele = datos.getElemento(i).getData();
            if (ele.getClave() == clave){
                datos.del(i);
            }
        }
    }
}
