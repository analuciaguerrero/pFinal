package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
public class Camino<TipoDelDato> {
    ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> camino;
    double peso;

    public Camino(ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> camino, double peso) {
        this.camino = camino;
        this.peso = peso;
    }

    public ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> getCamino() {
        return camino;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        salida.append("Volcado del camino desde [" + getCamino().getPrimero().getData().getDato() + "] hasta [" + getCamino().getUltimo().getData().getDato() + "] =======\n");
        salida.append("Lista de datos en vértices: [");
        int contador = 0;
        while (contador < this.getCamino().getNumeroElementos()) {
            salida.append(this.getCamino().getElemento(contador).getData().getDato());
            if (contador + 1 < this.getCamino().getNumeroElementos()) {
                salida.append(",");
            }
            contador++;
        }
        salida.append("] - Coste: " + this.getPeso() + "\n");

        return salida.toString();
    }
}
