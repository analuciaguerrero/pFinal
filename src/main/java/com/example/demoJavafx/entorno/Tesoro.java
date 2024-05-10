package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Tesoro extends Recursos{
    private double aumentoProbReproduccion;
    private static double probTesoro;

    public Tesoro(int posicionN, int posicionM, int turnosRestantes, double probRecurso, double aumentoProbReproduccion, double probTesoro) {
        super(posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoProbReproduccion = aumentoProbReproduccion;
        Tesoro.probTesoro = probTesoro;
    }
    public Tesoro(){}
    public Tesoro(double probTesoro){
        Tesoro.probTesoro = probTesoro;
    }
    public double getAumentoProbReproduccion(){
        return aumentoProbReproduccion;
    }
    public void setAumentoProbReproduccion(double aumentoProbReproduccion){
        this.aumentoProbReproduccion = aumentoProbReproduccion;
    }
    public static double getProbTesoro(){
        return  probTesoro;
    }
    public void setProbTesoro(double probTesoro){
        Tesoro.probTesoro = probTesoro;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setProbReproduccion(estudiante.getProbReproduccion() + aumentoProbReproduccion);
    }
}
