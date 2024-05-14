package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Agua extends Recursos<Agua> {
    @Expose
    private final String nombreTipo = "Agua";
    private int aumentoVida;
    private static double probAgua;
    private static final Logger logger = LogManager.getLogger(Agua.class);

    public Agua(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso, int aumentoVida, double probAgua) {
        super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        this.aumentoVida = aumentoVida;
        Agua.probAgua = probAgua;
    }
    public Agua(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        aumentoVida = dato.getAumentoVidaAgua();
    }
    public Agua() {
    }

    public Agua(double probAgua) {
        Agua.probAgua = probAgua;
    }
    public int getAumentoVida() {
        return aumentoVida;
    }

    public void setAumentoVida(int aumentoVida) throws IncrementoNoValido {
        this.aumentoVida = aumentoVida;
        if (aumentoVida < 0) throw new IncrementoNoValido();
        logger.info("Se ha aumentado la vida");
    }

    public static double getProbAgua() {
        return probAgua;
    }

    public void setProbAgua(double probAgua) {
        Agua.probAgua = probAgua;
    }
    @Override
    public Class<Agua> getTipo () {
        return Agua.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() + aumentoVida);
        logger.info("Efecto aplicado");
    }
}

