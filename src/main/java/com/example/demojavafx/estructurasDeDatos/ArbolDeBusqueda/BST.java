package com.example.demojavafx.estructurasDeDatos.ArbolDeBusqueda;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;

public class BST<TipoDeDatos>{
    public Nodo<TipoDeDatos> raiz;
    public BST(Nodo<TipoDeDatos> raiz, Nodo<TipoDeDatos> derecha, Nodo<TipoDeDatos> izquierda) {
        this.raiz = raiz;
        this.raiz.setDerecha(derecha);
        this.raiz.setIzquierda(izquierda);
    }

    public BST(Nodo<TipoDeDatos> raiz) {
        this.raiz = raiz;
    }

    public BST() {
        Nodo<TipoDeDatos> nodo = new Nodo<>(null);
        this.raiz = nodo;
    }
    public BST(TipoDeDatos dato) {
        this.raiz = new Nodo<>(dato);
    }

    public boolean isVacia() {
        return this.raiz.dato == null;
    }

    public void add(TipoDeDatos a1, Nodo<TipoDeDatos> raiz) {
        Nodo<TipoDeDatos> n1 = new Nodo<>(a1);
        Comparable c = (Comparable) raiz.getDato();
        if (isVacia()) {
            this.raiz.setDato(a1);
            this.raiz.setDerecha(null);
            this.raiz.setIzquierda(null);
        } else {
            if (c.compareTo(a1) <= 0) {
                if (raiz.getDerecha() == null) {
                    raiz.setDerecha(n1);
                } else {
                    add(a1, raiz.getDerecha());
                }
            } else {
                if (raiz.getIzquierda() == null) {
                    raiz.setIzquierda(n1);
                } else {
                    add(a1, raiz.getIzquierda());
                }
            }
        }
    }

    public void add(TipoDeDatos a1) {
        add(a1, this.raiz);
    }

    public int getGrado(Nodo<TipoDeDatos> n, int result) {
        if (result < n.gradoNodo()) {
            result = n.gradoNodo();
        }
        if (n.getIzquierda() != null) {
            getGrado(n.getIzquierda(), result);
        }
        if (n.getDerecha() != null) {
            getGrado(n.getDerecha(), result);
        }
        return result;
    }

