package com.example.demojavafx.estudiante;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada2;

public class EstudianteAvanzado extends Estudiante {
    public EstudianteAvanzado(int I, int G, int T, float PR, float PC) {
        super(I, G, T, PR, PC);
    }
    @Override
    public String getTipo () {
        return "EstudianteAvanzado";
    }

    public void mover (ListaDoblementeEnlazada2<Recursos> recursos, int caminoMaximo) {
        if (!recursos.isVacia()) {
            Recursos recursoCercano = null;
            int recursoCercanoPasos = caminoMaximo + 1;
            for (int i = 0; i != recursos.getNumeroElementos(); i++) {
                int pasosRecurso = Math.abs(recursos.getElemento(i).getData().getPosN() - this.getPosicionX()) + Math.abs(recursos.getElemento(i).getData().getPosM() - this.getPosicionY());
                if (pasosRecurso < recursoCercanoPasos) {
                    recursoCercano = recursos.getElemento(i).getData();
                    recursoCercanoPasos = pasosRecurso;
                }
            }
            assert recursoCercano != null;
            int[] posicionRecurso = recursoCercano.getPosicion();
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
