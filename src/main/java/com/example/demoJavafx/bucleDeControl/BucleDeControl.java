package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.excepciones.MasDe3Recursos;
import com.example.demoJavafx.zombieStudentsLife.Tablero;

public class BucleDeControl {
    private Tablero tablero;

    public BucleDeControl(Tablero tablero) {
        this.tablero = tablero;
    }

    public void ejecutar() {
        // Paso 1: Actualizar tiempo de vida de cada individuo y eliminar si ha muerto
        tablero.actualizarTiempoDeVida();

        // Paso 2: Evaluar si los recursos deben eliminarse por su tiempo de aparición
        tablero.evaluarEliminacionRecursos();

        // Paso 3: Ejecutar el movimiento de cada individuo
        tablero.moverEstudiantes();

        // Paso 4: Evaluar mejoras obtenidas por los recursos en la nueva posición de cada individuo
        tablero.evaluarMejoras();

        // Paso 5: Evaluar reproducción
        tablero.evaluarReproduccion();

        // Paso 6: Evaluar clonación
        tablero.evaluarClonacion();

        // Paso 7: Evaluar desaparición de estudiantes si hay más de 3 en una celda
        tablero.evaluarDesaparicionEstudiantes();

        // Paso 8: Evaluar si deben aparecer nuevos recursos
        try {
            tablero.evaluarAparicionRecursos();
        } catch (MasDe3Recursos e) {
            System.out.println("No se pudo agregar un nuevo recurso: " + e.getMessage());
        }
    }
}
