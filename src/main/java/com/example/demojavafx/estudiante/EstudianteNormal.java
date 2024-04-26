package com.example.demojavafx.estudiante;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada2;

import java.util.Random;

public class EstudianteNormal extends Estudiante{
    public EstudianteNormal(int I, int G, int T, float PR, float PC) {
        super(I, G, T, PR, PC);
    }
    @Override
    public String getTipo () {
        return "EstudianteNormal";
    }

    public void mover(ListaDoblementeEnlazada2<Recursos> recursos) {
        if (!recursos.isVacia()) {
            Random r = new Random();
            int recursoRandom = r.nextInt(recursos.getNumeroElementos());
            int[] posicionRecurso = recursos.getElemento(recursoRandom).getData().getPosicion();
            if (Math.abs(posicionRecurso[0] - this.getPosicionX()) > Math.abs(posicionRecurso[1] - this.getPosicionY())) {
                if (posicionRecurso[0] - this.getPosicionX() < 0) {
                    this.setPosicionX(this.getPosicionX() - 1);
                } else {
                    this.setPosicionX(this.getPosicionX() + 1);
                }
            } else {
                if (posicionRecurso[1] - this.getPosicionY() < 0) {
                    this.setPosicionY(this.getPosicionY() - 1);
                } else {
                    this.setPosicionY(this.getPosicionY() + 1);
                }
            }
        } else {
            this.moverAleatorio();
        }
    }
}
