package com.example.demojavafx.estructurasDeDatos.ArbolDeBusqueda;

public class Nodo<TipoDelDato>{
    Nodo<TipoDelDato> derecha;
    Nodo<TipoDelDato> izquierda;
    TipoDelDato dato;


    public Nodo(Nodo<TipoDelDato> derecha, Nodo<TipoDelDato> izquierda, TipoDelDato dato) {
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.dato = dato;
    }

    public Nodo(TipoDelDato dato) {
        this.dato = dato;
    }

    public Nodo<TipoDelDato> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<TipoDelDato> derecha) {
        this.derecha = derecha;
    }

    public Nodo<TipoDelDato> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<TipoDelDato> izquierda) {
        this.izquierda = izquierda;
    }

    public TipoDelDato getDato() {
        return dato;
    }

    public void setDato(TipoDelDato dato) {
        this.dato = dato;
    }

    public int gradoNodo(){
        Nodo<TipoDelDato> n = this;
        int contador =0;
        if(n.getIzquierda()!=null){
            contador++;
        }
        if (n.getDerecha()!=null){
            contador++;
        }
        return contador;
    }
    public boolean esHoja(){
        Nodo<TipoDelDato> n = this;
        return n.getDerecha()== null && n.getIzquierda()==null;
    }
}
