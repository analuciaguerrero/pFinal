package com.example.demojavafx.entorno;

public class Tesoro extends Recursos{
    private float AumentoPorcentajeRep;
    private float probTesoro;

    public Tesoro(int turnosRestantes, int aumentoVida) {
        super(turnosRestantes);
        AumentoPorcentajeRep = aumentoVida;
    }

    public Tesoro(int tiempoVida, float probTesoro) {
        super(tiempoVida);
        this.probTesoro = probTesoro;
    }

    public float getAumentoPorcentajeRep() {
        return AumentoPorcentajeRep;
    }

    public void setAumentoPorcentajeRep(float aumentoPorcentajeRep) {
        AumentoPorcentajeRep = aumentoPorcentajeRep;
    }

    public float getProbTesoro() {
        return probTesoro;
    }

    public void setProbTesoro(float probTesoro) {
        this.probTesoro = probTesoro;
    }
}
