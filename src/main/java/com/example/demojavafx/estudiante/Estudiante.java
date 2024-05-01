package com.example.demojavafx.estudiante;

public abstract class Estudiante {
    private int posicionN;
    private int posicionM;
    private int id;
    private int generacion;
    private int tiempoDeVida;
    private int probReproduccion;
    private int probClonacion;
    private int probMuerte;

    public Estudiante(int id, int generacion, int tiempoDeVida, int probReproduccion, int probClonacion, int probMuerte, int posicionN, int posicionM) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = probMuerte;
        this.posicionM = posicionM;
        this.posicionN = posicionN;
    }

    public Estudiante(int id, int tiempoDeVida){
        this.id = id;
        this.tiempoDeVida = tiempoDeVida;
    }

    public int getPosicionN() {
        return posicionN;
    }

    public void setPosicionN(int posicionN) {
        this.posicionN = posicionN;
    }

    public int getPosicionM() {
        return posicionM;
    }

    public void setPosicionM(int posicionM) {
        this.posicionM = posicionM;
    }

    public int[] getPosicion () {
        int[] posicion = new int[2];
        posicion[0] = posicionN;
        posicion[1] = posicionM;
        return posicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public int getTiempoDeVida() {
        return tiempoDeVida;
    }

    public void setTiempoDeVida(int tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
    }

    public int getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(int probReproduccion) {
        this.probReproduccion = probReproduccion;
    }

    public int getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(int probClonacion) {
        this.probClonacion = probClonacion;
    }

    public int getProbMuerte() {
        return probMuerte;
    }

    public void setProbMuerte(int probMuerte){
       this.probMuerte = probMuerte;
    }

    public String getTipo(){
        return null;
    }

    public String setTipo(String tipo){
        return null;
    }

    @Override
    public String toString(){
        return "Estudiante[" +"id=" + id + ", generacion=" + generacion + ", tiempoDeVidaRestante=" + tiempoDeVida + ", probReproduccion=" + probReproduccion + ", probClonacion=" + probClonacion + ", probMuerte=" + probMuerte + "]";
    }

}