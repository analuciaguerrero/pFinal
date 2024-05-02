package com.example.demojavafx.entorno;
import com.example.demojavafx.estudiante.Estudiante;
public abstract class Recursos {

    protected int turnosRestantes;

    public Recursos(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    public abstract void aplicarEfecto(Estudiante estudiante);

}
