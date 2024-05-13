package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.zombieStudentsLife.Tablero;

import java.util.Random;
public class EstudianteAvanzado extends Estudiante<EstudianteAvanzado> {
    public EstudianteAvanzado(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
    }
    public EstudianteAvanzado(Estudiante estudiante){
        super(estudiante);
    }
    @Override
    public Class<EstudianteAvanzado> getTipo () {
        return EstudianteAvanzado.class;
    }
    @Override
    public void mover(DatosJuego dato, Tablero tablero) {
    }
}
