package com.example.demoJavafx.excepciones;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;

public class MasDe3Estudiantes extends Exception {
    public MasDe3Estudiantes(ListaEnlazada<Estudiante> listaEstudiantes) {
        super();
        int rest = 0;
        int auxi = 0;
        while (auxi < listaEstudiantes.getNumeroElementos()) {
            int elem = listaEstudiantes.getElemento(auxi).getData().getTiempoDeVida();
            if (elem > listaEstudiantes.getElemento(rest).getData().getTiempoDeVida()) {
                rest = auxi;
            }
            auxi++;
        }
        listaEstudiantes.delete(rest);
        System.out.println("\nERROR. Se intentó agregar un recurso a una celda que ya tiene 3 estudiantes:" + listaEstudiantes.toString());
    }
}
