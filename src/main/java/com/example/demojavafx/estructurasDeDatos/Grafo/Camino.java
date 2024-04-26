package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple2;

public class Camino {
    ListaSimple2<Vertice> camino;
    double peso;

    public Camino(ListaSimple2<Vertice> camino, double peso) {
        this.camino = camino;
        this.peso = peso;
    }

    public ListaSimple2<Vertice> getCamino() {
        return camino;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        StringBuffer salida = new StringBuffer();
        salida.append("======= Volcado del camino desde [" + getCamino().getPrimero().getData() + "] hasta [" + getCamino().getUltimo().getData() + "]: ======\n");
        salida.append("Referencias a los vértices: " + this.getCamino() + "\n");
        salida.append("Lista de datos en vértices: [");
        Integer contador = 0;
        while (contador < this.getCamino().getNumeroElementos()) {
            salida.append(this.getCamino().getElemento(contador).getData().getDato());
        }
        salida.append("] - Coste: " + this.getPeso() + "\n");

        return salida.toString();
    }
}
