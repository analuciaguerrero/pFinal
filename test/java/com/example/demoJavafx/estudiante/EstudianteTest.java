package com.example.demoJavafx.estudiante;
import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
    @Test
    public void testReproducirse() {
        // Crear un estudiante básico y uno normal
        EstudianteBasico estudianteBasico = new EstudianteBasico(1, 0, 5, 0.5, 0.2);
        EstudianteNormal estudianteNormal = new EstudianteNormal(2, 0, 5, 0.5, 0.2);

        // Crear una celda vacía
        Celda celda = new Celda(0, 0);

        // Intentar reproducirse entre ellos
        boolean muerteEstudianteBasico = estudianteBasico.reproducirse(estudianteNormal, null, celda);
        boolean muerteEstudianteNormal = estudianteNormal.reproducirse(estudianteBasico, null, celda);

        // Verificar que uno de los dos debe morir según la probabilidad de reproducción
        assertTrue(muerteEstudianteBasico || muerteEstudianteNormal);
    }

    @Test
    public void testClonar() {
        // Crear un estudiante básico
        EstudianteBasico estudianteBasico = new EstudianteBasico(1, 0, 5, 0.5, 0.2);

        // Crear una celda vacía
        Celda celda = new Celda(0, 0);

        // Clonar al estudiante
        estudianteBasico.clonar(null, celda);

        // Verificar que el estudiante ha sido clonado
        assertEquals(2, celda.getListaEstudiantes().getNumeroElementos());
    }

    @Test
    public void testActualizarTiempoDeVida() {
        // Crear un estudiante básico con un tiempo de vida de 3
        EstudianteBasico estudianteBasico = new EstudianteBasico(1, 0, 3, 0.5, 0.2);

        // Crear una celda vacía
        Celda celda = new Celda(0, 0);

        // Actualizar el tiempo de vida del estudiante
        boolean muerte = estudianteBasico.actualizarTiempoDeVida(null, celda);

        // Verificar que el estudiante sigue vivo después de un ciclo de vida
        assertFalse(muerte);

        // Actualizar nuevamente el tiempo de vida del estudiante
        muerte = estudianteBasico.actualizarTiempoDeVida(null, celda);

        // Verificar que el estudiante ha muerto después de dos ciclos de vida
        assertTrue(muerte);
    }

    @Test
    public void testMoverseAleatorio() {
        // Crear un estudiante básico
        EstudianteBasico estudianteBasico = new EstudianteBasico(1, 0, 5, 0.5, 0.2);
        DatosJuego dato = new DatosJuego();
        // Crear un tablero con una sola celda
        Tablero tablero = new Tablero(1, 1, dato);

        // Mover al estudiante aleatoriamente varias veces y verificar que no haya excepciones
        for (int i = 0; i < 10; i++) {
            estudianteBasico.moverseAleatorio(tablero);
        }
    }
}