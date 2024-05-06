package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demojavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demojavafx.excepciones.ExistentVertix;
import com.example.demojavafx.excepciones.NonValidLink;
import com.example.demojavafx.excepciones.NonexistentElement;

import java.util.Objects;

public class Grafo {
    private ListaSimple<Vertice> vertices = new ListaSimple<>();

    private ListaSimple<Arista> aristas = new ListaSimple<>();

    public Grafo() {}

    public Grafo(Vertice v1) {
        vertices.add(v1);
    }

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

    protected boolean validarArista(Arista a1) throws NonValidLink {
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
            this.aristas.del(posicion);
        }
    }

    public void delVertice(Vertice v) throws NonexistentElement {
        int posicion = 0;
        boolean encontrado = false;
        while (!encontrado && posicion < this.vertices.getNumeroElementos()) {
            if (Objects.equals(this.vertices.getElemento(posicion).getData().getID(), v.getID())) {
                encontrado = true;
            } else {
                posicion++;
            }
        }
        if (!encontrado) {
            throw (new NonexistentElement("ERROR. El vertice que desea eliminar no se encuentra en el grafo"));
        } else {
            while (!v.getAristasVAntecesores().isVacia()) {
                Arista arista = (Arista) v.getAristasVAntecesores().getPrimero().getData();
                this.delArista(arista.getVerticeIni(),v);
            }
            while (!v.getAristasVHijos().isVacia()) {
                Arista arista = (Arista) v.getAristasVHijos().getPrimero().getData();
                this.delArista(v, arista.getVerticeFin());
            }
            this.vertices.del(posicion);
        }
    }

    public String getStringVertices() {
        int contador = 0;
        String lista = "[";
        while (contador < this.vertices.getNumeroElementos()) {
            if (this.vertices.getElemento(contador + 1) != null) {
                lista += this.vertices.getElemento(contador).getData().getDato() + ", ";
            } else {
                lista += this.vertices.getElemento(contador).getData().getDato() + "]";
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

    public Camino getCaminoMinimo(Vertice origen, Vertice fin) throws NonexistentElement {
        boolean isVerticeIni = false;
        boolean isVerticeFin = false;
        ListaSimple<Vertice> vertices = this.vertices.copiaLista();
        while ((!vertices.isVacia()) || ((!isVerticeFin) && (!isVerticeIni))) {
            if (vertices.getPrimero().getData().getID() == origen.getID()) {
                isVerticeIni = true;
            }
            if (vertices.getPrimero().getData().getID() == fin.getID()) {
                isVerticeFin = true;
            }
            vertices.del(0);
        }
        if (isVerticeFin && isVerticeIni) {
            Mapa<Vertice, Camino> caminos = this.dijkstra(origen);
            if (caminos.isVacio()) {
                throw (new NonexistentElement("ERROR. El vertice inicial no está conectado con ningún otro vertice"));
            } else {
                Camino caminoCompleto = caminos.get(fin);
                if (caminoCompleto == null) {
                    throw (new NonexistentElement("ERROR. No existe ningun camino entre los vertices intoducidos"));
                } else {
                    return caminoCompleto;
                }
            }
        } else {
            throw (new NonexistentElement("ERROR. Alguno de los vertices introducidos no se encuentra en el grafo"));
        }
    }


    private Mapa<Vertice,Camino> dijkstra(Vertice origen) {
        Mapa<Vertice, Double> distancias = new Mapa<>();
        Cola<Vertice> colaPendientes = new Cola<>();
        Mapa<Vertice, Vertice> verticesAnteriores = new Mapa<>();

        dijkstra_inicializar(origen,distancias,colaPendientes);
        dijkstra_ejecucion(distancias,colaPendientes,verticesAnteriores);
        return dijkstra_resultados(distancias,verticesAnteriores);

    }

    private void dijkstra_inicializar(Vertice origen, Mapa<Vertice, Double> distancias, Cola<Vertice> colaPendientes) {
        int contador = 0;
        while (contador < this.vertices.getNumeroElementos()) {
            distancias.add(this.vertices.getElemento(contador).getData(), Double.MAX_VALUE);
            contador++;
        }
        distancias.add(origen, 0.0);
        colaPendientes.push(origen);
    }

    private void dijkstra_ejecucion(Mapa<Vertice, Double> distancias, Cola<Vertice> colaPendientes, Mapa<Vertice, Vertice> verticesAnteriores) {
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

    private Mapa<Vertice, Camino> dijkstra_resultados(Mapa<Vertice, Double> distancias, Mapa<Vertice, Vertice> verticesAnteriores) {
        Mapa<Vertice, Camino> caminos = new Mapa<>();
        Integer contador = 0;
        while (contador < verticesAnteriores.getIndices().getNumeroElementos()) {
            Vertice verticeDestino = verticesAnteriores.getIndices().getElemento(contador).getData();
            ListaSimple<Vertice> caminoCalculado = new ListaSimple<>();
            Vertice v = verticeDestino;
            while (v != null) {
                caminoCalculado.add(v);
                v=verticesAnteriores.get(v);
            }
            caminoCalculado=caminoCalculado.voltear();
            Camino camino = new Camino(caminoCalculado,distancias.get(verticeDestino));
            caminos.add(verticeDestino,camino);
            contador++;
        }
        return caminos;
    }

    public ListaSimple<Vertice> getVertices() {
        return vertices;
    }

    public ListaSimple<Arista> getAristas() {
        return aristas;
    }

    public void setVertices(ListaSimple<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void setAristas(ListaSimple<Arista> aristas) {
        this.aristas = aristas;
    }
}