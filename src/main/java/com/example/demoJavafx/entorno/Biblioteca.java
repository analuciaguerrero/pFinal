package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
//import com.example.demojavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.estudiante.EstudianteNormal;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Biblioteca extends Recursos {
    private double aumentoProbClonacion;
    private static final Logger log = LogManager.getLogger();
    public Biblioteca(int id, int posicionN, int posicionM, DatosJuego dato) {
        super(id, posicionN, posicionM, dato);
        this.aumentoProbClonacion = dato.getAumentoProbClonacion();
    }
    public Biblioteca() {
    }
    public Biblioteca(int id, DatosJuego dato){
        super(id, dato);
        this.aumentoProbClonacion = dato.getAumentoProbClonacion();
    }
    public double getAumentoProbClonacion(){
        return aumentoProbClonacion;
    }
    public void setAumentoProbClonacion(double aumentoProbClonacion) throws ProbabilidadNoValida {
        this.aumentoProbClonacion = aumentoProbClonacion;
        if (aumentoProbClonacion < 0 || aumentoProbClonacion > 100) throw new ProbabilidadNoValida();
    }
    @Override
    public Class<Biblioteca> getTipo () {
        return Biblioteca.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {
        estudiante.getColaDeOperaciones().add(STR."Acción: efecto Biblioteca, turno: \{turno}");
        log.debug(STR."Efecto de biblioteca aplicado a \{estudiante.getId()}");
        if (estudiante.getProbClonacion() + aumentoProbClonacion > 100) {
            estudiante.setProbClonacion(100, turno);
        } else {
            estudiante.setProbClonacion(estudiante.getProbClonacion() + aumentoProbClonacion, turno);
        }
        if (estudiante.getTipo() == EstudianteBasico.class) {
            celda.agregarEstudiante(new EstudianteNormal(estudiante), true);
            celda.eliminarEstudiante(estudiante);
            estudiante.getColaDeOperaciones().add(STR."Acción: evolucionar estudiante, turno: \{turno}");
            log.debug(STR."Efecto de biblioteca aplicado a \{estudiante.getId()}");
        } else if (estudiante.getTipo() == EstudianteNormal.class) {
            celda.agregarEstudiante(new EstudianteAvanzado(estudiante), true);
            celda.eliminarEstudiante(estudiante);
            estudiante.getColaDeOperaciones().add(STR."Acción: efecto Biblioteca, turno: \{turno}");
            log.debug(STR."Efecto de biblioteca aplicado a \{estudiante.getId()}");
        }
    }
}


