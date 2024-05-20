package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.excepciones.CaminoNulo;
import com.example.demoJavafx.excepciones.NonexistentElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Grafo<TipoDeDatos> {
    private ListaDoblementeEnlazada<NodoGrafo<TipoDeDatos>> nodos = new ListaDoblementeEnlazada<>();

    private ListaDoblementeEnlazada<Arista<TipoDeDatos>> aristas = new ListaDoblementeEnlazada<>();
    private boolean isDirigido;
    private static final Logger log = LogManager.getLogger(Grafo.class);
    public Grafo() {}
    public Grafo(ListaDoblementeEnlazada<NodoGrafo<TipoDeDatos>> nodos, ListaDoblementeEnlazada<Arista<TipoDeDatos>> aristas){
        this.nodos= nodos;
        this.aristas= aristas;
    }
    public Grafo(boolean isDirigido) {
        this.isDirigido = isDirigido;
    }
    public ListaDoblementeEnlazada<NodoGrafo<TipoDeDatos>> getNodos() {
        return nodos;
    }

    public void setNodos(ListaDoblementeEnlazada<NodoGrafo<TipoDeDatos>> nodos) {
        this.nodos = nodos;
    }

    public ListaDoblementeEnlazada<Arista<TipoDeDatos>> getAristas() {
        return aristas;
    }

    public void setAristas(ListaDoblementeEnlazada<Arista<TipoDeDatos>> aristas) {
        this.aristas = aristas;
    }

    public boolean isDirigido() {
        return isDirigido;
    }

    public void setDirigido(boolean dirigido) {
        isDirigido = dirigido;
        for (int i=0; i != aristas.getNumeroElementos(); i++) {
            aristas.getElemento(i).getData().setDirigido(dirigido);
        }
    }
    public void addNodo (NodoGrafo<TipoDeDatos> nodo) {
        nodos.add(nodo);
    }
    public void addNodo (TipoDeDatos dato) {
        NodoGrafo<TipoDeDatos> nodo = new NodoGrafo<>(dato);
        nodos.add(nodo);
    }
    public void addNodo (NodoGrafo<TipoDeDatos> nodo, NodoGrafo<TipoDeDatos> nodo2, double pesoArco) {
        nodos.add(nodo);
        addArista(pesoArco, nodo, nodo2);
    }

    public void addArista(double peso, TipoDeDatos datoIni, TipoDeDatos datoFin, String anotacion) {
        NodoGrafo<TipoDeDatos> nodoIni = getNodoGrafo(datoIni);
        NodoGrafo<TipoDeDatos> nodoFin = getNodoGrafo(datoFin);
        Arista<TipoDeDatos> arista = new Arista<>(peso, nodoIni, nodoFin, anotacion, this.isDirigido);
        if (isDirigido) {
            nodoFin.getListaEntrada().add(arista);
            nodoIni.getListaSalida().add(arista);
        } else {
            nodoFin.getListaSalida().add(arista);
            nodoIni.getListaSalida().add(arista);
        }
        aristas.add(arista);
    }
    public void addArista (double peso, NodoGrafo<TipoDeDatos> nodoIni, NodoGrafo<TipoDeDatos> nodoFin) {
        Arista<TipoDeDatos> arista = new Arista<>(peso, nodoIni, nodoFin, this.isDirigido);
        if (isDirigido) {
            nodoFin.getListaEntrada().add(arista);
            nodoIni.getListaSalida().add(arista);
        } else {
            nodoFin.getListaSalida().add(arista);
            nodoIni.getListaSalida().add(arista);
        }
        aristas.add(arista);
    }

    public void addArista (double peso, TipoDeDatos datoIni, TipoDeDatos datoFin) {
        NodoGrafo<TipoDeDatos> nodoIni = getNodoGrafo(datoIni);
        NodoGrafo<TipoDeDatos> nodoFin = getNodoGrafo(datoFin);

        addArista(peso, nodoIni, nodoFin);
    }

    public void delNodo (TipoDeDatos dato) {
        NodoGrafo<TipoDeDatos> nodo = getNodoGrafo(dato);
        for (int i=0; i <= nodos.getNumeroElementos(); i++) {
            if (nodos.getElemento(i).getData().getDato() == nodo.getDato()) {
                nodos.del(i);
            }
        }
    }

    public void delArista (String anotacion) {
        Arista<TipoDeDatos> arista = getArista(anotacion);
        for (int i = 0; i <= aristas.getNumeroElementos(); i++) {
            if (aristas.getElemento(i).getData().getAnotacion().equals(arista.getAnotacion())) {
                aristas.del(i);
            }
        }
    }
    public NodoGrafo<TipoDeDatos> getNodoGrafo(TipoDeDatos dato) {
        try {
            for (int i = 0; i != nodos.getNumeroElementos(); i++) {
                if (nodos.getElemento(i).getData().getDato() == dato) return nodos.getElemento(i).getData();
            }
            throw new NonexistentElement();
        } catch (NonexistentElement e) {
            log.warn("El nodo no existe, por lo que no se ha añadido al grafo");
            return null;
        }
    }

    public Arista<TipoDeDatos> getArista (String anotacion) {
        try {
            for (int i = 0; i != aristas.getNumeroElementos(); i++) {
                if (aristas.getElemento(i).getData().getAnotacion().equals(anotacion)) return aristas.getElemento(i).getData();
            }
            throw new NonexistentElement();
        } catch (NonexistentElement e) {
            log.warn("La arista no existe, por lo que no se ha añadido al grafo");
            return null;
        }
    }
    public Camino<TipoDeDatos> getCaminoMinimo (NodoGrafo<TipoDeDatos> nodoIni, NodoGrafo<TipoDeDatos> nodoFin) {
        try {
            Mapa<NodoGrafo<TipoDeDatos>, Camino<TipoDeDatos>> grafoMapa = dijkstra(nodoIni);
            if (grafoMapa.get(nodoFin) == null) throw new CaminoNulo();
            return grafoMapa.get(nodoFin);
        } catch (CaminoNulo e) {
            log.warn("El nodo " + nodoFin + " (" + nodoFin.getDato() + ")" + " no existe o es inaccesible desde " + nodoIni + " (" + nodoIni.getDato() + ")");
            return null;
        }
    }

    public Mapa<NodoGrafo<TipoDeDatos>, Camino<TipoDeDatos>> dijkstra (NodoGrafo<TipoDeDatos> nodoIni) {
        Mapa<NodoGrafo<TipoDeDatos>, Double> distancia = new Mapa<>();
        Cola<NodoGrafo<TipoDeDatos>> cola = new Cola<>();
        Mapa<NodoGrafo<TipoDeDatos>, NodoGrafo<TipoDeDatos>> vertices = new Mapa<>();
        this.dijkstra_inicial(nodoIni,distancia,cola);
        this.dijkstra_calcular(distancia,cola,vertices);
        return this.dijkstra_procesarResultados(distancia,vertices);
    }

    private void dijkstra_inicial (NodoGrafo<TipoDeDatos> nodo, Mapa<NodoGrafo<TipoDeDatos>, Double> distancia, Cola<NodoGrafo<TipoDeDatos>> cola) {
        for (int i=0; i != nodos.getNumeroElementos(); i++) {
            distancia.put(nodos.getElemento(i).getData(), Double.MAX_VALUE);
        }
        distancia.put(nodo, 0.0);
        cola.add(nodo);
    }

    private void dijkstra_calcular (Mapa<NodoGrafo<TipoDeDatos>, Double> distancia, Cola<NodoGrafo<TipoDeDatos>> cola, Mapa<NodoGrafo<TipoDeDatos>, NodoGrafo<TipoDeDatos>> vertices) {
        while (!cola.isVacia()) {
            NodoGrafo<TipoDeDatos> nodoActual = cola.poll();
            for (int i=0; i != nodoActual.getListaSalida().getNumeroElementos(); i++) {
                NodoGrafo<TipoDeDatos> nodo2 = nodoActual.getListaSalida().getElemento(i).getData().getVertice(nodoActual);
                double calculoDistancia = distancia.get(nodoActual) + nodoActual.getListaSalida().getElemento(i).getData().getPeso();
                if (calculoDistancia < distancia.get(nodo2)) {
                    distancia.put(nodo2, calculoDistancia);
                    vertices.put(nodo2, nodoActual);
                    cola.add(nodo2);
                }
            }
        }
    }

    private Mapa<NodoGrafo<TipoDeDatos>, Camino<TipoDeDatos>> dijkstra_procesarResultados (Mapa<NodoGrafo<TipoDeDatos>, Double> distancia, Mapa<NodoGrafo<TipoDeDatos>, NodoGrafo<TipoDeDatos>> vertices) {
        Mapa<NodoGrafo<TipoDeDatos>, Camino<TipoDeDatos>> camino = new Mapa<>();
        for (int i=0; i != vertices.SetClave().getNumeroElementos(); i++) {
            ListaDoblementeEnlazada<NodoGrafo<TipoDeDatos>> calculoCamino = new ListaDoblementeEnlazada<>();
            NodoGrafo<TipoDeDatos> vertice = vertices.SetClave().getElemento(i).getData();
            NodoGrafo<TipoDeDatos> vert = vertice;
            while (vert != null) {
                calculoCamino.insert(vert, 0);
                vert = vertices.get(vert);
            }
            camino.put(vertice, new Camino<>(calculoCamino, distancia.get(vertice)));
        }
        return camino;
    }

    public String listaToString (ListaDoblementeEnlazada<NodoGrafo<TipoDeDatos>> lista) {
        StringBuilder cadena = new StringBuilder("[");
        if (lista.getNumeroElementos() != 0) {
            cadena.append(lista.getPrimero().getData().getDato());
            for (int i = 1; i != lista.getNumeroElementos(); i++) {
                cadena.append(", ").append(lista.getElemento(i).getData().getDato().toString());
            }
        }
        cadena.append("]");
        return cadena.toString();
    }

}