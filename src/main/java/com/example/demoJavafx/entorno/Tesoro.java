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
    private static double probTesoro;
    private static final Logger logger = LogManager.getLogger();

    public Tesoro(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso, double aumentoProbReproduccion, double probTesoro) {
        super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoProbReproduccion = aumentoProbReproduccion;
        Tesoro.probTesoro = probTesoro;
    }
    public Tesoro(){}
    public Tesoro(double probTesoro){
        Tesoro.probTesoro = probTesoro;
    }
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
        logger.info("Se ha aumentado la probabilidad de reproducción");
    }
    public static double getProbTesoro(){
        return  probTesoro;
    }
    public void setProbTesoro(double probTesoro){
        Tesoro.probTesoro = probTesoro;
    }
    @Override
    public Class<Tesoro> getTipo () {
        return Tesoro.class;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda) {
        if (estudiante.getProbReproduccion() + aumentoProbReproduccion > 100) {
            estudiante.setProbReproduccion(100);
        } else {
            estudiante.setProbReproduccion(estudiante.getProbReproduccion() + aumentoProbReproduccion);
        }
        logger.info("Efecto aplicado");
    }
}
