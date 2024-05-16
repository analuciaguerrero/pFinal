package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.bucleDeControl.BucleDeControl;
import com.example.demoJavafx.bucleDeControl.BucleDeControlProperties;
import com.example.demoJavafx.tablero.Tablero;

public class ZombieStudentsLife {
    private DatosJuego dato;
    private Tablero tablero;
    private BucleDeControl bucle;
    private BucleDeControlProperties propiedad;

    public ZombieStudentsLife(DatosJuego dato) {
        this.dato = dato;
        tablero = new Tablero(dato.getFilasDelTablero(), dato.getColumnasDelTablero(), dato);
        bucle = new BucleDeControl(tablero, dato);
    }
    public ZombieStudentsLife(DatosJuego dato, Tablero tablero) {
        this.dato = dato;
        bucle = new BucleDeControl(tablero, dato);
    }
    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public BucleDeControl getBucle() {
        return bucle;
    }

    public void setBucle(BucleDeControl bucle) {
        this.bucle = bucle;
    }

    public BucleDeControlProperties getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(BucleDeControlProperties propiedad) {
        this.propiedad = propiedad;
    }

    public void start(boolean turno) {
        if (turno) {
            bucle.setTurno(true);
        } else {
            bucle.setTurno(false);
        }
        Thread threadBucle = new Thread(bucle);
        threadBucle.start();
    }

    public ZombieStudentsLife CargarJuego () {
        return this;
    }
}