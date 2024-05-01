package com.example.demojavafx.estudiante;

public class EstudianteBasico extends Estudiante {
    public EstudianteBasico(int id, int tiempoDeVida) {
        super(id,tiempoDeVida);
    }
    @Override
    public String getTipo(){
        return "EstudianteBasico";
    }
}
