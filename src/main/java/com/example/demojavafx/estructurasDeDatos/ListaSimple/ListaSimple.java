package com.example.demojavafx.estructurasDeDatos.ListaSimple;

public class ListaSimple {
    protected ElementoLS[] datos;

    private Integer maximo = 9;

    //////////////

    public ListaSimple() {
        this.datos = new ElementoLS[maximo];
    }

    ////////////////

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    public boolean isVacia() {
        return getNumeroElementos() == 0;
    }

    public void vaciar() {
        Integer i = 0;
        int numElem = getNumeroElementos();
        while (i < numElem) {
            datos[i] = null;
            i++;
        }
    }

    private Integer add(ElementoLS el) {
        Integer i = 0;
        while (i < maximo && datos[i] != null) {
            i++;
        }
        datos[i] = el;
        return getNumeroElementos();
    }

    public void add(String s) {
        ElementoLS elem = new ElementoLS(s);
        add(elem);
    }

    public void add(Object o) {
        ElementoLS elem = new ElementoLS(o);
        add(elem);
    }

    private void insert(ElementoLS el, Integer pos) {
        if (pos >= 0 && pos <= getNumeroElementos()) {  // Comprobar que la posición sea válida
            if (pos == getNumeroElementos()) {
                add(el);
            } else {
                Integer elementosIniciales = getNumeroElementos();
                ElementoLS SiguienteElemento = datos[pos];
                datos[pos] = el;
                pos++;
                while (elementosIniciales == getNumeroElementos()) {
                    ElementoLS nextEl = datos[pos];
                    datos[pos] = SiguienteElemento;
                    SiguienteElemento = nextEl;
                    pos++;
                }
            }
        }
    }

    public void insert(String s, Integer pos) {
        ElementoLS elem = new ElementoLS(s);
        insert(elem, pos);
    }

    public void insert(Object o, Integer pos) {
        ElementoLS elem = new ElementoLS(o);
        insert(elem, pos);
    }

    public int del(int pos) {
        Integer num_elem = getNumeroElementos();
        if (pos < num_elem && pos >= 0) {
            datos[pos] = null;
            if (pos != num_elem - 1) {
                datos[pos] = datos[num_elem - 1];
                datos[num_elem - 1] = null;
            }
        }
        return num_elem - 1;
    }

    public Integer getPosicion(ElementoLS el) {
        Integer cont = 0;
        Integer posicion = null;
        while (cont < maximo && datos[cont] != null) {
            if (datos[cont].getData() == el.getData()) {
                posicion = cont;
            }
            cont++;
        }
        return posicion;
    }

    public ElementoLS getPrimero() {
        return datos[0];
    }

    public ElementoLS getUltimo() {
        if (!isVacia()) {
            Integer contador = 0;
            while (datos[contador] != null && contador < maximo) {
                contador++;
            }
            return datos[contador - 1];
        } else {
            return null;
        }
    }

    private ElementoLS getSiguiente(ElementoLS el) {
        ElementoLS devolver = null;
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null && datos[i + 1] != null && datos[i].getData() == el.getData())
                devolver = datos[i + 1];
        }
        return devolver;
    }

    public ElementoLS getElemento(int pos) {
        if (pos < maximo && pos >= 0) {
            return datos[pos];
        } else {
            return null;
        }
    }

    public Integer getNumeroElementos() {
        if (datos[0]==null) {
            return 0;
        } else {
            Integer contador = 1;
            ElementoLS el = datos[contador-1];
            while (this.getSiguiente(el) != null) {
                el=this.getSiguiente(el);
                contador++;
            }
            return contador;
        }
    }
}
