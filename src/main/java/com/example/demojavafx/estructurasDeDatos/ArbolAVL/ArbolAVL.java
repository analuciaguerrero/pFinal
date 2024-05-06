package com.example.demojavafx.estructurasDeDatos.ArbolAVL;

import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demojavafx.excepciones.DuplicateElement;
import com.example.demojavafx.excepciones.NonexistentElement;
import com.example.demojavafx.excepciones.VoidLevel;

public class ArbolAVL<TipoDelDato>{
    private NodoAVL<TipoDelDato> raiz;
    public ArbolAVL() {
        this.raiz = null;
    }

    public ArbolAVL(TipoDelDato raiz) {
        this.raiz = new NodoAVL<>(raiz);
    }

    public ArbolAVL(NodoAVL<TipoDelDato> nodo) {
        this.raiz = nodo;
    }
    public NodoAVL<TipoDelDato> getRaiz() {
        return raiz;
    }

    public ArbolAVL<TipoDelDato> getSubArbolIzquierda() {
        if ((this.raiz != null) && (this.raiz.getNodoIzq() != null)) {
            return new ArbolAVL<>(this.raiz.getNodoIzq());
        } else {
            return null;
        }
    }

    public ArbolAVL<TipoDelDato> getSubArbolDerecha() {
        if ((this.raiz != null) && (this.raiz.getNodoDch() != null)) {
            return new ArbolAVL<>(this.getRaiz().getNodoDch());
        } else {
            return null;
        }
    }

    private Integer compararDatos(TipoDelDato a, TipoDelDato b) {
        Comparable dato1 = (Comparable) a;
        Comparable dato2 = (Comparable) b;
        return dato1.compareTo(dato2);
    }
    public void add(TipoDelDato a) throws DuplicateElement {
        if (this.raiz == null) {
            this.raiz = new NodoAVL<>(a);
        } else {
            raiz.add(raiz, a);
        }
    }

    public void del(TipoDelDato a) throws NonexistentElement {
        if (raiz != null) {
            raiz.del(null, raiz, a);
        } else {
            throw (new NonexistentElement("La raíz del arbol seleccionado es nula"));
        }
    }

    private int gradoRecursivo(NodoAVL<TipoDelDato> actual) {
        int grado = actual.getGrado();
        int gradoizq;
        int gradodch;
        if (actual.getNodoIzq() == null) {
            gradoizq = 0;
        } else {
            gradoizq = this.gradoRecursivo(actual.getNodoIzq());
        }
        if (actual.getNodoDch() == null) {
            gradodch = 0;
        } else {
            gradodch = this.gradoRecursivo(actual.getNodoDch());
        }
        return Math.max(grado, Math.max(gradoizq, gradodch));
    }

    public Integer getGradoMaximo() {
        if (this.raiz == null) {
            return null;
        } else {
            return gradoRecursivo(raiz);
        }
    }

    public Integer getAltura() {
        if (this.raiz == null) {
            return 0;
        }
        return raiz.alturaNodo(raiz);
    }

    public ListaEnlazada<TipoDelDato> getListaDatosNivel(int nivel) throws VoidLevel {
        if ((nivel > 0) && (nivel <= this.getAltura())) {
            ListaEnlazada<TipoDelDato> datosNivel = new ListaEnlazada<>();
            return listaDatosNivelRecursivo(this.raiz, nivel, datosNivel);
        } else {
            throw (new VoidLevel("Nivel erróneo"));
        }
    }

    private ListaEnlazada<TipoDelDato> listaDatosNivelRecursivo(NodoAVL<TipoDelDato> nodo, int nivel, ListaEnlazada<TipoDelDato> lista) {
        if (nodo != null) {
            if (nivel == 1) {
                lista.add(new ElementoLE<>(nodo.getDato()));
            } else if (nivel > 1) {
                listaDatosNivelRecursivo(nodo.getNodoIzq(), nivel - 1, lista);
                listaDatosNivelRecursivo(nodo.getNodoDch(), nivel - 1, lista);
            }
        }
        return lista;
    }

    // En el siguiente método hemos añadido una excepción para el caso en que el elemento no exista en el árbol
    public ListaEnlazada<TipoDelDato> getCamino(TipoDelDato dato) throws NonexistentElement {
        ListaEnlazada<TipoDelDato> camino = new ListaEnlazada<>();
        boolean contenido = false;
        ListaEnlazada<TipoDelDato> listaElementos = this.getListaOrdenCentral();
        int contador = 0;
        while (contador < listaElementos.getNumeroElementos()) {
            if (this.compararDatos(dato, listaElementos.getElemento(contador).getData()) == 0) {
                contenido = true;
            }
            contador++;
        }
        if (contenido) {
            this.getCaminoRecursivo(dato, this.raiz, camino);
        } else { // Lanzamos excepción
            throw (new NonexistentElement("Elemento inexistente"));
        }
        return camino;
    }

    private void getCaminoRecursivo(TipoDelDato dato, NodoAVL<TipoDelDato> nodo, ListaEnlazada<TipoDelDato> lista) {
        lista.add(new ElementoLE<>(nodo.getDato()));
        if ((nodo.getNodoIzq() != null) && (this.compararDatos(dato, nodo.getDato()) == -1)) {
            getCaminoRecursivo(dato, nodo.getNodoIzq(), lista);
        } else if ((nodo.getNodoDch() != null) && (this.compararDatos(dato, nodo.getDato()) == 1)) {
            getCaminoRecursivo(dato, nodo.getNodoDch(), lista);
        }
    }
    private void listaNodosNivelRecursivo(NodoAVL<TipoDelDato> nodo, int nivel, ListaEnlazada<NodoAVL<TipoDelDato>> lista) {
        if (nodo != null) {
            if (nivel == 1) {
                lista.add(new ElementoLE<>(nodo));
            } else if (nivel > 1) {
                listaNodosNivelRecursivo(nodo.getNodoIzq(), nivel - 1, lista);
                listaNodosNivelRecursivo(nodo.getNodoDch(), nivel - 1, lista);
            }
        }
    }

