package com.example.demojavafx.estudiante;

public class EstudianteNormal extends Estudiante {
    public EstudianteNormal(int id, int tiempoDeVida) {
        super(id, tiempoDeVida);
    }

    @Override
    public String getTipo() {
        return "EstudianteNormal";
    }
}
