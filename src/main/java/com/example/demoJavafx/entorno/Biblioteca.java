package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;
//import com.example.demojavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.estudiante.EstudianteNormal;

import java.util.Random;

public class Biblioteca extends Recursos {

    private double aumentoProbClonacion;
    private static double probBiblioteca;
    public Biblioteca(int posicionN, int posicionM, int turnosRestantes, double probRecurso, double aumentoProbClonacion, double probBiblioteca) {
        super(posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoProbClonacion = aumentoProbClonacion;
        Biblioteca.probBiblioteca = probBiblioteca;
    }

    public Biblioteca() {
    }

    public Biblioteca(double probBiblioteca) {
        Biblioteca.probBiblioteca = probBiblioteca;
    }
    public double getAumentoProbClonacion(){
        return aumentoProbClonacion;
    }
    public void setAumentoProbClonacion(double aumentoProbClonacion){
        this.aumentoProbClonacion = aumentoProbClonacion;
    }
    public static double getProbBiblioteca() {
        return probBiblioteca;
    }

    public void setProbBiblioteca(double probBiblioteca) {
        Biblioteca.probBiblioteca = probBiblioteca;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        // Aumentar la probabilidad de clonación del estudiante
        estudiante.setProbClonacion(estudiante.getProbClonacion() + aumentoProbClonacion);

        // Ajustar el tipo del estudiante según su probabilidad de clonación
        if (estudiante instanceof EstudianteBasico) {
            if (estudiante.getProbClonacion() >= 0.5 && estudiante.getProbClonacion() < 0.7) {
                // Actualizar el tipo a EstudianteNormal
                estudiante = new EstudianteNormal(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(),
                        estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
            } else if (estudiante.getProbClonacion() >= 0.7 && estudiante.getProbClonacion() <= 1.0) {
                // Actualizar el tipo a EstudianteAvanzado
                estudiante = new EstudianteAvanzado(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(),
                        estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
            }
        } else if (estudiante instanceof EstudianteNormal) {
            if (estudiante.getProbClonacion() < 0.5) {
                // Actualizar el tipo a EstudianteBasico
                estudiante = new EstudianteBasico(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(),
                        estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
            } else if (estudiante.getProbClonacion() >= 0.7 && estudiante.getProbClonacion() <= 1.0) {
                // Actualizar el tipo a EstudianteAvanzado
                estudiante = new EstudianteAvanzado(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(),
                        estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
            }
        } else if (estudiante instanceof EstudianteAvanzado) {
            if (estudiante.getProbClonacion() < 0.5) {
                // Actualizar el tipo a EstudianteBasico
                estudiante = new EstudianteBasico(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(),
                        estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
            } else if (estudiante.getProbClonacion() >= 0.5 && estudiante.getProbClonacion() < 0.7) {
                // Actualizar el tipo a EstudianteNormal
                estudiante = new EstudianteNormal(estudiante.getId(), estudiante.getGeneracion(), estudiante.getTiempoDeVida(),
                        estudiante.getProbReproduccion(), estudiante.getProbClonacion(), estudiante.getProbMuerte());
            }
        }
    }
}


