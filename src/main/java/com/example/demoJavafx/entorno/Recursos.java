package com.example.demoJavafx.entorno;
import com.example.demoJavafx.estudiante.Estudiante;
public abstract class Recursos {

    protected int turnosRestantes;

    public Recursos(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }
    public void actualizarRecursos(){
        turnosRestantes--;
    }

    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    public abstract void aplicarEfecto(Estudiante estudiante);

}
