package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.tablero.Tablero;

public class EstudianteBasico extends Estudiante {
    public EstudianteBasico(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion);
    }
    public EstudianteBasico(int id, int tiempoDeVida) {
        super(id, tiempoDeVida);
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
