package com.example.demoJavafx.excepciones;

import com.example.demoJavafx.estudiante.Estudiante;

public class RecursosNoUtilizados extends RuntimeException {
    private Estudiante estudiante;
    public RecursosNoUtilizados (Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
}
