package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Agua extends Recursos {

    private int aumentoVida;
    private static double probAgua;

    public Agua(int posicionN, int posicionM, int turnosRestantes, double probRecurso, int aumentoVida, double probAgua) {
        super(posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoVida = aumentoVida;
        Agua.probAgua = probAgua;
    }
    public Agua() {
    }

    public Agua(double probAgua) {
        Agua.probAgua = probAgua;
    }
    public int getAumentoVida() {
        return aumentoVida;
    }

    public void setAumentoVida(int aumentoVida) {
        this.aumentoVida = aumentoVida;
    }

    public static double getProbAgua() {
        return probAgua;
    }

    public void setProbAgua(double probAgua) {
        Agua.probAgua = probAgua;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() + aumentoVida);
    }
}

