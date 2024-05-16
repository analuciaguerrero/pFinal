package com.example.demoJavafx.XControllers;
public class VentanaPrincipalDataModel {
    private int vida;
    private int velocidad;
    private String nombre;
    private String apellido;

    public VentanaPrincipalDataModel(int vida, int velocidad, String nombre, String Apellido) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.nombre = nombre;
        this.apellido= Apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
