package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Montaña extends Recursos{
    private int reduccionVida;
    private static double probMontaña;
    private static final Logger logger = LogManager.getLogger();

    public Montaña(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso, int reduccionVida, double probMontaña) {
        super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        this.reduccionVida = reduccionVida;
        Montaña.probMontaña = probMontaña;
    }
    public Montaña(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        reduccionVida = dato.getReduccionVidaMontaña();
    }
    public Montaña(){}
    public Montaña(double probMontaña){
        Montaña.probMontaña = probMontaña;
    }
    public int getReduccionVida(){
        return reduccionVida;
    }
    public void setReduccionVida(int reduccionVida) throws IncrementoNoValido{
        this.reduccionVida = reduccionVida;
        if (reduccionVida < 0) throw new IncrementoNoValido();
        logger.info("Se ha reducido la vida");
    }
    public static double getProbMontaña(){
        return probMontaña;
    }
    public void setProbMontaña(double probMontaña){
        Montaña.probMontaña = probMontaña;
    }
    @Override
    public Class<Montaña> getTipo () {
        return Montaña.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda) {
        if (estudiante.getTiempoDeVida() <= 0) {
            celda.eliminarEstudiante(estudiante);
        }else {
            estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() - reduccionVida);
            logger.info("Efecto aplicado");
        }
    }
}

