package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

import java.util.concurrent.Callable;

public class Comida extends Recursos{
    private int aumentoVida;
    private static double probComida;

    public Comida(int posicionN, int posicionM, int turnosRestantes, double probRecurso, int aumentoVida, double probComida) {
        super(posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoVida = aumentoVida;
        Comida.probComida = probComida;
    }
    public Comida(){}
    public Comida(double probComida){
        Comida.probComida = probComida;
    }
    public int getAumentoVida() {
        return aumentoVida;
    }

    public void setAumentoVida(int aumentoVida) {
        this.aumentoVida = aumentoVida;
    }

    public static double getProbComida() {
        return probComida;
    }

    public void setProbComida(double probComida) {
        Comida.probComida = probComida;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() + aumentoVida);
    }
}

