package com.example.demojavafx.excepciones;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;

public class MasDe3Recursos extends Throwable {
    public MasDe3Recursos(ListaEnlazada<Recursos> listaRecursos) {
        super();
        System.out.println("\nERROR. Se intentó agregar un recurso a una celda que ya tiene 3 recursos:" + listaRecursos.toString());
    }
}
