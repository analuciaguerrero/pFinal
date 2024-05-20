package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pozo extends Recursos{
    private static final Logger log = LogManager.getLogger();
    public Pozo(int id, DatosJuego dato) {
        super(id, dato);
    }
    public Pozo(){
    }
    public Pozo(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
    }
    @Override
    public Class<Pozo> getTipo () {
        return Pozo.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {
        celda.eliminarEstudiante(estudiante);// Muerte instantánea
        estudiante.getColaDeOperaciones().add(STR."Acción: efecto Pozo, turno: \{turno}");
        log.debug(STR."Efecto de pozo aplicado a \{estudiante.getId()}");
    }
}

