package com.example.demojavafx.estudiante;

import com.example.demojavafx.zombieStudentsLife.Celda;

import java.util.Random;

public abstract class Estudiante {
    private int posicionN;
    private int posicionM;
    private boolean vivo;
    private int id;
    private int generacion;
    private int tiempoDeVida;
    private double probReproduccion;
    private double probClonacion;
    private double probMuerte;

    public Estudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte, int posicionN, int posicionM) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = probMuerte;
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        this.vivo = true;
    }

    public Estudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = probMuerte;
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

    public double getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(double probReproduccion) {
        this.probReproduccion = probReproduccion;
    }

    public double getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(double probClonacion) {
        this.probClonacion = probClonacion;
    }

    public double getProbMuerte() {
        return probMuerte;
    }

    public void setProbMuerte(double probMuerte){
       this.probMuerte = probMuerte;
    }

    public boolean isVivo(){
        return vivo;
    }

    public void setVivo(boolean vivo){
        this.vivo = vivo;
    }

    public void actualizar() {
        tiempoDeVida--;
        probReproduccion *= 0.9; // Reducir probabilidad de reproducción
        probClonacion *= 0.9; // Reducir probabilidad de clonación
        probMuerte = 1 - probReproduccion;
    }

    public abstract void mover(Celda[][] tablero);

    public boolean puedeReproducirse() {
        return tiempoDeVida > 0 && probReproduccion > 0;
    }

    public abstract Estudiante reproducirse(Estudiante pareja);

    public abstract Estudiante clonar();

    public boolean sobrevive() {
        Random rand = new Random();
        return rand.nextDouble() >= probMuerte;
    }
}









    //public String getTipo(){
        //return null;
    //}

    //public String setTipo(String tipo){
       // return null;
    //}

    //@Override
    //public String toString(){
        //return "Estudiante[" +"id=" + id + ", generacion=" + generacion + ", tiempoDeVidaRestante=" + tiempoDeVida + ", probReproduccion=" + probReproduccion + ", probClonacion=" + probClonacion + ", probMuerte=" + probMuerte + "]";
    //}

//}