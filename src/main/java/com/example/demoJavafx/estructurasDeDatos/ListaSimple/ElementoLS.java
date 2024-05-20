package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

public class ElementoLS<TipoDeDatos> {
    private TipoDeDatos data;

    public ElementoLS(TipoDeDatos dato) {
        this.data = dato;
    }
    public ElementoLS(){}

    public TipoDeDatos getData() {
        return data;
    }

    public void setData(TipoDeDatos a) {
        this.data = a;
    }
}

