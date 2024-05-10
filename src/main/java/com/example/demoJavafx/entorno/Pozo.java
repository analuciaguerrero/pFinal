package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Pozo extends Recursos{
    private static double probPozo;
    public Pozo(int posicionN, int posicionM, int turnosRestantes, double probRecurso, double probPozo) {
        super(posicionN, posicionM, turnosRestantes, probRecurso);
        Pozo.probPozo = probPozo;
    }
    public Pozo(){
    }
    public Pozo(double probPozo){
        Pozo.probPozo = probPozo;
    }
    public static double getProbPozo(){
        return probPozo;
    }
    public void setProbPozo(double probPozo){
        Pozo.probPozo = probPozo;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(0); // Muerte instantánea
    }
}

