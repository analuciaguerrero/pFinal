package com.example.demojavafx.estudiante;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada2;

public class EstudianteAvanzado extends Estudiante {
    public EstudianteAvanzado(int id, int tiempoDeVida) {
        super(id,tiempoDeVida);
    }
    @Override
    public String getTipo () {
        return "EstudianteAvanzado";
    }
}
