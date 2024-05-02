package com.example.demojavafx.entorno;

import com.example.demojavafx.estudiante.Estudiante;
import com.example.demojavafx.estudiante.EstudianteAvanzado;
import com.example.demojavafx.estudiante.EstudianteBasico;
import com.example.demojavafx.estudiante.EstudianteNormal;

import java.util.Random;

public class Biblioteca extends Recursos{

    private final double aumentoProbClonacion;

    public Biblioteca(int turnosRestantes, double aumentoProbClonacion) {
        super(turnosRestantes);
        this.aumentoProbClonacion = aumentoProbClonacion;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setProbClonacion(estudiante.getProbClonacion() + aumentoProbClonacion);
        // Ajustar el tipo del individuo
        Random rand = new Random();
        int tipo = rand.nextInt(3) + 1; // Seleccionar aleatoriamente un tipo de individuo (entre 1 y 3)
        switch (tipo) {
            case 1:
                if (!(estudiante instanceof EstudianteBasico)) {
                    // Convertir a individuo básico
                    estudiante = new EstudianteBasico(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(), estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
                }
                break;
            case 2:
                if (!(estudiante instanceof EstudianteNormal)) {
                    // Convertir a individuo normal
                    estudiante = new EstudianteNormal(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(), estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
                }
                break;
            case 3:
                if (!(estudiante instanceof EstudianteAvanzado)) {
                    // Convertir a individuo avanzado
                    estudiante = new EstudianteAvanzado(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(), estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
                }
                break;
        }
    }
}

