package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple2;
import com.example.demojavafx.estructurasDeDatos.OtrasEstructuras.Dupla;

public class Mapa<T, E> {
    ListaSimple2<Dupla<T, E>> datos;

    public Mapa() {
        datos = new ListaSimple2<>();
    }

    public void add(T clave, E dato) {
        int contador = 0;
        boolean existentIndex = false;
        while ((contador < datos.getNumeroElementos()) && (!existentIndex)) {
            if (datos.getElemento(contador).getData().getClave() == clave) {
                existentIndex = true;
            } else {
                contador++;
            }
        }
        if (!existentIndex) {
            datos.add(new Dupla<>(clave, dato));
        } else {
            datos.getElemento(contador).getData().setDato(dato);
        }
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

    public ListaSimple2<T> getIndices(){
        ListaSimple2<T> listaIndices=new ListaSimple2<>();
        Integer contador=0;
        while (contador<datos.getNumeroElementos()){
            listaIndices.add(datos.getElemento(contador).getData().getClave());
            contador++;
        }
        return listaIndices;
    }


}