    private ListaEnlazada<NodoAVL<TipoDelDato>> getListaNodosNivel() {
        ListaEnlazada<NodoAVL<TipoDelDato>> nodosNivel = new ListaEnlazada<>();
        this.listaNodosNivelRecursivo(this.raiz, this.getAltura() - 1, nodosNivel);
        return nodosNivel;
    }

    public boolean isArbolCasiCompleto() {
        boolean CasiCompleto = false;
        try {
            if ((Math.pow(2, this.getAltura() - 2) == this.getListaDatosNivel(this.getAltura() - 1).getNumeroElementos()) && (!this.isArbolCompleto())) {
                ListaEnlazada<NodoAVL<TipoDelDato>> listaNodos = this.getListaNodosNivel();
                int contador = 0;
                int posicionDeCambio = 0;
                while (contador < listaNodos.getNumeroElementos()) {
                    if (listaNodos.getElemento(contador).getData().getGrado() == 2) {
                        contador++;
                    } else {
                        posicionDeCambio = contador;
                        contador = listaNodos.getNumeroElementos() + 1;
                    }

                }
                if ((listaNodos.getElemento(posicionDeCambio).getData().getGrado() == 0) || ((listaNodos.getElemento(posicionDeCambio).getData().getNodoIzq() != null) && (listaNodos.getElemento(posicionDeCambio).getData().getNodoDch() == null))) {
                    if (posicionDeCambio == listaNodos.getNumeroElementos() - 1) {
                        CasiCompleto = true;
                    } else {
                        boolean NodosNulos = false;
                        while (posicionDeCambio + 1 < listaNodos.getNumeroElementos()) {
                            if (listaNodos.getElemento(posicionDeCambio + 1).getData().getGrado() == 0) {
                                NodosNulos = true;
                            } else {
                                NodosNulos = false;
                                posicionDeCambio = listaNodos.getNumeroElementos() + 2;
                            }
                            posicionDeCambio++;
                        }
                        CasiCompleto = NodosNulos;
                    }
                }
            }
            // Al ejecutar los tests las dos siguientes lineas de código no son testeadas (el programa nunca va a pasar por ellas pero hay que añadirlas al código para manejar la excepción)
        } catch (VoidLevel ex) {
            ex.printStackTrace();
        }
        return CasiCompleto;
    }

    public boolean isArbolHomogeneo() {  //Devuelve si todos los nodos del arbol tienen el mismo grado (0, 1 o 2)
        if (this.isArbolCompleto()) {
            return true;
        } else if (this.nodosGradoUno(this.raiz)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean nodosGradoUno(NodoAVL<TipoDelDato> nodo) {
        if (nodo.getNodoDch() == null && nodo.getNodoIzq() != null) {
            return (nodosGradoUno(nodo.getNodoIzq()));
        } else if (nodo.getNodoDch() != null && nodo.getNodoIzq() == null) {
            return (nodosGradoUno(nodo.getNodoDch()));
        } else if (nodo.getNodoDch() != null && nodo.getNodoIzq() != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isArbolCompleto() {  //Devuelve si el arbol forma un triangulo (todos los nodos tienen grado 2)
        Integer contador = 0;
        while (contador < this.getAltura()) {
            try {
                if (Math.pow(2, contador) == this.getListaDatosNivel(contador + 1).getNumeroElementos()) {
                    contador++;
                } else {
                    return false;
                }
                // Al ejecutar los tests las dos siguientes lineas de código no son testeadas (el programa nunca va a pasar por ellas pero hay que añadirlas al código para manejar la excepción)
            } catch (VoidLevel ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    public ListaEnlazada<TipoDelDato> getListaOrdenCentral() {
        ListaEnlazada<TipoDelDato> resultado = new ListaEnlazada<>();
        recorridoOrdenCentral(raiz, resultado);
        return resultado;
    }

    private void recorridoOrdenCentral(NodoAVL<TipoDelDato> nodo, ListaEnlazada<TipoDelDato> lista) {
        if (nodo != null) {
            recorridoOrdenCentral(nodo.getNodoIzq(), lista);
            lista.add(new ElementoLE<>(nodo.getDato()));
            recorridoOrdenCentral(nodo.getNodoDch(), lista);
        }
    }

    public ListaEnlazada<TipoDelDato> getListaPreOrden() {
        ListaEnlazada<TipoDelDato> resultado = new ListaEnlazada<>();
        recorridoPreOrden(raiz, resultado);
        return resultado;
    }

    private void recorridoPreOrden(NodoAVL<TipoDelDato> nodo, ListaEnlazada<TipoDelDato> lista) {
        if (nodo != null) {
            lista.add(new ElementoLE<>(nodo.getDato()));
            recorridoPreOrden(nodo.getNodoIzq(), lista);
            recorridoPreOrden(nodo.getNodoDch(), lista);
        }
    }

    public ListaEnlazada<TipoDelDato> getListaPostOrden() {
        ListaEnlazada<TipoDelDato> resultado = new ListaEnlazada<>();
        recorridoPostOrden(raiz, resultado);
        return resultado;
    }

    private void recorridoPostOrden(NodoAVL<TipoDelDato> nodo, ListaEnlazada<TipoDelDato> lista) {
        if (nodo != null) {
            recorridoPostOrden(nodo.getNodoIzq(), lista);
            recorridoPostOrden(nodo.getNodoDch(), lista);
            lista.add(new ElementoLE<>(nodo.getDato()));
        }
    }
}

