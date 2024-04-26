package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple2;
import com.example.demojavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demojavafx.excepciones.ExistentVertix;
import com.example.demojavafx.excepciones.NonValidLink;
import com.example.demojavafx.excepciones.NonexistentElement;

import java.util.Objects;

public class Grafo {
    private ListaSimple2<Vertice> vertices = new ListaSimple2<>();

    private ListaSimple2<Arista> aristas = new ListaSimple2<>();

    // Constructores

    public Grafo() {}

    public Grafo(Vertice v1) {
        vertices.add(v1);
    }

    // Métodos para la modificacion de los elementos del grafo

    public void addVertice(Vertice v) throws ExistentVertix {
        boolean contenido = false;
        Integer contador = 0;
        while (contador < vertices.getNumeroElementos()) {
            if (Objects.equals(vertices.getElemento(contador).getData().getID(), v.getID())) {
                contenido = true;
            }
            contador++;
        }
        if (!contenido) {
            this.vertices.add(v);
        } else {
            throw (new ExistentVertix("El ID del vertice ya existe en el grafo"));
        }
    }

    public void addArista(Arista a1) throws NonValidLink {
        if (Objects.equals(a1.getVerticeFin().getID(), a1.getVerticeIni().getID())) {
            throw (new NonValidLink("ERROR. Una arista no puede unir un vértice consigo mismo."));
        } else if (validarArista(a1)) {
            this.aristas.add(a1);
            a1.getVerticeIni().addAristaVHijo(a1);
            a1.getVerticeFin().addAristaVAntecesor(a1);
        } else {
            throw (new NonValidLink("ERROR. Alguno de los vértices que une la arista no se encuentra en el grafo."));
        }
    }

    private boolean validarArista(Arista a1) throws NonValidLink {
        Vertice vertice1 = a1.getVerticeIni();
        Vertice vertice2 = a1.getVerticeFin();
        boolean isVertice1 = false;
        boolean isVertice2 = false;
        Integer contador = 0;
        while (contador < vertices.getNumeroElementos()) {
            if (vertices.getElemento(contador).getData().getID() == vertice1.getID()) {
                isVertice1 = true;
            }
            if (vertices.getElemento(contador).getData().getID() == vertice2.getID()) {
                isVertice2 = true;
            }
            contador++;
        }
        if (isVertice1 && isVertice2) {
            Integer contador2 = 0;
            while (contador2 < aristas.getNumeroElementos()) {
                if ((Objects.equals(aristas.getElemento(contador2).getData().getVerticeIni().getID(), vertice1.getID())) && (Objects.equals(aristas.getElemento(contador2).getData().getVerticeFin().getID(), vertice2.getID()))) {
                    throw (new NonValidLink("ERROR. Ya existe una arista uniendo estos dos grafos"));
                }
                contador2++;
            }
            return true;
        } else {
            throw (new NonValidLink("ERROR. Alguno de los vértices que une la arista no se encuentra en el grafo."));
        }
    }

    public void delArista(Vertice vIni, Vertice vFin) throws NonexistentElement {
        Arista aristaBuscada = null;
        int posicion = 0;
        while ((posicion < this.aristas.getNumeroElementos()) && (aristaBuscada == null)) {
            if ((Objects.equals(this.aristas.getElemento(posicion).getData().getVerticeIni().getID(), vIni.getID())) && (Objects.equals(this.aristas.getElemento(posicion).getData().getVerticeFin().getID(), vFin.getID()))) {
                aristaBuscada = this.aristas.getElemento(posicion).getData();
            } else {
                posicion++;
            }
        }
        if (aristaBuscada == null) {
            throw (new NonexistentElement("ERROR. La arista que desea eliminar no se encuentrá en el grafo"));
        } else {

            // Modificamos las listas de vertices antecesores y vertices hijos de los vertices de la la arista

            int contador = 0;
            while (contador < vIni.getAristasVHijos().getNumeroElementos()) {
                Arista arista = (Arista) vIni.getAristasVHijos().getElemento(contador).getData();
                if (Objects.equals(arista.getVerticeFin().getID(), vFin.getID())) {
                    vIni.getAristasVHijos().del(contador);
                    contador = vIni.getAristasVHijos().getNumeroElementos();
                }
                contador++;
            }
            int contador2 = 0;
            while (contador2 < vFin.getAristasVAntecesores().getNumeroElementos()) {
                Arista arista = (Arista) vFin.getAristasVAntecesores().getElemento(contador2).getData();
                if (Objects.equals(arista.getVerticeIni().getID(), vIni.getID())) {
                    vFin.getAristasVAntecesores().del(contador2);
                    contador2 = vFin.getAristasVAntecesores().getNumeroElementos();
                }
                contador2++;
            }

            // Eliminamos la arista

            this.aristas.del(posicion);
        }
    }

    public void delVertice(Vertice v) throws NonexistentElement {
        int posicion = 0;
        boolean isVertice = false;
        while ((!isVertice) && (posicion < this.vertices.getNumeroElementos())) {
            if (Objects.equals(this.vertices.getElemento(posicion).getData().getID(), v.getID())) {
                isVertice = true;
            } else {
                posicion++;
            }
        }
        if (!isVertice) {
            throw (new NonexistentElement("ERROR. El vertice que desea eliminar no se encuentra en el grafo"));
        } else {

            // Eliminamos sus aristas

            while (!v.getAristasVAntecesores().isVacia()) {
                Arista arista = (Arista) v.getAristasVAntecesores().getPrimero().getData();
                this.delArista(v, arista.getVerticeFin());
            }
            while (!v.getAristasVHijos().isVacia()) {
                Arista arista = (Arista) v.getAristasVHijos().getPrimero().getData();
                this.delArista(arista.getVerticeIni(), v);
            }

            // Eliminamos el vertice

            this.vertices.del(posicion);

        }
    }

