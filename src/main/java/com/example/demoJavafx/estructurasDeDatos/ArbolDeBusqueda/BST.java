package com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.excepciones.NonexistentElement;

public class BST<TipoDeDatos>{
    private Nodo<TipoDeDatos> raiz;
    public BST(Nodo<TipoDeDatos> raiz, Nodo<TipoDeDatos> derecha, Nodo<TipoDeDatos> izquierda) {
        this.raiz = raiz;
        this.raiz.setDerecha(derecha);
        this.raiz.setIzquierda(izquierda);
    }

    public BST(Nodo<TipoDeDatos> raiz) {
        this.raiz = raiz;
    }

    public BST() {
    }
    public BST(TipoDeDatos dato) {
        this.raiz = new Nodo<>(dato);
    }
    public void add (TipoDeDatos dato) {
        Nodo<TipoDeDatos> nodo = new Nodo<>(dato);
        add(nodo);
    }
    public void add(Nodo<TipoDeDatos> nodo) {
        if (getAltura() == -1) {
            raiz = nodo;
        } else {
            addAux(raiz, nodo);
        }
    }

    private void addAux(Nodo<TipoDeDatos> raiz, Nodo<TipoDeDatos> dato) {
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
    }

    public int getGradoN(Nodo<TipoDeDatos> n) {
        int izquierdo = 0;
        int derecho = 0;
        int nodo = 0;
        if (n.getIzquierda() != null && n.getDerecha() != null) {
            nodo = 2;
            izquierdo = getGradoN(n.getIzquierda());
            derecho = getGradoN(n.getDerecha());
        } else if (n.getIzquierda() != null && n.getDerecha() == null) {
            nodo = 1;
            izquierdo = getGradoN(n.getIzquierda());
        } else if (n.getIzquierda() == null && n.getDerecha() != null) {
            nodo = 1;
            derecho = getGradoN(n.getDerecha());
        }
        return Math.max(izquierdo, Math.max(derecho, nodo));
    }

    public int getGrado() {
        if (getAltura() == -1) return 0;
        else {
            return getGradoN(this.raiz);
        }
    }
    public ListaDoblementeEnlazada<Nodo<TipoDeDatos>> getCamino(TipoDeDatos dato) {
        try {
            ListaDoblementeEnlazada<Nodo<TipoDeDatos>> camino = new ListaDoblementeEnlazada<>();
            ListaDoblementeEnlazada<Nodo<TipoDeDatos>> caminoNuevo = getCaminoPrinc(this.raiz, dato, camino);
            if (caminoNuevo.isVacia()) throw new NonexistentElement();
            else return caminoNuevo;
        } catch (NonexistentElement e) {
            System.out.println("El dato que estas buscando no existe");
            return null;
        }
    }

    private ListaDoblementeEnlazada<Nodo<TipoDeDatos>> getCaminoPrinc(Nodo<TipoDeDatos> raiz, TipoDeDatos dato, ListaDoblementeEnlazada<Nodo<TipoDeDatos>> camino) {
        if (raiz == null) return camino;
        getCaminoPrinc(raiz.getIzquierda(), dato, camino);
        if (camino.isVacia()) getCaminoPrinc(raiz.getDerecha(), dato, camino);
        if (raiz.getDato() == dato || !camino.isVacia()) camino.insert(raiz, 0);
        return camino;
    }
    public BST<TipoDeDatos> getSubArbolDcha() {
        BST<TipoDeDatos> subArbol = new BST<>(this.raiz.getDerecha());
        return subArbol;
    }

