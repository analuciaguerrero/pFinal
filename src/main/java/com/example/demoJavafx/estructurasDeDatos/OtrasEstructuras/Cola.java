package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import javafx.scene.layout.TilePane;

public class Cola<Dato> {
    private ListaDoblementeEnlazada<Dato> datos = new ListaDoblementeEnlazada<>();
    public ElementoLDE<Dato> cabeza;
    private ElementoLDE<Dato> cola;
    public Cola(ElementoLDE<Dato> cabeza, ElementoLDE<Dato> cola) {
        this.cabeza = cabeza;
        this.cola = cola;
    }
    public Cola() {
        datos = new ListaDoblementeEnlazada<>();
    }
    public ListaDoblementeEnlazada<Dato> getDatos() {
        return datos;
    }

    public void setDatos(ListaDoblementeEnlazada<Dato> datos) {
        this.datos = datos;
    }

    public ElementoLDE<Dato> getCola() {
        return cola;
    }

    public void setCola(ElementoLDE<Dato> cola) {
        this.cola = cola;
    }

    public ElementoLDE<Dato> getCabeza() {
        return cabeza;
    }

    public void setCabeza(ElementoLDE<Dato> cabeza) {
        this.cabeza = cabeza;
    }

    public Dato pull () {
        Dato primero = datos.getPrimero().getData();
        datos.del(0);
        return primero;
    }
    public void push(ElementoLDE<Dato> elemento){
        ElementoLDE<Dato> use = new ElementoLDE<>(elemento.getData());
        if (isVacia()){
            cabeza=use;
        }else{
            use.setSiguiente(cola);
            cola.setAnterior(use);
        }
        cola=use;

    }
    public ElementoLDE<Dato> pop(){
        ElementoLDE<Dato> cabeza1 = cabeza;
        ElementoLDE<Dato> cabeza2 = getCabeza();
        if (cabeza != null) {
            cabeza=cabeza.getAnterior();
            cabeza1.setAnterior(null);
        }else{
            cola = null;
            cabeza = null;
        }
        return cabeza2;

    }
    public ElementoLDE<Dato> getElemento(int posicion){
        ElementoLDE<Dato> cabeza1 = this.cabeza;
        int contador = 0;
        if (cabeza1 != null) {
            while (contador < posicion) {
                cabeza1 = cabeza1.getAnterior();
                contador ++;
            }
        }
        return cabeza1;
    }
    public int getNumeroElementos() {
        ElementoLDE<Dato> cabeza = this.cabeza;
        if (this.cabeza==null){
            return 0;
        }else{
            int cont = 0;
            while (cabeza!=null){
                cabeza = cabeza.getAnterior();
                cont++;
            }
            return cont;
        }
    }
    public int getPosicion(ElementoLDE<Dato> el) {
        if (!isVacia()){
            ElementoLDE<Dato> cabeza1 = this.cabeza;
            int cont=0;
            while(cabeza1.getData()!=el.getData() && cont<getNumeroElementos()-1){
                cabeza1 = cabeza1.getAnterior();
                cont++;
            }
            return cont;
        }else{
            return -1;
        }
    }
    public void add (Dato e) {
        datos.add(e);
    }

    public boolean isVacia(){
        return cabeza==null;
    }
    public boolean esVacia() {
        return datos.isVacia();
    }
}


