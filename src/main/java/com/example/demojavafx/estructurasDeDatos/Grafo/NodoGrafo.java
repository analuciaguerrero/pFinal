package com.example.demojavafx.estructurasDeDatos.Grafo;
import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple2;
public class NodoGrafo <TipoDelDato>{
    public TipoDelDato dato;
    public ListaSimple2<NodoGrafo> listaEntrada;
    public ListaSimple2<NodoGrafo> listaSalida;

    public NodoGrafo(TipoDelDato dato) {
        this.dato = dato;
        this.listaEntrada = new ListaSimple2();
    }

    public NodoGrafo(TipoDelDato dato, ListaSimple2 listaEntrada, ListaSimple2 listaSalida) {
        this.dato = dato;
        this.listaEntrada = listaEntrada;
        this.listaSalida = listaSalida;
    }

    public TipoDelDato getDato() {
        return dato;
    }

    public void setDato(TipoDelDato dato) {
        this.dato = dato;
    }

    public ListaSimple2 getListaEntrada() {
        return listaEntrada;
    }

    public void setListaEntrada(ListaSimple2 listaEntrada) {
        this.listaEntrada = listaEntrada;
    }

    public ListaSimple2 getListaSalida() {
        return listaSalida;
    }

    public void setListaSalida(ListaSimple2 listaSalida) {
        this.listaSalida = listaSalida;
    }
}

