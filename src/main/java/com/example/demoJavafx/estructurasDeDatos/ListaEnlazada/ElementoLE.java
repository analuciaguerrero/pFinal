package com.example.demoJavafx.estructurasDeDatos.ListaEnlazada;

import com.google.gson.annotations.Expose;

public class ElementoLE<TipoDeDatos> {
    @Expose
    private TipoDeDatos data;
    @Expose
    private ElementoLE<TipoDeDatos> siguiente;

    // Siempre hay que poner el constructor
    public ElementoLE(TipoDeDatos dato){
        this.data=dato;
        this.siguiente=null;
    }
    public ElementoLE(TipoDeDatos dato, ElementoLE<TipoDeDatos> siguiente){
        this.data = dato;
        this.siguiente = siguiente;
    }
    public ElementoLE(){}
    public void setSiguiente(ElementoLE<TipoDeDatos> siguiente) {
        this.siguiente = siguiente;
    }
    public void insertarmeEn(ElementoLE<TipoDeDatos> el){ //Si no pone nada es void
        this.siguiente=el.siguiente;
        el.siguiente= this;
    }
    public ElementoLE<TipoDeDatos> getSiguiente(){
        return siguiente;
    }
    public TipoDeDatos getData(){
        return data;
    }
    public void setData(TipoDeDatos data){
        this.data=data;
    }
}
