package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import com.example.demoJavafx.zombieStudentsLife.Tablero;

import java.util.Random;

public class EstudianteBasico extends Estudiante<EstudianteBasico> {
    public EstudianteBasico(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
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
