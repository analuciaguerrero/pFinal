package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
//import com.example.demojavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.estudiante.EstudianteNormal;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Biblioteca extends Recursos<Biblioteca> {
    @Expose
    private final String nombreTipo = "Biblioteca";
    private double aumentoProbClonacion;
    private static double probBiblioteca;
    private static final Logger logger = LogManager.getLogger(Biblioteca.class);
    public Biblioteca(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso, double aumentoProbClonacion, double probBiblioteca) {
        super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoProbClonacion = aumentoProbClonacion;
        Biblioteca.probBiblioteca = probBiblioteca;
    }

    public Biblioteca() {
    }
    public Biblioteca(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        aumentoProbClonacion = dato.getAumentoProbClonacion();
    }

    public Biblioteca(double probBiblioteca) {
        Biblioteca.probBiblioteca = probBiblioteca;
    }
    public double getAumentoProbClonacion(){
        return aumentoProbClonacion;
    }
    public void setAumentoProbClonacion(double aumentoProbClonacion) throws ProbabilidadNoValida {
        this.aumentoProbClonacion = aumentoProbClonacion;
        if (aumentoProbClonacion < 0 || aumentoProbClonacion > 100) throw new ProbabilidadNoValida();
        logger.info("Se ha aumentado la probabilidad de clonación");
    }
    public static double getProbBiblioteca() {
        return probBiblioteca;
    }

    public void setProbBiblioteca(double probBiblioteca) {
        Biblioteca.probBiblioteca = probBiblioteca;
    }
    @Override
    public Class<Biblioteca> getTipo () {
        return Biblioteca.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda) {
        if (estudiante.getProbClonacion() + aumentoProbClonacion > 100) {
            estudiante.setProbClonacion(100);
        } else {
            estudiante.setProbClonacion(estudiante.getProbClonacion() + aumentoProbClonacion);
        }
        if (estudiante.getTipo() == EstudianteBasico.class) {
            celda.agregarEstudiante(new EstudianteNormal(estudiante));
            celda.eliminarEstudiante(estudiante);
        } else if (estudiante.getTipo() == EstudianteNormal.class) {
            celda.agregarEstudiante(new EstudianteAvanzado(estudiante));
            celda.eliminarEstudiante(estudiante);
        }
        logger.info("Efecto aplicado");
    }
}


