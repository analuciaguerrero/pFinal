package com.example.demojavafx.zombieStudentsLife;

public class Celda{
    int x;
    int y;
    boolean ocupado;
    String nombre;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.ocupado = false;
    }

    @Override
    public String toString(){
        String status;
        if(this.ocupado) status="Ocupado";
        else status="Libre";
        return "Celda" + this.x + this.y + ": " + status;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
