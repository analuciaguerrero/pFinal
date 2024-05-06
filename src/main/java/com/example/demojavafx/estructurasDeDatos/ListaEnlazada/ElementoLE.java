package com.example.demojavafx.estructurasDeDatos.ListaEnlazada;

public class ElementoLE<TipoDelDato> {
    private TipoDelDato data;
    private ElementoLE<TipoDelDato> siguiente;

    // Siempre hay que poner el constructor
    public ElementoLE(TipoDelDato dato){
        this.data=dato;
        this.siguiente=null;
    }
    public ElementoLE(TipoDelDato dato, ElementoLE<TipoDelDato> siguiente){
        this.data = dato;
        this.siguiente = siguiente;
    }
    public void setSiguiente(ElementoLE<TipoDelDato> siguiente) {
        this.siguiente = siguiente;
    }
    public void insertarmeEn(ElementoLE<TipoDelDato> el){ //Si no pone nada es void
        el.siguiente= this.siguiente;
        this.siguiente=el;
    }
    public ElementoLE<TipoDelDato> getSiguiente(){
        return siguiente;
    }
    public TipoDelDato getData(){
        return data;
    }
    public void setData(TipoDelDato data){
        this.data=data;
    }
}
