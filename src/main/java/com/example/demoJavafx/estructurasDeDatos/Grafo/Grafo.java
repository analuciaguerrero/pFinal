package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.excepciones.CaminoNulo;
import com.example.demoJavafx.excepciones.NonexistentElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Grafo<TipoDelDato> {
    private ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> nodos = new ListaDoblementeEnlazada<>();

    private ListaDoblementeEnlazada<Arista<TipoDelDato>> aristas = new ListaDoblementeEnlazada<>();
    private boolean isDirigido;
    private static final Logger log = LogManager.getLogger(Grafo.class);
    public Grafo() {}

    public Grafo(boolean isDirigido) {
        this.isDirigido = isDirigido;
    }
    public Grafo(ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> nodos, ListaDoblementeEnlazada<Arista<TipoDelDato>> aristas){
        this.nodos= nodos;
        this.aristas= aristas;
    }
    public ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> getNodos() {
        return nodos;
    }

    public void setNodos(ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> nodos) {
        this.nodos = nodos;
    }

    public ListaDoblementeEnlazada<Arista<TipoDelDato>> getAristas() {
        return aristas;
    }

    public void setAristas(ListaDoblementeEnlazada<Arista<TipoDelDato>> aristas) {
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
    public void addNodo (NodoGrafo<TipoDelDato> nodo) {
        nodos.add(nodo);
    }
    public void addNodo (TipoDelDato dato) {
        NodoGrafo<TipoDelDato> nodo = new NodoGrafo<>(dato);
        nodos.add(nodo);
    }

    public void addArista(double peso, TipoDelDato datoIni, TipoDelDato datoFin, String anotacion) {
        NodoGrafo<TipoDelDato> nodoIni = getNodoGrafo(datoIni);
        NodoGrafo<TipoDelDato> nodoFin = getNodoGrafo(datoFin);
        if (nodoIni != null && nodoFin != null) { // Verifica si los nodos son distintos de nulos
            Arista<TipoDelDato> arista = new Arista<>(peso, nodoIni, nodoFin, anotacion, this.isDirigido);
            if (isDirigido) {
                nodoFin.getListaEntrada().add(arista);
                nodoIni.getListaSalida().add(arista);
            } else {
                nodoFin.getListaSalida().add(arista);
                nodoIni.getListaSalida().add(arista);
            }
            aristas.add(arista);
        } else {
            // Manejar el caso en que uno o ambos nodos son nulos
            log.error("Uno o ambos nodos son nulos. No se puede agregar la arista.");
        }
    }
    public void addArista (double peso, NodoGrafo<TipoDelDato> nodoIni, NodoGrafo<TipoDelDato> nodoFin) {
        Arista<TipoDelDato> arista = new Arista<>(peso, nodoIni, nodoFin, this.isDirigido);
        if (isDirigido) {
            nodoFin.getListaEntrada().add(arista);
            nodoIni.getListaSalida().add(arista);
        } else {
            nodoFin.getListaSalida().add(arista);
            nodoIni.getListaSalida().add(arista);
        }
        aristas.add(arista);
    }

    public void addArista (double peso, TipoDelDato datoIni, TipoDelDato datoFin) {
        NodoGrafo<TipoDelDato> nodoIni = getNodoGrafo(datoIni);
        NodoGrafo<TipoDelDato> nodoFin = getNodoGrafo(datoFin);

        addArista(peso, nodoIni, nodoFin);
    }

    public void delNodo (TipoDelDato dato) {
        NodoGrafo<TipoDelDato> nodo = getNodoGrafo(dato);
        for (int i=0; i <= nodos.getNumeroElementos(); i++) {
            if (nodos.getElemento(i).getData().getDato() == nodo.getDato()) {
                nodos.del(i);
            }
        }
    }

    public void delArista (String anotacion) {
        Arista<TipoDelDato> arista = getArista(anotacion);
        for (int i = 0; i <= aristas.getNumeroElementos(); i++) {
            if (aristas.getElemento(i).getData().getAnotacion().equals(arista.getAnotacion())) {
                aristas.del(i);
            }
        }
    }
    public NodoGrafo<TipoDelDato> getNodoGrafo(TipoDelDato dato) {
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

    public Arista<TipoDelDato> getArista (String anotacion) {
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
    public Camino<TipoDelDato> getCaminoMinimo (NodoGrafo<TipoDelDato> nodoIni, NodoGrafo<TipoDelDato> nodoFin) {
        try {
            Mapa<NodoGrafo<TipoDelDato>, Camino<TipoDelDato>> grafoMapa = dijkstra(nodoIni);
            if (grafoMapa.get(nodoFin) == null) throw new CaminoNulo();
            return grafoMapa.get(nodoFin);
        } catch (CaminoNulo e) {
            log.warn("El nodo " + nodoFin + " (" + nodoFin.getDato() + ")" + " no existe o es inaccesible desde " + nodoIni + " (" + nodoIni.getDato() + ")");
            return null;
        }
    }

    public Mapa<NodoGrafo<TipoDelDato>, Camino<TipoDelDato>> dijkstra (NodoGrafo<TipoDelDato> nodoIni) {
        Mapa<NodoGrafo<TipoDelDato>, Double> distancia = new Mapa<>();
        Cola<NodoGrafo<TipoDelDato>> cola = new Cola<>();
        Mapa<NodoGrafo<TipoDelDato>, NodoGrafo<TipoDelDato>> vertices = new Mapa<>();
        this.dijkstra_inicial(nodoIni,distancia,cola);
        this.dijkstra_calcular(distancia,cola,vertices);
        return this.dijkstra_procesarResultados(distancia,vertices);
    }

    private void dijkstra_inicial (NodoGrafo<TipoDelDato> nodo, Mapa<NodoGrafo<TipoDelDato>, Double> distancia, Cola<NodoGrafo<TipoDelDato>> cola) {
        for (int i=0; i != nodos.getNumeroElementos(); i++) {
            distancia.put(nodos.getElemento(i).getData(), Double.MAX_VALUE);
        }
        distancia.put(nodo, 0.0);
        cola.add(nodo);
    }

    private void dijkstra_calcular (Mapa<NodoGrafo<TipoDelDato>, Double> distancia, Cola<NodoGrafo<TipoDelDato>> cola, Mapa<NodoGrafo<TipoDelDato>, NodoGrafo<TipoDelDato>> vertices) {
        while (!cola.isVacia()) {
            NodoGrafo<TipoDelDato> nodoActual = cola.pull();
            for (int i=0; i != nodoActual.getListaSalida().getNumeroElementos(); i++) {
                NodoGrafo<TipoDelDato> nodo2 = nodoActual.getListaSalida().getElemento(i).getData().getVertice(nodoActual);
                double calculoDistancia = distancia.get(nodoActual) + nodoActual.getListaSalida().getElemento(i).getData().getPeso();

                if (calculoDistancia < distancia.get(nodo2)) {
                    distancia.put(nodo2, calculoDistancia);
                    vertices.put(nodo2, nodoActual);
                    cola.add(nodo2);
                }
            }
        }
    }

    private Mapa<NodoGrafo<TipoDelDato>, Camino<TipoDelDato>> dijkstra_procesarResultados (Mapa<NodoGrafo<TipoDelDato>, Double> distancia, Mapa<NodoGrafo<TipoDelDato>, NodoGrafo<TipoDelDato>> vertices) {
        Mapa<NodoGrafo<TipoDelDato>, Camino<TipoDelDato>> camino = new Mapa<>();

        for (int i=0; i != vertices.SetClave().getNumeroElementos(); i++) {
            ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> calculoCamino = new ListaDoblementeEnlazada<>();
            NodoGrafo<TipoDelDato> vertice = vertices.SetClave().getElemento(i).getData();
            NodoGrafo<TipoDelDato> vert = vertice;
            while (vert != null) {
                calculoCamino.insert(vert, 0);
                vert = vertices.get(vert);
            }
            camino.put(vertice, new Camino<>(calculoCamino, distancia.get(vertice)));
        }
        return camino;
    }

    public String listaToString (ListaDoblementeEnlazada<NodoGrafo<TipoDelDato>> lista) {
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