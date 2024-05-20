package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tesoro extends Recursos{
    private double aumentoProbReproduccion;
    private static final Logger log = LogManager.getLogger();

    public Tesoro(int id, DatosJuego dato) {
        super (id, dato);
        aumentoProbReproduccion = dato.getAumentoProbReproduccion();
    }
    public Tesoro(){}
    public Tesoro(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        aumentoProbReproduccion = dato.getAumentoProbReproduccion();
    }
    public double getAumentoProbReproduccion(){
        return aumentoProbReproduccion;
    }
    public void setAumentoProbReproduccion(double aumentoProbReproduccion) throws ProbabilidadNoValida {
        this.aumentoProbReproduccion = aumentoProbReproduccion;
        if (aumentoProbReproduccion < 0 || aumentoProbReproduccion > 100) throw new ProbabilidadNoValida();
    }
    @Override
    public Class<Tesoro> getTipo () {
        return Tesoro.class;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {
        estudiante.getColaDeOperaciones().add(STR."Acción: efecto Tesoro, turno: \{turno}");
        log.debug(STR."Efecto de tesoro aplicado a \{estudiante.getId()}");
        if (estudiante.getProbReproduccion() + aumentoProbReproduccion > 100) {
            estudiante.setProbReproduccion(100, turno);
        } else {
            estudiante.setProbReproduccion(estudiante.getProbReproduccion() + aumentoProbReproduccion, turno);
        }

    }
}
