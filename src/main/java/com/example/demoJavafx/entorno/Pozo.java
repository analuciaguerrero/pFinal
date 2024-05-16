package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pozo extends Recursos{
    private static double probPozo;
    private static final Logger logger = LogManager.getLogger(Pozo.class);
    public Pozo(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso, double probPozo) {
        super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        Pozo.probPozo = probPozo;
    }
    public Pozo(){
    }
    public Pozo(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
    }
    public Pozo(double probPozo){
        Pozo.probPozo = probPozo;
    }
    public static double getProbPozo(){
        return probPozo;
    }
    public void setProbPozo(double probPozo){
        Pozo.probPozo = probPozo;
    }
    @Override
    public Class<Pozo> getTipo () {
        return Pozo.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda) {
        celda.eliminarEstudiante(estudiante);// Muerte instantánea
        logger.info("Efecto aplicado");
    }
}

