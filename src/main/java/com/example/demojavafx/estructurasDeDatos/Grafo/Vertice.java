package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple2;

public class Vertice<TipoDelDato> {
    private Integer ID;

    private TipoDelDato dato;
    private ListaSimple2<Arista> aristasVAntecesores;
    private ListaSimple2<Arista> aristasVHijos;

    public Vertice(Integer ID,TipoDelDato dato) {
        this.ID=ID;
        this.dato = dato;
        this.aristasVHijos = new ListaSimple2<>();
        this.aristasVAntecesores = new ListaSimple2<>();
    }

    public void addAristaVAntecesor(Arista arista){
        this.aristasVAntecesores.add(arista);
    }

    public void addAristaVHijo(Arista arista){
        this.aristasVHijos.add(arista);
    }

    public TipoDelDato getDato() {
        return dato;
    }

    public void setDato(TipoDelDato dato) {
        this.dato = dato;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public ListaSimple2<Arista> getAristasVAntecesores() {
        return aristasVAntecesores;
    }

    public void setAristasVAntecesores(ListaSimple2<Arista> aristasVAntecesores) {
        this.aristasVAntecesores = aristasVAntecesores;
    }

    public ListaSimple2<Arista> getAristasVHijos() {
        return aristasVHijos;
    }

    public void setAristasVHijos(ListaSimple2<Arista> aristasVHijos) {
        this.aristasVHijos = aristasVHijos;
    }
}

