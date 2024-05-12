package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import java.util.Random;
public class EstudianteAvanzado extends Estudiante {
    public EstudianteAvanzado(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
    }
    @Override
    public String getTipo() {
        return "EstudianteAvanzado";
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
            // Obtener la celda actual
            ElementoLE<Celda> elementoCeldaActual = tablero.getElemento(filaActual);
            ElementoLE<Celda> celdaActual = elementoCeldaActual.getSiguiente();
            for (int i = 0; i < columnaActual; i++) {
                celdaActual = celdaActual.getSiguiente();
            }

            // Obtener los recursos disponibles en la celda actual
            ListaEnlazada<Recursos> recursosDisponibles = celdaActual.getData().getListaRecursos();

            if (!recursosDisponibles.isVacia()) {
                // Seleccionar aleatoriamente un recurso como destino
                int index = rand.nextInt(recursosDisponibles.getNumeroElementos());
                Recursos destino = recursosDisponibles.getElemento(index).getData();

                // Aplicar efecto del recurso al estudiante
                destino.aplicarEfecto(this);

                // Mover al estudiante hacia la nueva posición
                setPosicionN(filaActual);
                setPosicionM(columnaActual);
            }
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
        return new EstudianteAvanzado(nuevoId, nuevaGeneracion, nuevosTurnosVida, nuevaProbabilidadReproduccion, nuevaProbabilidadClonacion, nuevaProbabilidadMuerte);
    }

    @Override
    public Estudiante clonar() {
        Random rand = new Random();
        int nuevoId = rand.nextInt(Integer.MAX_VALUE);
        return new EstudianteAvanzado(nuevoId, getGeneracion(), getTiempoDeVida(), getProbReproduccion(), getProbClonacion(), getProbMuerte());
    }
}
