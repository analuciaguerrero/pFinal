package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaminoTest {
    @Test
    void getCamino() {
        ListaSimple<Vertice> camino = new ListaSimple<>();
        double peso = 10.0;
        Camino c = new Camino(camino, peso);
        assertEquals(camino, c.getCamino());
    }

    @Test
    void getPeso() {
        ListaSimple<Vertice> camino = new ListaSimple<>();
        double peso = 10.0;
        Camino c = new Camino(camino, peso);
        assertEquals(peso, c.getPeso(), 0.001);
    }

    @Test
    void testToString() {
        Vertice v1 = new Vertice(1, 1);
        Vertice v2 = new Vertice(2, 2);
        Vertice v3 = new Vertice(3, 3);
        Vertice v4 = new Vertice(4, 4);

        ListaSimple<Vertice> listavertices = new ListaSimple<>(v1);
        listavertices.add(v2);
        listavertices.add(v3);
        listavertices.add(v4);

        Camino camino = new Camino(listavertices,78);

        StringBuffer salida = new StringBuffer();
        salida.append("======= Volcado del camino desde [" + camino.getCamino().getPrimero().getData().getDato() + "] hasta [" + camino.getCamino().getUltimo().getData().getDato() + "] =======\n");
        salida.append("Referencias a los vértices: " + camino.getCamino() + "\n");
        salida.append("Lista de datos en vértices: [");
        Integer contador = 0;
        while (contador < camino.getCamino().getNumeroElementos()) {
            salida.append(camino.getCamino().getElemento(contador).getData().getDato());
            if (contador+1<camino.getCamino().getNumeroElementos()){
                salida.append(",");
            }
            contador++;
        }
        salida.append("] - Coste: " + camino.getPeso() + "\n");
        assertEquals(salida.toString(),camino.toString());
    }
}