    // Metodos para sacar por la terminal datos legibles

    public String getStringVertices() {
        int contador = 0;
        String lista = "[";
        while (contador < this.vertices.getNumeroElementos()) {
            if (this.vertices.getElemento(contador + 1) != null) {
                lista += this.vertices.getElemento(contador).getData().getDato() + ", ";
            } else {
                lista += this.vertices.getElemento(contador).getData().getDato() + "].";
            }
            contador++;
        }
        return lista;
    }

    public String getStringAristas() {
        int contador = 0;
        String lista = "[";
        while (contador < this.aristas.getNumeroElementos()) {
            if (this.aristas.getElemento(contador + 1) != null) {
                lista += "{" + this.aristas.getElemento(contador).getData().getVerticeIni().getDato() + ", " + this.aristas.getElemento(contador).getData().getVerticeFin().getDato() + "}, ";
            } else {
                lista += "{" + this.aristas.getElemento(contador).getData().getVerticeIni().getDato() + ", " + this.aristas.getElemento(contador).getData().getVerticeFin().getDato() + "}].";
            }
            contador++;
        }
        return lista;
    }

    public String printCodigoGrafo() {
        char com = '"';
        String codigo = "digraph regexp {\nfontname=" + com + "Helvetica,Arial,sans-serif" + com + "\nnode [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]\nedge [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]";
        Integer contadorV = 0;
        while (contadorV < vertices.getNumeroElementos()) {
            codigo += "\nn" + vertices.getElemento(contadorV).getData().getID() + " [label=" + com + vertices.getElemento(contadorV).getData().getID() + com + "];";
            contadorV++;
        }
        Integer contadorA = 0;
        while (contadorA < aristas.getNumeroElementos()) {
            codigo += "\nn" + aristas.getElemento(contadorA).getData().getVerticeIni().getID() + " -> n" + aristas.getElemento(contadorA).getData().getVerticeFin().getID() + " [label=" + com + aristas.getElemento(contadorA).getData().getPeso() + com + "];";
            contadorA++;
        }
        codigo += "\n}";
        return codigo;
    }

    public Mapa<Vertice,Camino> dijkstra(Vertice origen) {
        Mapa<Vertice, Double> distancias = new Mapa<>();
        Cola<Vertice> colaPendientes = new Cola<>();
        Mapa<Vertice, Vertice> verticesAnteriores = new Mapa<>();

        this.dijkstra_inicializar(origen,distancias,colaPendientes,verticesAnteriores);
        this.dijkstra_ejecucion(distancias,colaPendientes,verticesAnteriores);
        return this.dijkstra_resultados(distancias,verticesAnteriores);

    }

    protected void dijkstra_inicializar(Vertice origen, Mapa<Vertice, Double> distancias, Cola<Vertice> colaPendientes, Mapa<Vertice, Vertice> verticesAnteriores) {
        int contador = 0;
        while (contador < this.vertices.getNumeroElementos()) {
            distancias.add(this.vertices.getElemento(contador).getData(), Double.MAX_VALUE);
            contador++;
        }
        distancias.add(origen, 0.0);
        colaPendientes.push(origen);
    }

    protected void dijkstra_ejecucion(Mapa<Vertice, Double> distancias, Cola<Vertice> colaPendientes, Mapa<Vertice, Vertice> verticesAnteriores) {
        while (!colaPendientes.esVacia()) {
            Vertice verticeActual = colaPendientes.pull();

            Integer contador = 0;
            while (contador < verticeActual.getAristasVHijos().getNumeroElementos()) {
                Arista aristaDestino = (Arista) verticeActual.getAristasVHijos().getElemento(contador).getData();
                Vertice verticeDestino = aristaDestino.getVerticeFin();
                double nuevaDistancia = distancias.get(verticeActual) + aristaDestino.getPeso();

                if (nuevaDistancia < distancias.get(verticeDestino)) {
                    distancias.add(verticeDestino, nuevaDistancia);
                    verticesAnteriores.add(verticeDestino, verticeActual);
                    colaPendientes.push(verticeDestino);
                }
                contador++;
            }
        }
    }

    protected Mapa<Vertice, Camino> dijkstra_resultados(Mapa<Vertice, Double> distancias, Mapa<Vertice, Vertice> verticesAnteriores) {
        Mapa<Vertice, Camino> caminos = new Mapa<>();

        Integer contador = 0;
        while (contador < verticesAnteriores.getIndices().getNumeroElementos()) {
            Vertice verticeDestino = verticesAnteriores.getIndices().getElemento(contador).getData();
            ListaSimple2<Vertice> caminoCalculado = new ListaSimple2<>();
            Vertice v = verticeDestino;
            while (v != null) {
                caminoCalculado.add(v);
                v=verticesAnteriores.get(v);
            }
            caminoCalculado=caminoCalculado.voltear();
            caminos.add(verticeDestino,new Camino(caminoCalculado, distancias.get(verticeDestino)));
            contador++;
        }
        return caminos;
    }

    public ListaSimple2<Vertice> getVertices() {
        return vertices;
    }

    public ListaSimple2<Arista> getAristas() {
        return aristas;
    }

    public void setVertices(ListaSimple2<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void setAristas(ListaSimple2<Arista> aristas) {
        this.aristas = aristas;
    }
}
