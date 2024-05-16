package com.example.demoJavafx.estructurasDeDatos.Grafo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Arista<TipoDelDato> {
    private NodoGrafo<TipoDelDato> nodoIni;
    private NodoGrafo<TipoDelDato> nodoFin;
    private String anotacion;
    private boolean isDirigido;
    private double peso;
    private static final Logger log = LogManager.getLogger(Grafo.class);

    public Arista(){}
    public Arista(double peso, NodoGrafo<TipoDelDato> nodoIni, NodoGrafo<TipoDelDato> nodoFin, boolean isDirigido) {
        this.peso = peso;
        this.isDirigido = isDirigido;
        this.nodoIni = nodoIni;
        this.nodoFin = nodoFin;
    }
    public Arista(double peso, NodoGrafo<TipoDelDato> nodoIni, NodoGrafo<TipoDelDato> nodoFin, String anotacion, boolean isDirigido) {
        this.peso = peso;
        this.isDirigido = isDirigido;
        this.nodoIni = nodoIni;
        this.nodoFin = nodoFin;
        this.anotacion = anotacion;
    }

    public boolean isDirigido() {
        return isDirigido;
    }

    public void setDirigido(boolean dirigido) {
        isDirigido = dirigido;
    }

    public NodoGrafo<TipoDelDato> getNodoIni() {
        return nodoIni;
    }

    public void setNodoIni(NodoGrafo<TipoDelDato> nodoIni) {
        if (nodoIni!=null) {
            this.nodoIni = nodoIni;
        } else{
            log.warn("Se intentó asignar un nodo nulo como el punto de inicio del arco: " + this);
        }
    }

    public NodoGrafo<TipoDelDato> getNodoFin() {
        return nodoFin;
    }

    public void setNodoFin(NodoGrafo<TipoDelDato> nodoFin) {
        if (nodoFin!=null) {
            this.nodoFin = nodoFin;
        } else{
            log.warn("Se intentó asignar un nodo nulo como el punto de fin del arco: " + this);
        }
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    public NodoGrafo<TipoDelDato> getVertice (NodoGrafo<TipoDelDato> vertice) {
        if (vertice == nodoIni) {
            return nodoFin;
        } else if (vertice == nodoFin){
            return nodoIni;
        } else {
            return null;
        }
    }
}
