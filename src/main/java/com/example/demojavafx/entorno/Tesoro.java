package com.example.demojavafx.entorno;

import com.example.demojavafx.estudiante.Estudiante;

public class Tesoro extends Recursos{
    private final double aumentoProbReproduccion;

    public Tesoro(int turnosRestantes, double aumentoProbReproduccion) {
        super(turnosRestantes);
        this.aumentoProbReproduccion = aumentoProbReproduccion;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setProbReproduccion(estudiante.getProbReproduccion() + aumentoProbReproduccion);
    }
}
