package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Dupla;

public class Mapa<T, E> {
    ListaDoblementeEnlazada<ElementoMap<T, E>> datos;
    public Mapa(ListaDoblementeEnlazada<ElementoMap<T,E>> el){
        datos = el;
    }
    public Mapa() {
        datos = new ListaDoblementeEnlazada<>();
    }

    public boolean isVacio() {
        return this.datos.getPrimero()==null;
    }

    public E get(T indice){
        Integer contador=0;
        while (contador<datos.getNumeroElementos()){
            if (datos.getElemento(contador).getData().getClave()==indice){
                return datos.getElemento(contador).getData().getDato();
            }
            contador++;
        }
        return null;
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
    public ListaDoblementeEnlazada<T> SetClave(){
        ListaDoblementeEnlazada<T> listaClaves = new ListaDoblementeEnlazada<>();
        ElementoLDE<ElementoMap<T,E>> elementoActual = datos.getPrimero();
        while (elementoActual != null) {
            listaClaves.add(elementoActual.getData().getClave());
            elementoActual = elementoActual.getSiguiente();
        }
        return listaClaves;
    }


    public ListaSimple<T> getIndices(){
        ListaSimple<T> listaIndices=new ListaSimple<>();
        Integer contador=0;
        while (contador<datos.getNumeroElementos()){
            listaIndices.add(datos.getElemento(contador).getData().getClave());
            contador++;
        }
        return listaIndices;
    }


}