    public ListaEnlazada<TipoDeDatos> getCamino(Nodo<TipoDeDatos> n, Nodo<TipoDeDatos> raiz, ListaEnlazada<TipoDeDatos> lista) {
        Comparable c = (Comparable) n.getDato();
        lista.add(raiz.getDato());
        if (c.compareTo(raiz.getDato()) < 0) {
            getCamino(n, raiz.getIzquierda(), lista);
        } else if (c.compareTo(raiz.getDato()) > 0) {
            getCamino(n, raiz.getDerecha(), lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDeDatos> getCamino(Nodo<TipoDeDatos> n) {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        ListaEnlazada<TipoDeDatos> lista1 = getCamino(n, this.raiz, lista);
        return lista1.invertir();
    }

    public int getLongitud(Nodo<TipoDeDatos> nodo){
        return (getCamino(nodo).getNumeroElementos()-1);
    }

    public BST<TipoDeDatos> getSubArbolDcha() {
        BST<TipoDeDatos> subArbol = new BST<>(this.raiz.getDerecha());
        return subArbol;
    }

    public BST<TipoDeDatos> getSubArbolIzq() {
        BST<TipoDeDatos> subArbol = new BST<>(this.raiz.getIzquierda());
        return subArbol;
    }

    public ListaEnlazada<TipoDeDatos> preorden(Nodo<TipoDeDatos> n, ListaEnlazada<TipoDeDatos> lista) {
        lista.add(n.dato);
        //System.out.println(n.dato);
        if (n.getIzquierda() != null) {
            preorden(n.getIzquierda(), lista);
        }
        if (n.getDerecha() != null) {
            preorden(n.getDerecha(), lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDeDatos> preorden() {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        return preorden(this.raiz, lista).invertir();
    }

    public ListaEnlazada<TipoDeDatos> ordenCentral(Nodo<TipoDeDatos> n, ListaEnlazada<TipoDeDatos> lista) {
        if (n.getIzquierda() != null) {
            ordenCentral(n.getIzquierda(), lista);
        }
        //System.out.println(n.dato);
        lista.add(n.dato);
        if (n.getDerecha() != null) {
            ordenCentral(n.derecha, lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDeDatos> ordenCentral() {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        return ordenCentral(this.raiz, lista).invertir();
    }

    public ListaEnlazada<TipoDeDatos> postOrder(Nodo<TipoDeDatos> n, ListaEnlazada<TipoDeDatos> lista) {
        if (n.getIzquierda() != null) {
            postOrder(n.getIzquierda(), lista);
        }
        if (n.getDerecha() != null) {
            postOrder(n.getDerecha(), lista);
        }
        //System.out.println(n.dato);
        lista.add(n.dato);
        return lista;
    }

    public ListaEnlazada<TipoDeDatos> postOrder() {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        return postOrder(this.raiz, lista).invertir();
    }

    public int getAltura(Nodo<TipoDeDatos> n, int p) {
        int x = 0;
        int y = 0;
        if (n.getDerecha() != null) {
            y = getAltura(n.getDerecha(), p + 1);
        }
        if (n.getIzquierda() != null) {
            x = getAltura(n.getIzquierda(), p + 1);
        }
        if(x>=y && x>p){
            return x;
        } else if (x<y && y>p) {
            return y;
        }
        return p;
    }

    public int getAltura() {
        return 1 + getAltura(this.raiz, 0);
    }

    boolean aux = true;
    boolean aux2 = true;

    public boolean isArbolCompleto(Nodo<TipoDeDatos> n, int numero) {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        if (n.getIzquierda() == null && n.getDerecha() == null) {
            lista = getCamino(n, this.raiz, lista);
            int num = lista.getNumeroElementos();
            if (num != numero) {
                aux = false;
            }
        }
        if (n.getIzquierda() != null) {
            isArbolCompleto(n.getIzquierda(), numero);
        }
        if (n.getDerecha() != null) {
            isArbolCompleto(n.getDerecha(), numero);
        }
        return aux;
    }

    public boolean isArbolCompleto() {
        aux = true;
        int num = 1;
        Nodo<TipoDeDatos> nodo = this.raiz;
        while (nodo.getIzquierda() != null) {
            num++;
            nodo = nodo.getIzquierda();
        }
        return isArbolCompleto(this.raiz, num);
    }

    public boolean isArbolHomogeneo(Nodo<TipoDeDatos> n) {
        int num = n.gradoNodo();
        if (num != 2 && num != 0) {
            aux = false;
        }
        if (n.getIzquierda() != null) {
            isArbolHomogeneo(n.getIzquierda());
        }
        if (n.getDerecha() != null) {
            isArbolHomogeneo(n.getDerecha());
        }
        return aux;
    }

    public boolean isArbolHomogeneo() {
        aux = true;
        return isArbolHomogeneo(this.raiz);
    }

    public ListaEnlazada<TipoDeDatos> getListaDatosNivel(int nivel, Nodo<TipoDeDatos> n, ListaEnlazada<TipoDeDatos> lista) {
        int num = this.getCamino(n).getNumeroElementos();
        if (num == nivel) {
            lista.add(n.dato);
        }
        if (n.getIzquierda() != null) {
            getListaDatosNivel(nivel, n.getIzquierda(), lista);
        }
        if (n.getDerecha() != null) {
            getListaDatosNivel(nivel, n.getDerecha(), lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDeDatos> getListaDatosNivel(int nivel) {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        return getListaDatosNivel(nivel, this.raiz, lista).invertir();
    }

    public boolean isArbolCasiCompleto(Nodo<TipoDeDatos> n, int numero) {
        ListaEnlazada<TipoDeDatos> lista = new ListaEnlazada<>();
        if (n.getIzquierda() == null && n.getDerecha() == null) {
            lista = getCamino(n, this.raiz, lista);
            int num = lista.getNumeroElementos();
            if (num == numero - 1) {
                aux = false;
            } else if (num <= numero - 1) {
                aux2 = false;
            }
            if (!aux) {
                if (num != numero - 1) {
                    aux2 = false;
                }
            }
        }
        if (n.getIzquierda() != null) {
            isArbolCasiCompleto(n.getIzquierda(), numero);
        }
        if (n.getDerecha() != null) {
            isArbolCasiCompleto(n.getDerecha(), numero);
        }
        return aux2;
    }

    public boolean isArbolCasiCompleto() {
        int num = 1;
        Nodo<TipoDeDatos> nodo = this.raiz;
        while (nodo.getIzquierda() != null) {
            num++;
            nodo = nodo.getIzquierda();
        }
        if (isArbolCompleto()) {
            aux = true;
            aux2 = true;
            return false;
        } else {
            aux = true;
            aux2 = true;
            return isArbolCasiCompleto(this.raiz, num);
        }
    }
}


