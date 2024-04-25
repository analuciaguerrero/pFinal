package com.example.demojavafx.entorno;

public class Montaña extends Recursos{
    private int disminucionDeVida;
    private float probMontaña;

    public Montaña(int tiempoVida, float probMontaña) {
        super(tiempoVida);
        this.probMontaña = probMontaña;
    }

    public Montaña(int turnosRestantes, int aumentoDeVida) {
        super(turnosRestantes);
        disminucionDeVida = aumentoDeVida;
    }

    public int getDisminucionDeVida() {
        return disminucionDeVida;
    }

    public void setDisminucionDeVida(int disminucionDeVida) {
        this.disminucionDeVida = disminucionDeVida;
    }

    public float getProbMontaña() {
        return probMontaña;
    }

    public void setProbMontaña(float probMontaña) {
        this.probMontaña = probMontaña;
    }
}

