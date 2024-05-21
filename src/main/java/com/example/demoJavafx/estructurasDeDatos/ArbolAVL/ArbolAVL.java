package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.excepciones.BalanceExcepcion;
import com.example.demoJavafx.excepciones.DuplicateElement;
import com.example.demoJavafx.excepciones.NonexistentElement;
import com.example.demoJavafx.excepciones.VoidLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArbolAVL<TipoDeDatos> extends BST<TipoDeDatos> {
    private NodoAVL<TipoDeDatos> raiz;
    private static final Logger log = LogManager.getLogger();

    public ArbolAVL() {
        super();
    }

    public ArbolAVL(TipoDeDatos raiz) {
        this.raiz = new NodoAVL<>(raiz);
    }

    public ArbolAVL(NodoAVL<TipoDeDatos> nodo) {
        this.raiz = nodo;
    }

    @Override
    public NodoAVL<TipoDeDatos> getRaiz() {
        return raiz;
    }

    @Override
    public ArbolAVL<TipoDeDatos> getSubArbolIzq() {
        if (this.raiz.getIzquierda() == null) {
            return null;
        } else {
            return new ArbolAVL<>(this.raiz.getIzquierda());
        }
    }

    @Override
    public ArbolAVL<TipoDeDatos> getSubArbolDcha() {
        if (this.raiz.getDerecha() == null) {
            return null;
        } else {
            return new ArbolAVL<>(this.raiz.getDerecha());
        }
    }

    public int getGradoN(NodoAVL<TipoDeDatos> nodo) {
        return super.getGradoN(nodo);
    }

    @Override
    public int getAltura() {
        return raiz.getAltura();
    }

    public int getAlturaN(NodoAVL<TipoDeDatos> nodo) {
        return nodo.getAltura();
    }

    private void actAltura(NodoAVL<TipoDeDatos> nodo) {
        if (nodo != null) {
            int alturaIzq;
            int alturaDcha;
            if (nodo.getIzquierda() != null) {
                alturaIzq = nodo.getIzquierda().getAltura();
            } else {
                alturaIzq = -1;
            }
            if (nodo.getDerecha() != null) {
                alturaDcha = nodo.getDerecha().getAltura();
            } else {
                alturaDcha = -1;
            }
            nodo.setAltura(Math.max(alturaIzq, alturaDcha) + 1);
        }
    }

    @Override
    public ListaDoblementeEnlazada<TipoDeDatos> preOrden() {
        ListaDoblementeEnlazada<TipoDeDatos> lista = new ListaDoblementeEnlazada<>();
        lista = getPreOrden(this.raiz, lista);
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> getPreOrden(NodoAVL<TipoDeDatos> nodoAVL, ListaDoblementeEnlazada<TipoDeDatos> lista) {
        if ((nodoAVL != null)) {
            lista.add(nodoAVL.getDato());
            getPreOrden(nodoAVL.getIzquierda(), lista);
            getPreOrden(nodoAVL.getDerecha(), lista);
        }
        return lista;
    }

    @Override
    public ListaDoblementeEnlazada<TipoDeDatos> ordenCentral() {
        ListaDoblementeEnlazada<TipoDeDatos> lista = new ListaDoblementeEnlazada<>();
        lista = getOrdenCentral(this.raiz, lista);
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> getOrdenCentral(NodoAVL<TipoDeDatos> nodoAVL, ListaDoblementeEnlazada<TipoDeDatos> lista) {
        if ((nodoAVL != null)) {
            getOrdenCentral(nodoAVL.getIzquierda(), lista);
            lista.add(nodoAVL.getDato());
            getOrdenCentral(nodoAVL.getDerecha(), lista);
        }
        return lista;
    }

    @Override
    public ListaDoblementeEnlazada<TipoDeDatos> postOrden() {
        ListaDoblementeEnlazada<TipoDeDatos> lista = new ListaDoblementeEnlazada<>();
        lista = getPostOrden(this.raiz, lista);
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> getPostOrden(NodoAVL<TipoDeDatos> nodoAVL, ListaDoblementeEnlazada<TipoDeDatos> lista) {
        if ((nodoAVL != null)) {
            getPostOrden(nodoAVL.getIzquierda(), lista);
            getPostOrden(nodoAVL.getDerecha(), lista);
            lista.add(nodoAVL.getDato());
        }
        return lista;
    }

    private NodoAVL<TipoDeDatos> rotar_s(NodoAVL<TipoDeDatos> raiz, Boolean izq) {
        if (izq) {
            NodoAVL<TipoDeDatos> nodoDcha = new NodoAVL<>(raiz.getDato());
            nodoDcha.setIzquierda(raiz.getIzquierda().getDerecha());
            nodoDcha.setDerecha(raiz.getDerecha());
            raiz = raiz.getIzquierda();
            raiz.setDerecha(nodoDcha);
        } else {
            NodoAVL<TipoDeDatos> nodoIzq = new NodoAVL<>(raiz.getDato());
            nodoIzq.setDerecha(raiz.getDerecha().getIzquierda());
            nodoIzq.setIzquierda(raiz.getIzquierda());
            raiz = raiz.getDerecha();
            raiz.setIzquierda(nodoIzq);
        }
        actAltura(raiz);
        actAltura(raiz.getIzquierda());
        return raiz;
    }

    private NodoAVL<TipoDeDatos> rotar_d(NodoAVL<TipoDeDatos> raiz, Boolean izq) {
        if (izq) {
            raiz.setIzquierda(rotar_s(raiz.getIzquierda(), false));
            raiz = rotar_s(raiz, true);
        } else {
            raiz.setDerecha(rotar_s(raiz.getDerecha(), true));
            raiz = rotar_s(raiz, false);
        }
        return raiz;
    }

    public void balanceo() {
        try {
            if (getAltura() > -1) {
                int alturaIzq;
                int alturaDcha;
                if (getSubArbolIzq() != null) {
                    alturaIzq = getSubArbolIzq().getAltura();
                } else {
                    alturaIzq = -1;
                }
                if (getSubArbolDcha() != null) {
                    alturaDcha = getSubArbolDcha().getAltura();
                } else {
                    alturaDcha = -1;
                }
                if (alturaIzq - alturaDcha == 2) {
                    if (getSubArbolIzq().getSubArbolDcha() == null) {
                        raiz = rotar_s(raiz, true);
                    } else if (getSubArbolIzq().getSubArbolIzq() == null) {
                        raiz = rotar_d(raiz, true);
                    } else if (getSubArbolIzq().getSubArbolIzq().getAltura() >= getSubArbolIzq().getSubArbolDcha().getAltura()) {
                        raiz = rotar_s(raiz, true);
                    } else {
                        raiz = rotar_d(raiz, true);
                    }
                } else if (alturaDcha - alturaIzq == 2) {
                    if (getSubArbolDcha().getSubArbolIzq() == null) {
                        raiz = rotar_s(raiz, false);
                    } else if (getSubArbolDcha().getSubArbolDcha() == null) {
                        raiz = rotar_d(raiz, true);
                    } else if (getSubArbolDcha().getSubArbolDcha().getAltura() >= getSubArbolDcha().getSubArbolIzq().getAltura()) {
                        raiz = rotar_s(raiz, false);
                    } else {
                        raiz = rotar_d(raiz, false);
                    }
                } else if (Math.abs(alturaDcha - alturaIzq) > 2) throw new BalanceExcepcion();
            }
        } catch (BalanceExcepcion e) {
            log.error("No se ha podido equilibrar el balance ya que es más de dos de altura");
        }
    }

    @Override
    public void add(TipoDeDatos dato) {
        NodoAVL<TipoDeDatos> nuevoNodoAVL = new NodoAVL<>(dato);
        add(nuevoNodoAVL);
    }

    public void add(NodoAVL<TipoDeDatos> nuevoNodoAVL) {
        if (getAltura() == -1) {
            raiz = nuevoNodoAVL;
        } else {
            addAux(raiz, nuevoNodoAVL);
        }
    }

    public void addAux(NodoAVL<TipoDeDatos> raiz, NodoAVL<TipoDeDatos> dato) {
        Comparable r = (Comparable) raiz.getDato();
        Comparable d = (Comparable) dato.getDato();
        if (d.compareTo(r) < 0) {
            if (raiz.getIzquierda() == null) {
                raiz.setIzquierda(dato);
            } else {
                addAux(raiz.getIzquierda(), dato);
            }
        } else {
            if (raiz.getDerecha() == null) {
                raiz.setDerecha(dato);
            } else {
                addAux(raiz.getDerecha(), dato);
            }
        }
        actAltura(raiz);
        this.balanceo();
    }

    public void del(TipoDeDatos dato) {
        raiz = delAux1(raiz, dato);
    }

    private NodoAVL<TipoDeDatos> delAux1(NodoAVL<TipoDeDatos> raiz, TipoDeDatos dato) {
        Comparable r = (Comparable) raiz.getDato();
        Comparable d = (Comparable) dato;
        if (d.compareTo(r) < 0) {
            raiz.setIzquierda(delAux1(raiz.getIzquierda(), dato));
        } else if (d.compareTo(r) > 0) {
            raiz.setDerecha(delAux1(raiz.getDerecha(), dato));
        } else {
            if (raiz.getDerecha() == null && raiz.getIzquierda() == null) raiz = null;
            else if (raiz.getIzquierda() == null) raiz = raiz.getDerecha();
            else if (raiz.getDerecha() == null) raiz = raiz.getIzquierda();
            else {
                raiz = delAux2(raiz.getDerecha());
            }
        }
        actAltura(raiz);
        this.balanceo();
        return raiz;
    }

    private NodoAVL<TipoDeDatos> delAux2(NodoAVL<TipoDeDatos> raiz) {
        if (raiz.getIzquierda() != null) {
            NodoAVL<TipoDeDatos> nodo = delAux2(raiz.getIzquierda());
            ArbolAVL<TipoDeDatos> arbol = new ArbolAVL<>(raiz);
            arbol.balanceo();
            actAltura(raiz);
            return nodo;
        } else {
            NodoAVL<TipoDeDatos> nodo = raiz;
            raiz = raiz.getDerecha();
            ArbolAVL<TipoDeDatos> arbol = new ArbolAVL<>(raiz);
            arbol.balanceo();
            actAltura(raiz);
            return nodo;
        }
    }

    public ListaDoblementeEnlazada<NodoAVL<TipoDeDatos>> getcamino(TipoDeDatos dato) {
        try {
            ListaDoblementeEnlazada<NodoAVL<TipoDeDatos>> camino = new ListaDoblementeEnlazada<>();
            ListaDoblementeEnlazada<NodoAVL<TipoDeDatos>> caminoNuevo = getCaminoPrinc(this.raiz, dato, camino);
            if (caminoNuevo.isVacia()) throw new NonexistentElement();
            else return caminoNuevo;
        } catch (NonexistentElement e) {
            log.warn("Se esta intentando encontrar un camino y alguno de los nodos no existe");
            return null;
        }
    }

    private ListaDoblementeEnlazada<NodoAVL<TipoDeDatos>> getCaminoPrinc(NodoAVL<TipoDeDatos> raiz, TipoDeDatos dato, ListaDoblementeEnlazada<NodoAVL<TipoDeDatos>> camino) {
        if (raiz == null) return camino;
        getCaminoPrinc(raiz.getIzquierda(), dato, camino);
        if (camino.isVacia()) getCaminoPrinc(raiz.getDerecha(), dato, camino);
        if (raiz.getDato() == dato || !camino.isVacia()) camino.insert(raiz, 0);
        return camino;
    }
}
