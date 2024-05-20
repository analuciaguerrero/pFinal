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
    private static final Logger log = LogManager.getLogger();

    public Montaña(int id, DatosJuego dato){
        super(id,dato);
        reduccionVida = dato.getReduccionVidaMontaña();
    }
    public Montaña(int id, int posicionN, int posicionM, DatosJuego dato) {
        super (id, posicionN, posicionM, dato);
        reduccionVida = dato.getReduccionVidaMontaña();
    }
    public Montaña(){}
    public int getReduccionVida(){
        return reduccionVida;
    }
    public void setReduccionVida(int reduccionVida) throws IncrementoNoValido{
        this.reduccionVida = reduccionVida;
        if (reduccionVida < 0) throw new IncrementoNoValido();
    }
    @Override
    public Class<Montaña> getTipo () {
        return Montaña.class;
    }
    @Override
    public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {
        estudiante.getColaDeOperaciones().add(STR."Acción: efecto Montaña, turno: \{turno}");
        log.debug(STR."Efecto de montaña aplicado a \{estudiante.getId()}");
        if (estudiante.getTiempoDeVida() <= 0) {
            celda.eliminarEstudiante(estudiante);
        }else {
            estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() - reduccionVida, turno);
        }
    }
}

