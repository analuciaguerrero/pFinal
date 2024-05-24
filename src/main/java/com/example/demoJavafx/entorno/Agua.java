package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.tablero.Celda;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Agua extends Recursos {
    private double aumentoVida;
    private static final Logger log = LogManager.getLogger();
    public Agua(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        aumentoVida = dato.getAumentoVidaAgua();
    }
    public Agua() {
    }

    public Agua(int id, DatosJuego dato){
        super(id,dato);
        aumentoVida = dato.getAumentoVidaAgua();
    }
    public double getAumentoVida() {
        return aumentoVida;
    }

    public void setAumentoVida(int aumentoVida) throws IncrementoNoValido {
        this.aumentoVida = aumentoVida;
        if (aumentoVida < 0) throw new IncrementoNoValido();
    }
    @Override
    public Class<Agua> getTipo () {
        return Agua.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {
        estudiante.getColaDeOperaciones().add(STR."Acción: efecto Agua, turno: \{turno}");
        log.debug(STR."Efecto de agua aplicado a \{estudiante.getId()}");
        estudiante.setTiempoDeVida((int) (estudiante.getTiempoDeVida() + aumentoVida), turno);
    }
}

