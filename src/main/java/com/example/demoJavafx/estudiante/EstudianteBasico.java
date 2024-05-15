package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.tablero.Tablero;
import com.google.gson.annotations.Expose;

public class EstudianteBasico extends Estudiante<EstudianteBasico> {
    @Expose
    private final String nombreClase = "EstudianteBasico";
    public EstudianteBasico(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion);
    }
    public EstudianteBasico(Estudiante estudiante){
        super(estudiante);
    }

    @Override
    public Class<EstudianteBasico> getTipo () {
        return EstudianteBasico.class;
    }
    @Override
    public void mover(DatosJuego dato, Tablero tablero) {
        this.moverseAleatorio(tablero);
    }

}
