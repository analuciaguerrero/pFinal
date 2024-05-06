package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;

public class Vertice<TipoDelDato> {
    private Integer ID;

    private TipoDelDato dato;
    private ListaSimple<Arista> aristasVAntecesores;
    private ListaSimple<Arista> aristasVHijos;

    public Vertice(Integer ID,TipoDelDato dato) {
        this.ID=ID;
        this.dato = dato;
        this.aristasVHijos = new ListaSimple<>();
        this.aristasVAntecesores = new ListaSimple<>();
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
    public ListaSimple<Arista> getAristasVAntecesores() {
        return aristasVAntecesores;
    }

    public void setAristasVAntecesores(ListaSimple<Arista> aristas) {
        this.aristasVAntecesores = aristas;
    }

    public ListaSimple<Arista> getAristasVHijos() {
        return aristasVHijos;
    }

    public void setAristasVHijos(ListaSimple<Arista> aristas) {
        this.aristasVHijos = aristas;
    }
}
