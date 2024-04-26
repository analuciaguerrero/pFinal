package com.example.demojavafx.estudiante;
import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.excepciones.ProbabilidadInvalida;

import java.util.Random;
public class Estudiante {
    private int posicionX;
    private int posicionY;
    private int id;
    private int generacion;
    private int tiempoDeVida;
    private float probReproduccion;
    private float probClonacion;
    private float probMuerte;

    public Estudiante(int I, int G, int T, float PR, float PC) {
        this.id = I;
        this.generacion = G;
        this.tiempoDeVida = T;
        if (PR < 0 || PR > 100 || PC < 0 || PC > 100) throw new ProbabilidadInvalida();
        this.probReproduccion = PR;
        this.probClonacion = PC;
        this.probMuerte = 1-PR;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int[] getPosicion () {
        int[] posicion = new int[2];
        posicion[0] = posicionX;
        posicion[1] = posicionY;
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

    public float getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(float probReproduccion) {
        if (probReproduccion < 0 || probReproduccion > 100) throw new ProbabilidadInvalida();
        this.probReproduccion = probReproduccion;
        this.probMuerte = 1 - probReproduccion;
    }

    public float getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(float probClonacion) {
        if (probClonacion < 0 || probClonacion > 100) throw new ProbabilidadInvalida();
        this.probClonacion = probClonacion;
    }

    public float getProbMuerte() {
        return probMuerte;
    }

    public String getTipo () {return null;}

    public String setTipo (String tipo) {return null;}

    public void morir () {

    }
    public void actualizarTV () {
        tiempoDeVida -= 1;
        if (tiempoDeVida == 0) morir();
    }

    public void mover() {}

    public void moverAleatorio() {
        Random r = new Random();
        int movimiento = r.nextInt(4);
        if (movimiento == 1) {
            this.setPosicionX(this.getPosicionX() - 1);
        } else if (movimiento == 2) {
            this.setPosicionX(this.getPosicionX() + 1);
        } else if (movimiento == 3) {
            this.setPosicionY(this.getPosicionY() - 1);
        } else {
            this.setPosicionY(this.getPosicionY() + 1);
        }
    }

    public void mejorar (Recursos recurso) {}


}