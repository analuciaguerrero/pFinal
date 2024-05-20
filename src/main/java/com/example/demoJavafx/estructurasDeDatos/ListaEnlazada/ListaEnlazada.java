package com.example.demoJavafx.estructurasDeDatos.ListaEnlazada;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaEnlazada<TipoDeDatos> {
    private static final Logger log = LogManager.getLogger();
    private ElementoLE<TipoDeDatos> primero;
    public ListaEnlazada(ElementoLE<TipoDeDatos> primero) {
        this.primero = primero;
    }
    public ListaEnlazada() {
        this.primero = null;
    }


    public boolean isVacia() {
        return this.primero == null;
    }

    public void vaciar() {
        this.primero = null;
    }
    public ElementoLE<TipoDeDatos> getPrimero() {
        if (isVacia()) {
            return null;
        }else{
            return this.primero;
        }
    }
    public ElementoLE<TipoDeDatos> getUltimo() {
        if (this.isVacia()) {
            return null;
        }else{
            ElementoLE<TipoDeDatos> first = this.primero;
            while (first.getSiguiente() != null) {
                first = first.getSiguiente();
            }
            return first;
        }
    }
    public ElementoLE<TipoDeDatos> getElemento(int posicion) {
        if (isVacia()) {
            return null;
        }else{
            ElementoLE<TipoDeDatos> first = this.primero;
            for (int i=0; i != posicion; i++) {
                first = first.getSiguiente();
            }
            return first;
        }
    }
    public int getNumeroElementos() {
        if (isVacia()) {
            return 0;
        }else{
            int elem = 0;
            ElementoLE<TipoDeDatos> first = primero;
            while (first != null) {
                elem += 1;
                first = first.getSiguiente();
            }
            return elem;
        }
    }
    public Integer getPosicion(ElementoLE<TipoDeDatos> el) {
        int pos = 0;
        ElementoLE<TipoDeDatos> first = this.primero;
        for (int i = 0; (pos < getNumeroElementos()) && (first.getData() != el.getData()); i++) {
            first = first.getSiguiente();
            pos += 1;
        }
        if ((pos>=getNumeroElementos())&&(el.getData()!= getUltimo().getData())) {
            return null;
        }
        return pos;
    }
    public Integer getPosicion(TipoDeDatos el) {
        if (!this.isVacia()) {
            int pos = 0;
            ElementoLE<TipoDeDatos> first = this.primero;
            for (int i = 0; (pos < getNumeroElementos()) && (first.getData() != el); i++) {
                first = first.getSiguiente();
                pos += 1;
            }
            if ((pos >= getNumeroElementos())&&(el != this.getUltimo().getData())) {
                return null;
            }
            return pos;
        }
        return null;
    }
    public ElementoLE<TipoDeDatos> getSiguiente(ElementoLE<TipoDeDatos> el) {
        return el.getSiguiente();
    }
    private void add(ElementoLE<TipoDeDatos> el) {
        if (isVacia()) {
            this.primero = el;
        } else {
            ElementoLE<TipoDeDatos> actual = this.primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            el.insertarmeEn(actual);
        }
    }
    public void add(TipoDeDatos dato) {
        ElementoLE<TipoDeDatos> el = new ElementoLE<>();
        el.setData(dato);
        add(el);
    }
    public void insert(TipoDeDatos objeto, int posicion) {
        ElementoLE<TipoDeDatos> obj = new ElementoLE<>();
        obj.setData(objeto);
        if (posicion == 0) {
            obj.setSiguiente(primero);
            primero = obj;
        } else {
            obj.insertarmeEn(getElemento(posicion-1));
        }
    }
    public int delete(int pos) {
        if (pos == 0) {
            primero = primero.getSiguiente();
            return this.getNumeroElementos();
        }else{
            ElementoLE<TipoDeDatos> first = this.primero;
            for (int i=0; i != pos - 1; i++) {
                if (first.getSiguiente().getSiguiente() == null) {
                    first.setSiguiente(null);
                    return this.getNumeroElementos();
                }
                first = first.getSiguiente();
            }
            first.setSiguiente(first.getSiguiente().getSiguiente());
            return this.getNumeroElementos();
        }
    }

    public void del (TipoDeDatos el) {
        Integer indiceAEliminar = null;
        for (int i=0; i != getNumeroElementos(); i++) {
            if (getElemento(i).getData() == el) {
                indiceAEliminar = i;
            }
        }
        if (indiceAEliminar == null) {
            log.warn("El elemento que se quería eliminar no pertenece a la lista");
        } else {
            delete(indiceAEliminar);
        }
    }
    private void reverseRecursivo(ListaEnlazada<TipoDeDatos> lista, ListaEnlazada<TipoDeDatos> listaInvert, int index) {
        if (index == lista.getNumeroElementos() ) {
            return;
        }
        reverseRecursivo(lista, listaInvert, index + 1);
        listaInvert.add(lista.getElemento(index));
    }
    public ListaEnlazada<TipoDeDatos> reverse(ListaEnlazada<TipoDeDatos> lista) {
        ListaEnlazada<TipoDeDatos> listaInvertida = new ListaEnlazada<>();
        if (lista.getNumeroElementos() <= 1) {
            return lista;
        }
        reverseRecursivo(lista, listaInvertida, 0);
        return listaInvertida;
    }
    public String toString() {
        return "[" + toStrings(this.primero) + "]";
    }
    private String toStrings(ElementoLE<TipoDeDatos> n) {
        String ret = "";
        if (n == null) {
            ret = "";
        } else if (n != this.getUltimo()) {
            ret = n.getData() + ", " + toStrings(n.getSiguiente());
        } else if (n == this.getUltimo()) {
            ret = n.getData() + "";
        }
        return ret;
    }
}
