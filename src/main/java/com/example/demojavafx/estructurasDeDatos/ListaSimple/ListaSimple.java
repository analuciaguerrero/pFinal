package com.example.demojavafx.estructurasDeDatos.ListaSimple;

public class ListaSimple<TipoDeldato> {
    protected ElementoLS<TipoDeldato>[] datos;

    private Integer maximo = 10000;

    public ListaSimple() {
        this.datos = new ElementoLS[maximo];
    }

    public ListaSimple(TipoDeldato a) {
        this.datos = new ElementoLS[maximo];
        datos[0] = new ElementoLS<>(a);
    }

    public ListaSimple<TipoDeldato> copiaLista(){
        ListaSimple<TipoDeldato> nuevaLista = new ListaSimple<>();
        Integer contador=0;
        while (contador<this.getNumeroElementos()){
            nuevaLista.add(datos[contador].getData());
            contador++;
        }
        nuevaLista.setMaximo(this.maximo);
        return nuevaLista;
    }
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

    public void add(TipoDeldato el) {
        int i = 0;
        while (datos[i] != null) {
            i++;
        }
        datos[i] = new ElementoLS<>(el);
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

    public Integer getPosicion(TipoDeldato el) {
        int cont = 0;
        Integer posicion = null;
        while (cont < maximo && datos[cont] != null) {
            if (datos[cont].getData() == el) {
                posicion = cont;
            }
            cont++;
        }
        return posicion;
    }

    public ElementoLS<TipoDeldato> getPrimero() {
        return datos[0];
    }

    public ElementoLS<TipoDeldato> getUltimo() {
        if (!isVacia()) {
            int contador = 0;
            while (datos[contador] != null && contador < maximo) {
                contador++;
            }
            return datos[contador - 1];
        } else {
            return null;
        }
    }

    private ElementoLS<TipoDeldato> getSiguiente(ElementoLS<TipoDeldato> el) {
        ElementoLS<TipoDeldato> devolver = null;
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null && datos[i + 1] != null && datos[i].getData() == el.getData())
                devolver = datos[i + 1];
        }
        return devolver;
    }

    public ElementoLS<TipoDeldato> getElemento(int pos) {
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
            int contador = 1;
            ElementoLS<TipoDeldato> el = datos[contador-1];
            while (this.getSiguiente(el) != null) {
                el=this.getSiguiente(el);
                contador++;
            }
            return contador;
        }
    }

    public ListaSimple<TipoDeldato> voltear() {
        ListaSimple<TipoDeldato> nuevaLista = new ListaSimple<>();
        Integer contador=this.getNumeroElementos()-1;
        while (contador>=0){
            nuevaLista.add(this.getElemento(contador).getData());
            contador--;
        }
        return nuevaLista;
    }

    @Override
    public String toString() {
        int contador = 0;
        StringBuilder lista = new StringBuilder("[");
        while (contador < this.getNumeroElementos()) {
            if (this.getElemento(contador + 1) != null) {
                lista.append(this.getElemento(contador).getData()).append(", ");
            } else {
                lista.append(this.getElemento(contador).getData()).append("]");
            }
            contador++;
        }
        return lista.toString();
    }
}