package com.example.demojavafx.entorno;

public class Montaña extends Recursos{
    private int disminucionVida;
    private float probMontaña;

    public Montaña(int tiempoVida, float probMontaña) {
        super(tiempoVida);
        this.probMontaña = probMontaña;
    }

    public Montaña(int turnosRestantes, int aumentoVida) {
        super(turnosRestantes);
        disminucionVida = aumentoVida;
    }

    public int getDisminucionVida() {
        return disminucionVida;
    }

    public void setDisminucionVida(int disminucionVida) {
        this.disminucionVida = disminucionVida;
    }

    public float getProbMontaña() {
        return probMontaña;
    }

    public void setProbMontaña(float probMontaña) {
        this.probMontaña = probMontaña;
    }
}

