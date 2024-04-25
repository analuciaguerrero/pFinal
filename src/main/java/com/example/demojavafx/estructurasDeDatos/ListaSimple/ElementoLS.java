package com.example.demojavafx.estructurasDeDatos.ListaSimple;

public class ElementoLS {
    private Object data;
    protected ElementoLS siguiente;
    public ElementoLS(Object data){
        this.data=data;
        this.siguiente = null;
    }
    public Object getData() {
        return data;
    }
    public Object setData(Object data) {
        this.data = data;
        return this.data;
    }
}
