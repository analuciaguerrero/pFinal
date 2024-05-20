package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.Nodo;
import com.example.demoJavafx.excepciones.DuplicateElement;
import com.example.demoJavafx.excepciones.NonexistentElement;

public class NodoAVL<TipoDeDatos> extends Nodo<TipoDeDatos> {
    private NodoAVL<TipoDeDatos> izquierda;
    private NodoAVL<TipoDeDatos> derecha;
    private int altura;
    public NodoAVL() {
        super();
        this.altura = 0;
    }

    public NodoAVL(TipoDeDatos dato) {
        super(dato);
        this.altura = 0;
    }

    @Override
    public NodoAVL<TipoDeDatos> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVL<TipoDeDatos> izquierda) {
        this.izquierda = izquierda;
    }

    @Override
    public NodoAVL<TipoDeDatos> getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVL<TipoDeDatos> derecha) {
        this.derecha = derecha;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
