package com.example.demojavafx.estudiante;

import com.example.demojavafx.zombieStudentsLife.Celda;

import java.util.Random;

public class EstudianteBasico extends Estudiante {
    public EstudianteBasico(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
    }

    @Override
    public void mover(Celda[][] tablero) {
        Random rand = new Random();
        int filaActual = getPosicionN();
        int columnaActual = getPosicionM();
        // El estudiante básico se puede mover en cualquier dirección
        // Verificar si la nueva posición es válida
        if (filaActual >= 0 && filaActual < tablero.length && columnaActual >= 0 && columnaActual < tablero[0].length) {
            // Mover el estudiante a la nueva posición
            tablero[getPosicionN()][getPosicionM()].eliminarEstudiante(this);
            setPosicionN(filaActual);
            setPosicionM(columnaActual);
            tablero[getPosicionN()][getPosicionM()].agregarEstudiante(this);
        }
    }

    @Override
    public Estudiante reproducirse(Estudiante pareja) {
        Random rand = new Random();
        int nuevoId = rand.nextInt(Integer.MAX_VALUE);
        int nuevaGeneracion = Math.max(getGeneracion(), pareja.getGeneracion()) + 1;
        int nuevosTurnosVida = (getTiempoDeVida() + pareja.getTiempoDeVida()) / 2;
        double nuevaProbabilidadReproduccion = (getProbReproduccion() + pareja.getProbReproduccion()) / 2;
        double nuevaProbabilidadClonacion = (getProbClonacion() + pareja.getProbClonacion()) / 2;
        double nuevaProbabilidadMuerte = (getProbMuerte() + pareja.getProbMuerte()) / 2;
        return new EstudianteBasico(nuevoId, nuevaGeneracion, nuevosTurnosVida, nuevaProbabilidadReproduccion, nuevaProbabilidadClonacion, nuevaProbabilidadMuerte);
    }

    @Override
    public Estudiante clonar() {
        Random rand = new Random();
        int nuevoId = rand.nextInt(Integer.MAX_VALUE);
        return new EstudianteBasico(nuevoId, getGeneracion(), getTiempoDeVida(), getProbReproduccion(), getProbClonacion(), getProbMuerte());
    }
}
