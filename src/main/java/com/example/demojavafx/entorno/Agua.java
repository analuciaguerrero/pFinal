package com.example.demojavafx.entorno;

import com.example.demojavafx.estudiante.Estudiante;

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

