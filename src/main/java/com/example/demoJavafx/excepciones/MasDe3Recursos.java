package com.example.demoJavafx.excepciones;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;

public class MasDe3Recursos extends Exception {
    public MasDe3Recursos(ListaEnlazada<Recursos> listaRecursos) {
        super();
        int res = 0;
        int aux = 0;
        while (aux < listaRecursos.getNumeroElementos()) {
            int elem = listaRecursos.getElemento(aux).getData().getTurnosRestantes();
            if (elem > listaRecursos.getElemento(res).getData().getTurnosRestantes()) {
                res = aux;
            }
            aux++;
        }
        listaRecursos.delete(res);
        System.out.println("\nERROR. Se intentó agregar un recurso a una celda que ya tiene 3 recursos:" + listaRecursos.toString());
    }
}
