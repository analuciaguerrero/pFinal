package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Comida extends Recursos{
    private int aumentoVida;
    private static final Logger log = LogManager.getLogger();

    public Comida(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        aumentoVida = dato.getAumentoVidaComida();
    }
    public Comida(){}
    public Comida(int id, DatosJuego dato){
        super(id,dato);
        aumentoVida = dato.getAumentoVidaComida();
    }
    public int getAumentoVida() {
        return aumentoVida;
    }

    public void setAumentoVida(int aumentoVida) throws IncrementoNoValido {
        this.aumentoVida = aumentoVida;
        if (aumentoVida < 0) throw new IncrementoNoValido();
    }
    @Override
    public Class<Comida> getTipo () {
        return Comida.class;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() + aumentoVida, turno);
        estudiante.getColaDeOperaciones().add(STR."Acción: efecto Comida, turno: \{turno}");
        log.debug(STR."Efecto de comida aplicado a \{estudiante.getId()}");
    }
}

