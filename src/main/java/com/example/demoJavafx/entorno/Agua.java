package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Agua extends Recursos {

    private final int aumentoVida;

    public Agua(int turnosRestantes, int aumentoVida) {
        super(turnosRestantes);
        this.aumentoVida = aumentoVida;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() + aumentoVida);
    }
}