    public BST<TipoDeDatos> getSubArbolIzq() {
        BST<TipoDeDatos> subArbol = new BST<>(this.raiz.getIzquierda());
        return subArbol;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> preOrden() {
        ListaDoblementeEnlazada<TipoDeDatos> lista = new ListaDoblementeEnlazada<>();
        lista = getPreOrden(this.raiz, lista);
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> getPreOrden(Nodo<TipoDeDatos> nodo, ListaDoblementeEnlazada<TipoDeDatos> lista) {
        if ((nodo != null)) {
            lista.add(nodo.getDato());
            getPreOrden(nodo.getIzquierda(), lista);
            getPreOrden(nodo.getDerecha(), lista);
        }
        return lista;
    }
    public ListaDoblementeEnlazada<TipoDeDatos> ordenCentral() {
        ListaDoblementeEnlazada<TipoDeDatos> lista = new ListaDoblementeEnlazada<>();
        lista = getOrdenCentral(this.raiz, lista);
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> getOrdenCentral(Nodo<TipoDeDatos> nodo, ListaDoblementeEnlazada<TipoDeDatos> lista) {
        if ((nodo != null)) {
            getOrdenCentral(nodo.getIzquierda(), lista);
            lista.add(nodo.getDato());
            getOrdenCentral(nodo.getDerecha(), lista);
        }
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> postOrden() {
        ListaDoblementeEnlazada<TipoDeDatos> lista = new ListaDoblementeEnlazada<>();
        lista = getPostOrden(this.raiz, lista);
        return lista;
    }

    public ListaDoblementeEnlazada<TipoDeDatos> getPostOrden(Nodo<TipoDeDatos> nodo, ListaDoblementeEnlazada<TipoDeDatos> lista) {
        if ((nodo != null)) {
            getPostOrden(nodo.getIzquierda(), lista);
            getPostOrden(nodo.getDerecha(), lista);
            lista.add(nodo.getDato());
        }
        return lista;
    }
    public int getAlturaN(Nodo<TipoDeDatos> nodo) {
        if (nodo == null) return - 1;
        int altIzq = getAlturaN(nodo.getIzquierda()) + 1;
        int altDcha = getAlturaN(nodo.getDerecha()) + 1;
        return Math.max(altIzq, altDcha);
    }
    public int getAltura() {
        return getAlturaN(this.raiz);
    }
    public ListaDoblementeEnlazada<Nodo<TipoDeDatos>> getDatos(int nivel) {
        ListaDoblementeEnlazada<Nodo<TipoDeDatos>> lista = new ListaDoblementeEnlazada<>();
        getDatosAux(this.raiz, nivel, lista);
        return lista;
    }
    private void getDatosAux(Nodo<TipoDeDatos> nodo, int nivel, ListaDoblementeEnlazada<Nodo<TipoDeDatos>> lista) {
        if (nodo == null) return;
        if (nivel == 1) {
            lista.add(nodo);
            return;
        }
        getDatosAux(nodo.getIzquierda(), nivel - 1, lista);
        getDatosAux(nodo.getDerecha(), nivel - 1, lista);
    }

    public Boolean isArbolHomogeneo() {
        return isArbolHomogeneoPrinc(this.raiz);
    }

    private Boolean isArbolHomogeneoPrinc(Nodo<TipoDeDatos> nodo) {
        if (nodo == null || (nodo.getDerecha() != null && nodo.getIzquierda() != null)) {
            return true;
        }
        if (nodo.getDerecha() == null || nodo.getIzquierda() == null) {
            return false;
        }
        return isArbolHomogeneoPrinc(nodo.getDerecha()) && isArbolHomogeneoPrinc(nodo.getIzquierda());
    }

    public Boolean isArbolCompleto() {
        return isArbolCompletoPrinc(getAltura());
    }

    private Boolean isArbolCompletoPrinc(int nivel) {
        if (nivel == 0) {
            return true;
        }
        if (getDatos(nivel).getNumeroElementos() != Math.pow(2, nivel - 1)) {
            return false;
        }
        return isArbolCompletoPrinc(nivel - 1);
    }

    public Boolean isArbolCasiCompleto() {
        if (!isArbolCompletoPrinc(getAltura() - 1)) {
            return false;
        } else {
            ListaDoblementeEnlazada<Nodo<TipoDeDatos>> lista = getDatos(getAltura() - 1);
            return isArbolCasiCompleto1(lista);
        }
    }

    private Boolean isArbolCasiCompleto1(ListaDoblementeEnlazada<Nodo<TipoDeDatos>> lista) {
        if (lista.isVacia()) {
            return false;
        }
        BST<TipoDeDatos> arbol = new BST<>(lista.getPrimero().getData());
        if (arbol.getGrado() == 2) {
            lista.del(0);
            return isArbolCasiCompleto1(lista);
        } else if (arbol.getGrado() == 1) {
            lista.del(0);
            return isArbolCasiCompleto2(lista);
        } else return false;
    }

    private Boolean isArbolCasiCompleto2(ListaDoblementeEnlazada<Nodo<TipoDeDatos>> lista) {
        if (lista.isVacia()) return true;
        BST<TipoDeDatos> arbol = new BST<>(lista.getPrimero().getData().getDato());
        if (arbol.getGrado() != 0) {
            return false;
        } else {
            lista.del(0);
            return isArbolCasiCompleto2(lista);
        }
    }

    public Nodo<TipoDeDatos> getRaiz() {
        return raiz;
    }
}


