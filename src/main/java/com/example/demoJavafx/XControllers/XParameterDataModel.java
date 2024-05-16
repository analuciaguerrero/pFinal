package com.example.demoJavafx.XControllers;

/** Esta es la clase del modelo de datos básico **/
public class XParameterDataModel {

    private int vida;
    private int velocidad;
    private String nombre;

    /** Constructor **/
    public XParameterDataModel(int vida, int velocidad, String nombre) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.nombre = nombre;
    }

    /** Setters y Getters **/
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
