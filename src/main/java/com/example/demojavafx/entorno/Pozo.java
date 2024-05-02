package com.example.demojavafx.entorno;

import com.example.demojavafx.estudiante.Estudiante;

public class Pozo extends Recursos{
    public Pozo(int turnosRestantes) {
        super(turnosRestantes);
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(0); // Muerte instantánea
    }
}

