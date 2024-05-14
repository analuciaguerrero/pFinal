package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.zombieStudentsLife.Tablero;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class EstudianteNormal extends Estudiante<EstudianteNormal> {
    @Expose
    private final String nombreClase = "EstudianteNormal";
    private static final Logger log = LogManager.getLogger(EstudianteNormal.class);
    public EstudianteNormal(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion);
    }
    public EstudianteNormal(Estudiante estudiante){
        super(estudiante);
    }

    @Override
    public Class<EstudianteNormal> getTipo () {
        return EstudianteNormal.class;
    }
    @Override
    public void mover(DatosJuego dato, Tablero tablero) {
        if (dato.getRecursos().isVacia()) {
            moverseAleatorio(tablero);
        } else {
            Random a = new Random();
            int numAleatorio = a.nextInt(dato.getRecursos().getNumeroElementos());
            Recursos recursoObjetivo = dato.getRecursos().getElemento(numAleatorio).getData();

            if (Math.abs(recursoObjetivo.getPosicionN() - getPosicionN()) >
                    Math.abs(recursoObjetivo.getPosicionM() - getPosicionM())) {
                if (recursoObjetivo.getPosicionN() - getPosicionN() < 0) {
                    cambiarDePosicion(getPosicionN() - 1, getPosicionM(), tablero);
                } else {
                    cambiarDePosicion(getPosicionN() + 1, getPosicionM(), tablero);
                }
            } else {
                if (recursoObjetivo.getPosicionM() - getPosicionM() < 0) {
                    cambiarDePosicion(getPosicionN(), getPosicionM() - 1, tablero);
                } else {
                    cambiarDePosicion(getPosicionN(), getPosicionM() + 1, tablero);
                }
            }
        }
        log.info("Movimiento realizado por el estudiante normal.");
    }
}

