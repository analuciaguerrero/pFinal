package com.example.demojavafx.entorno;

public class Biblioteca extends Recursos{

    private float aumentoPorcentajeClon;
    private float probBiblio;

    public Biblioteca(int tiempoVida, float probBiblio) {
        super(tiempoVida);
        this.probBiblio = probBiblio;
    }

    public Biblioteca(int turnosRestantes, int aumentoVida) {
        super(turnosRestantes);
        aumentoPorcentajeClon = aumentoVida;
    }

    public float getAumentoPorcentajeClon() {
        return aumentoPorcentajeClon;
    }

    public void setAumentoPorcentajeClon(float aumentoPorcentajeClon) {
        this.aumentoPorcentajeClon = aumentoPorcentajeClon;
    }

    public float getProbBiblio() {
        return probBiblio;
    }

    public void setProbBiblio(float probBiblio) {
        this.probBiblio = probBiblio;
    }
}

