package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Montaña;
import com.example.demoJavafx.entorno.Pozo;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Camino;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Grafo;
import com.example.demoJavafx.estructurasDeDatos.Grafo.NodoGrafo;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EstudianteAvanzado extends Estudiante {
    private static final Logger log = LogManager.getLogger(EstudianteAvanzado.class);
    public EstudianteAvanzado(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion) {
        super(id, generacion, tiempoDeVida, probReproduccion, probClonacion);
    }
    public EstudianteAvanzado(Estudiante estudiante){
        super(estudiante);
    }
    @Override
    public Class<EstudianteAvanzado> getTipo () {
        return EstudianteAvanzado.class;
    }
    private Grafo<Celda> getGrafoTab(DatosJuego dato, Tablero tablero) {
        Grafo<Celda> grafoTab = new Grafo<>(false);
        int reduccionMontaña = dato.getReduccionVidaMontaña();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Celda celda= tablero.getCelda(i, j);
                grafoTab.addNodo(celda);
                if (i > 0) {
                    Celda vert = tablero.getCelda(i - 1, j);
                    int pesoArco = calcularPesoArista(celda, vert, reduccionMontaña);
                    grafoTab.addArista(pesoArco, celda, vert);
                }
                if (j > 0) {
                    Celda vert2 = tablero.getCelda(i, j - 1);
                    int pesoArco2 = calcularPesoArista(celda, vert2, reduccionMontaña);
                    grafoTab.addArista(pesoArco2, celda, vert2);
                }
                if (i > 0 && j > 0) {
                    Celda vert3 = tablero.getCelda(i - 1, j - 1);
                    int pesoArco3 = calcularPesoArista(celda, vert3, reduccionMontaña);
                    grafoTab.addArista(pesoArco3, celda, vert3);
                }
                if (i > 0 && j < tablero.getColumnas() - 1) {
                    Celda vert4 = tablero.getCelda(i - 1, j + 1);
                    int pesoArco4 = calcularPesoArista(celda, vert4, reduccionMontaña);
                    grafoTab.addArista(pesoArco4, celda, vert4);
                }
            }
        }
        return grafoTab;
    }

    private int calcularPesoArista(Celda vertice1, Celda vertice2, int reduccionMontaña) {
        int peso = 1;
        peso = addPesoObs(peso, vertice1, reduccionMontaña);
        peso = addPesoObs(peso, vertice2, reduccionMontaña);
        return peso;
    }

    private int addPesoObs(int peso, Celda vertice, int reduccionMontaña) {
        for (int i = 0; i < vertice.getListaRecursos().getNumeroElementos(); i++) {
            switch (vertice.getListaRecursos().getElemento(i).getData().getTipo().getSimpleName()) {
                case "Montaña":
                    if (peso < Integer.MAX_VALUE / 2) {
                        peso += reduccionMontaña;
                    }
                    break;
                case "Pozo":
                    peso = Integer.MAX_VALUE / 2;
                    break;
                default:
                    log.trace("El recurso tiene un peso de 1");
            }
        }
        return peso;
    }
    @Override
    public void mover(DatosJuego dato, Tablero tablero) throws RecursosNoUtilizados {
        ListaDoblementeEnlazada<Recursos> recursoDeseado = new ListaDoblementeEnlazada<>();
        for (int i = 0; i < dato.getRecursos().getNumeroElementos(); i++) {
            Recursos recurso = dato.getRecursos().getElemento(i).getData();
            if (!recurso.getTipo().equals(Montaña.class) && !recurso.getTipo().equals(Pozo.class) && !recurso.getPosicion().equals(getPosicion())) {
                recursoDeseado.add(recurso);
            }
        }
        if (recursoDeseado.isVacia()) {
            this.moverseAleatorio(tablero);
        } else {
            Grafo<Celda> grafoTablero = getGrafoTab(dato, tablero);

            Camino<Celda> caminoMin = new Camino<>(new ListaDoblementeEnlazada<>(), Integer.MAX_VALUE);
            for (int m = 0; m < recursoDeseado.getNumeroElementos(); m++) {
                NodoGrafo<Celda> nodoCelda = grafoTablero.getNodoGrafo(tablero.getCelda(getPosicionN(), getPosicionM() ));
                NodoGrafo<Celda> nodoRecurso = grafoTablero.getNodoGrafo(tablero.getCelda(recursoDeseado.getElemento(m).getData().getPosicionN(), getPosicionM()));
                Camino<Celda> caminoRecurso = grafoTablero.getCaminoMinimo(nodoCelda, nodoRecurso);
                if (caminoRecurso == null) {
                    throw new RecursosNoUtilizados(this);
                }
                if (caminoRecurso.getPeso() <= caminoMin.getPeso()) {
                    caminoMin = caminoRecurso;
                }
            }
            Celda celda= caminoMin.getCamino().getElemento(1).getData().getDato();
            cambiarDePosicion(celda.getPosicionN(), celda.getPosicionM(), tablero);
            colaDeOperaciones.push(new ElementoLDE<>("movimiento"));
        }
    }
}
