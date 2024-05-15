package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

public class ElementoLS<TipoDelDato> {
    TipoDelDato data;

    public ElementoLS(TipoDelDato dato) {
        this.setData(dato);
    }
    public ElementoLS(){}

    public TipoDelDato getData() {
        return data;
    }

    public void setData(TipoDelDato a) {
        this.data = a;
    }
}

