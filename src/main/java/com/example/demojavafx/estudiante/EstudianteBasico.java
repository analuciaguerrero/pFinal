package com.example.demojavafx.estudiante;

public class EstudianteBasico extends Estudiante {
    public EstudianteBasico(int I, int G, int T, float PR, float PC) {
        super(I, G, T, PR, PC);
    }

    @Override
    public String getTipo () {
        return "EstudianteBasico";
    }

    @Override
    public void mover() {
        this.moverAleatorio();
    }
}
