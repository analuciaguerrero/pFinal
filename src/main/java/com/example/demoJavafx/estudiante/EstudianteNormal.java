package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.tablero.Tablero;
import com.google.gson.annotations.Expose;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class EstudianteNormal extends Estudiante {
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
    public void mover(DatosJuego dato, Tablero tablero) throws RecursosNoUtilizados {
        if (dato.getRecursos().isVacia()) {
            moverseAleatorio(tablero);
        } else {
            Random a = new Random();
            int aleatorio = a.nextInt(dato.getRecursos().getNumeroElementos());
            Recursos recurso = dato.getRecursos().getElemento(aleatorio).getData();
            int distanciaN = recurso.getPosicionN() - getPosicionN();
            int distanciaM = recurso.getPosicionM() - getPosicionM();
            int destinoN = getPosicionN();
            int destinoM = getPosicionM();
            if (distanciaN == 0 && distanciaM == 0) throw new RecursosNoUtilizados(this);
            if (distanciaN < 0) {
                destinoN -= 1;
            } else if (distanciaN > 0) {
                destinoN += 1;
            }
            if (distanciaM < 0) {
                destinoM -= 1;
            } else if (distanciaM > 0) {
                destinoM += 1;
            }
            cambiarDePosicion(destinoN, destinoM, tablero);
            colaDeOperaciones.push(new ElementoLDE<>("movimiento"));
        }
        log.info("Movimiento realizado por el estudiante normal.");
    }
}

