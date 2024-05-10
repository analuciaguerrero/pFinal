package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.entorno.AguaTest;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.entorno.RecursosTest;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import org.junit.jupiter.api.Test;

import javax.swing.border.EmptyBorder;

import static org.junit.jupiter.api.Assertions.*;

class CeldaTest {
    @Test
    void testAgregarEstudiante() throws MasDe3Estudiantes {
        Celda celda = new Celda();
        AguaTest.MockEstudiante estudiante1 = new AguaTest.MockEstudiante(1);
        AguaTest.MockEstudiante estudiante2 = new AguaTest.MockEstudiante(2);
        AguaTest.MockEstudiante estudiante3 = new AguaTest.MockEstudiante(3);
        celda.agregarEstudiante(estudiante1);
        celda.agregarEstudiante(estudiante2);
        celda.agregarEstudiante(estudiante3);

        // Verificar que se lanzó una excepción cuando se agregó el cuarto estudiante
        assertThrows(MasDe3Estudiantes.class, () ->
        {
            AguaTest.MockEstudiante estudiante4 = new AguaTest.MockEstudiante(4);
            celda.agregarEstudiante(estudiante4);
        });
    }

    @Test
    void testAgregarRecurso() {
        Celda celda = new Celda();
        RecursosTest.MockRecursos recurso1 = new RecursosTest.MockRecursos();
        RecursosTest.MockRecursos recurso2 = new RecursosTest.MockRecursos();
        RecursosTest.MockRecursos recurso3 = new RecursosTest.MockRecursos();

        try {
            celda.agregarRecurso(recurso1);
            celda.agregarRecurso(recurso2);
            celda.agregarRecurso(recurso3);

            // Verificar que se lanzó una excepción cuando se agregó el cuarto recurso
            assertThrows(MasDe3Recursos.class, () -> {
                RecursosTest.MockRecursos recurso4 = new RecursosTest.MockRecursos();
                celda.agregarRecurso(recurso4);
            });

        } catch (MasDe3Recursos ex) {
            fail("No se esperaba una excepción aquí");
        }
    }

    @Test
    void testEliminarEstudiante() throws MasDe3Estudiantes {
        Celda celda = new Celda();
        AguaTest.MockEstudiante estudiante1 = new AguaTest.MockEstudiante(1);
        Estudiante estudiante2 = new AguaTest.MockEstudiante(2);
        Estudiante estudiante3 = new AguaTest.MockEstudiante(3);

        celda.agregarEstudiante(estudiante1);
        celda.agregarEstudiante(estudiante2);
        celda.agregarEstudiante(estudiante3);

        celda.eliminarEstudiante(estudiante2);

        assertEquals(2, celda.getListaEstudiantes().getNumeroElementos());
        boolean estudiante2Eliminado = true;
        ElementoLE<Estudiante> nodoEstudiante = celda.getListaEstudiantes().getPrimero();
        while (nodoEstudiante != null) {
            if (nodoEstudiante.getData() == estudiante2) {
                estudiante2Eliminado = false;
                break;
            }
            nodoEstudiante = nodoEstudiante.getSiguiente();
        }

        assertTrue(estudiante2Eliminado);
    }

    @Test
    void testEliminarRecurso() throws MasDe3Recursos {
        Celda celda = new Celda();
        RecursosTest.MockRecursos recurso1 = new RecursosTest.MockRecursos();
        RecursosTest.MockRecursos recurso2 = new RecursosTest.MockRecursos();
        RecursosTest.MockRecursos recurso3 = new RecursosTest.MockRecursos();

        celda.agregarRecurso(recurso1);
        celda.agregarRecurso(recurso2);
        celda.agregarRecurso(recurso3);

        celda.eliminarRecurso(recurso2);

        assertEquals(2, celda.getListaRecursos().getNumeroElementos());
        boolean recurso2Eliminado = true;
        ElementoLE<Recursos> nodoRecurso = celda.getListaRecursos().getPrimero();
        while (nodoRecurso != null) {
            if (nodoRecurso.getData() == recurso2) {
                recurso2Eliminado = false;
                break;
            }
            nodoRecurso = nodoRecurso.getSiguiente();
        }

        assertTrue(recurso2Eliminado);
    }

    @Test
    void testObtenerEstudianteAleatorio() throws MasDe3Estudiantes {
        Celda celda = new Celda();
        AguaTest.MockEstudiante estudiante1 = new AguaTest.MockEstudiante(1);
        AguaTest.MockEstudiante estudiante2 = new AguaTest.MockEstudiante(2);
        AguaTest.MockEstudiante estudiante3 = new AguaTest.MockEstudiante(3);

        celda.agregarEstudiante(estudiante1);
        celda.agregarEstudiante(estudiante2);
        celda.agregarEstudiante(estudiante3);

        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);

        // Verificar que el estudiante obtenido está en la lista original
        boolean estudianteEnListaOriginal = false;
        ElementoLE<Estudiante> nodoEstudiante = estudiantes.getPrimero();
        Estudiante estudianteAleatorio = celda.obtenerEstudianteAleatorio();
        while (nodoEstudiante != null) {
            if (nodoEstudiante.getData() == estudianteAleatorio) {
                estudianteEnListaOriginal = true;
                break;
            }
            nodoEstudiante = nodoEstudiante.getSiguiente();
        }

        assertTrue(estudianteEnListaOriginal);
    }

    @Test
    void testEliminarEstudianteAleatorio() throws MasDe3Estudiantes {
        Celda celda = new Celda();
        AguaTest.MockEstudiante estudiante1 = new AguaTest.MockEstudiante(1);
        AguaTest.MockEstudiante estudiante2 = new AguaTest.MockEstudiante(2);
        AguaTest.MockEstudiante estudiante3 = new AguaTest.MockEstudiante(3);

        celda.agregarEstudiante(estudiante1);
        celda.agregarEstudiante(estudiante2);
        celda.agregarEstudiante(estudiante3);

        celda.eliminarEstudianteAleatorio();

        // Verificar que el número de estudiantes disminuyó en uno
        assertEquals(2, celda.getListaEstudiantes().getNumeroElementos());
    }

    @Test
    void testEvaluarMejoras() throws MasDe3Estudiantes, MasDe3Recursos {
        Celda celda = new Celda();
        AguaTest.MockEstudiante estudiante = new AguaTest.MockEstudiante(1);
        RecursosTest.MockRecursos recurso = new RecursosTest.MockRecursos();

        celda.agregarEstudiante(estudiante);
        celda.agregarRecurso(recurso);

        // Verificar que el método no lance ninguna excepción
        assertDoesNotThrow(() -> {
            celda.evaluarMejoras();
        });
    }
}
