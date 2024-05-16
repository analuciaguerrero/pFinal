package com.example.demoJavafx.estructurasDeDatos.Grafo;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
public class NodoGrafo <TipoDelDato> {

    private TipoDelDato dato;
    private ListaDoblementeEnlazada<Arista<TipoDelDato>> listaEntrada = new ListaDoblementeEnlazada<>();
    private ListaDoblementeEnlazada<Arista<TipoDelDato>> listaSalida = new ListaDoblementeEnlazada<>();
    private String anotacion;
    private double peso;

    public NodoGrafo(TipoDelDato dato) {
        this.dato = dato;
    }

    public NodoGrafo(TipoDelDato dato, ListaDoblementeEnlazada<Arista<TipoDelDato>> listaEntrada, ListaDoblementeEnlazada<Arista<TipoDelDato>> listaSalida) {
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

    public ListaDoblementeEnlazada<Arista<TipoDelDato>> getListaEntrada() {
        return listaEntrada;
    }

    public void setListaEntrada(ListaDoblementeEnlazada<Arista<TipoDelDato>> listaEntrada) {
        this.listaEntrada = listaEntrada;
    }

    public ListaDoblementeEnlazada<Arista<TipoDelDato>> getListaSalida() {
        return listaSalida;
    }

    public void setListaSalida(ListaDoblementeEnlazada<Arista<TipoDelDato>> listaSalida) {
        this.listaSalida = listaSalida;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}

