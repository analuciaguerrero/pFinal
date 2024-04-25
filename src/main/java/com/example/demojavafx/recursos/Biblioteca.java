package com.example.demojavafx.recursos;

public class Biblioteca extends Recursos{

    private float aumentoDePorcenClon;
    private float probBiblioteca;

    public Biblioteca(int tiempoVida, float probBiblioteca) {
        super(tiempoVida);
        this.probBiblioteca = probBiblioteca;
    }

    public Biblioteca(int turnosRestantes, int aumentoDeVida) {
        super(turnosRestantes);
        aumentoDePorcenClon = aumentoDeVida;
    }

    public float getAumentoDePorcenClon() {
        return aumentoDePorcenClon;
    }

    public void setAumentoDePorcenClon(float aumentoDePorcenClon) {
        this.aumentoDePorcenClon = aumentoDePorcenClon;
    }

    public float getProbBiblioteca() {
        return probBiblioteca;
    }

    public void setProbBiblioteca(float probBiblioteca) {
        this.probBiblioteca = probBiblioteca;
    }
}

