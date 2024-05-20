package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.tablero.Tablero;

public class EstudianteBasico extends Estudiante {
    public EstudianteBasico(Estudiante estudiante){
        super(estudiante);
    }
    public EstudianteBasico(int id, int posicionN, int posicionM, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, int turno) {
        super(id, posicionN, posicionM, generacion, tiempoDeVida, probReproduccion, probClonacion, turno);
    }
    public EstudianteBasico(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, int turno) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, turno);
    }
    public EstudianteBasico(){super();}
    @Override
    public Class<EstudianteBasico> getTipo () {
        return EstudianteBasico.class;
    }
    @Override
    public void mover(DatosJuego dato, Tablero tablero) {
        this.moverseAleatorio(tablero, dato.getTurnoActual());
    }

}
