package com.example.demojavafx.estructurasDeDatos.ListaSimple;

public class ElementoLS2<TipoDelDato> {
    TipoDelDato data;

    public ElementoLS2(TipoDelDato dato) {
        this.setData(dato);
    }

    public TipoDelDato getData() {
        return data;
    }

    public void setData(TipoDelDato dato) {
        this.data = dato;
    }
}

