package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Montaña extends Recursos{
    private final int reduccionVida;

    public Montaña(int turnosRestantes, int reduccionVida) {
        super(turnosRestantes);
        this.reduccionVida = reduccionVida;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() - reduccionVida);
    }
}

