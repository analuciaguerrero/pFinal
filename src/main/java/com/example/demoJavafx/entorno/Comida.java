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
    private static double probComida;
    private static final Logger logger = LogManager.getLogger();

    public Comida(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso, int aumentoVida, double probComida) {
        super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoVida = aumentoVida;
        Comida.probComida = probComida;
    }
    public Comida(){}
    public Comida(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        aumentoVida = dato.getAumentoVidaComida();
    }
    public Comida(double probComida){
        Comida.probComida = probComida;
    }
    public int getAumentoVida() {
        return aumentoVida;
    }

    public void setAumentoVida(int aumentoVida) throws IncrementoNoValido {
        this.aumentoVida = aumentoVida;
        if (aumentoVida < 0) throw new IncrementoNoValido();
        logger.info("Se ha aumentado la vida");
    }

    public static double getProbComida() {
        return probComida;
    }

    public void setProbComida(double probComida) {
        Comida.probComida = probComida;
    }
    @Override
    public Class<Comida> getTipo () {
        return Comida.class;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() + aumentoVida);
        logger.info("Efecto aplicado");
    }
}

