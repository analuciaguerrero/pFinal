package com.example.demojavafx.excepciones;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demojavafx.estudiante.Estudiante;

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
