package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Pozo extends Recursos{
    public Pozo(int turnosRestantes) {
        super(turnosRestantes);
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(0); // Muerte instantánea
    }
}

