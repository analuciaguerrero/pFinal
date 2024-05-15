package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.tablero.Tablero;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EstudianteAvanzado extends Estudiante<EstudianteAvanzado> {
    @Expose
    private final String nombreClase = "EstudianteAvanzado";
    private static final Logger log = LogManager.getLogger(EstudianteAvanzado.class);
    public EstudianteAvanzado(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion);
    }
    public EstudianteAvanzado(Estudiante estudiante){
        super(estudiante);
    }
    @Override
    public Class<EstudianteAvanzado> getTipo () {
        return EstudianteAvanzado.class;
    }
    @Override
    public void mover(DatosJuego dato, Tablero tablero) {
    }
}
