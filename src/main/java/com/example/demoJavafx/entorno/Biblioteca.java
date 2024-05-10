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


