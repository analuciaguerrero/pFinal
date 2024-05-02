package com.example.demojavafx.entorno;

import com.example.demojavafx.estudiante.Estudiante;

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

