package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;

public class CeldaProperties {
    protected ListaEnlazada<Celda> original;

    protected ListaEnlazada<Celda> properties;

    public CeldaProperties(ListaEnlazada<Celda> original) {
        this.original = original;
    }

    public ListaEnlazada<Celda> getOriginal() {
        return original;
    }
    public void setOriginal(ListaEnlazada<Celda> original) {
        this.original = original;
    }
    public ListaEnlazada<Celda> getProperties() {
        return properties;
    }
    public void setProperties(ListaEnlazada<Celda> properties) {
        this.properties = properties;
    }
    public void commit(){
        original = properties;
    }
    public void rollback(){
        properties = original;
    }
}
