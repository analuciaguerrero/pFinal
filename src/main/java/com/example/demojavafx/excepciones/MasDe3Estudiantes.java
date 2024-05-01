package com.example.demojavafx.excepciones;

import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demojavafx.estudiante.Estudiante;

public class MasDe3Estudiantes extends Exception {
    public MasDe3Estudiantes(ListaEnlazada<Estudiante> listaEstudiante){
        int aux = 0;
        int res = 0;
        while (aux < listaEstudiante.getNumeroElementos()) {
            int elem = listaEstudiante.getElemento(aux).getData().getTiempoDeVida();
            if (elem > listaEstudiante.getElemento(res).getData().getTiempoDeVida()) {
                res = aux;
            }
            aux++;
        }
        listaEstudiante.delete(res);
    }
}
