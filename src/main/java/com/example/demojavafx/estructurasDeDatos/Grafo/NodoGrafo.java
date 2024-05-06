package com.example.demojavafx.estructurasDeDatos.Grafo;
import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple;
public class NodoGrafo <TipoDelDato>{
    public TipoDelDato dato;
    public ListaSimple<NodoGrafo> listaEntrada;
    public ListaSimple<NodoGrafo> listaSalida;

    public NodoGrafo(TipoDelDato dato) {
        this.dato = dato;
        this.listaEntrada = new ListaSimple();
    }

    public NodoGrafo(TipoDelDato dato, ListaSimple listaEntrada, ListaSimple listaSalida) {
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

    public ListaSimple getListaEntrada() {
        return listaEntrada;
    }

    public void setListaEntrada(ListaSimple listaEntrada) {
        this.listaEntrada = listaEntrada;
    }

    public ListaSimple getListaSalida() {
        return listaSalida;
    }

    public void setListaSalida(ListaSimple listaSalida) {
        this.listaSalida = listaSalida;
    }
}
