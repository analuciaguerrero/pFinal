package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;

import java.util.Random;

public class EstudianteNormal extends Estudiante {
        public EstudianteNormal(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
    }
    @Override
    public String getTipo() {
        return "EstudianteNormal";
    }

    @Override
    public void mover(ListaEnlazada<Celda> tablero) {
        Random rand = new Random();
        int filaActual = getPosicionN();
        int columnaActual = getPosicionM();

        // Determinar la dirección de movimiento (arriba, abajo, izquierda, derecha)
        int direccion = rand.nextInt(4);
        switch (direccion) {
            case 0: // Arriba
                filaActual--;
                break;
            case 1: // Abajo
                filaActual++;
                break;
            case 2: // Izquierda
                columnaActual--;
                break;
            case 3: // Derecha
                columnaActual++;
                break;
        }

        // Verificar si la nueva posición es válida
        if (filaActual >= 0 && filaActual < tablero.getNumeroElementos()) {
            // Mover el estudiante a la nueva posición
            ElementoLE<Celda> elementoActual = tablero.getElemento(filaActual);
            ElementoLE<Celda> elementoAnterior = null;
            for (int i = 0; i < columnaActual; i++) {
                elementoAnterior = elementoActual;
                elementoActual = elementoActual.getSiguiente();
            }
            if (elementoAnterior != null) {
                elementoAnterior.setSiguiente(elementoActual.getSiguiente());
                elementoActual.getSiguiente().setData(elementoAnterior.getData());
            } else {
                tablero.getPrimero().setSiguiente(elementoActual.getSiguiente());
            }
            ElementoLE<Celda> nuevoElemento = new ElementoLE<>(elementoActual.getData());
            nuevoElemento.setSiguiente(tablero.getElemento(filaActual + 1));
            tablero.getElemento(filaActual + 1).setSiguiente(nuevoElemento);
        }
    }


    @Override
    public Estudiante reproducirse(Estudiante pareja) {
        Random rand = new Random();
        int nuevoId = rand.nextInt(Integer.MAX_VALUE);
        int nuevaGeneracion = Math.max(getGeneracion(), pareja.getGeneracion()) + 1;
        int nuevosTurnosVida = (getTiempoDeVida() + pareja.getTiempoDeVida()) / 2;
        double nuevaProbabilidadReproduccion = (getProbReproduccion() + pareja.getProbReproduccion()) / 2;
        double nuevaProbabilidadClonacion = (getProbClonacion() + pareja.getProbClonacion()) / 2;
        double nuevaProbabilidadMuerte = (getProbMuerte() + pareja.getProbMuerte()) / 2;
        return new EstudianteNormal(nuevoId, nuevaGeneracion, nuevosTurnosVida, nuevaProbabilidadReproduccion, nuevaProbabilidadClonacion, nuevaProbabilidadMuerte);
    }

    @Override
    public Estudiante clonar() {
        Random rand = new Random();
        int nuevoId = rand.nextInt(Integer.MAX_VALUE);
        return new EstudianteNormal(nuevoId, getGeneracion(), getTiempoDeVida(), getProbReproduccion(), getProbClonacion(), getProbMuerte());
    }
}

