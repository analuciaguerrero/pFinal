package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.entorno.AguaTest;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.entorno.RecursosTest;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.Test;

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
    void testObtenerEstudianteAleatorioListaVacia() throws MasDe3Estudiantes {
        // Creamos una celda con una lista de estudiantes vacía
        Celda celda = new Celda();

        // Verificamos que obtenerEstudianteAleatorio devuelva null cuando la lista de estudiantes está vacía
        assertNull(celda.obtenerEstudianteAleatorio());
    }

    @Test
    void testObtenerEstudianteAleatorioListaNoVacia() throws MasDe3Estudiantes {
        // Creamos una lista de estudiantes con algunos elementos
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        estudiantes.add(new AguaTest.MockEstudiante(1, 1, 10, 0.5, 0.3, 0.2));
        estudiantes.add(new AguaTest.MockEstudiante(2, 2, 10, 0.5, 0.3, 0.2));
        estudiantes.add(new AguaTest.MockEstudiante(3, 3, 10, 0.5, 0.3, 0.2));

        // Creamos una celda con la lista de estudiantes
        Celda celda = new Celda();
        celda.setListaEstudiantes(estudiantes);

        // Verificamos que obtenerEstudianteAleatorio devuelva un estudiante cuando la lista de estudiantes no está vacía
        assertNotNull(celda.obtenerEstudianteAleatorio());
    }

    @Test
    void testObtenerEstudianteAleatorioValidacionIndice() throws MasDe3Estudiantes {
        // Creamos una lista de estudiantes con un solo elemento
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        estudiantes.add(new AguaTest.MockEstudiante(1, 1, 10, 0.5, 0.3, 0.2));

        // Creamos una celda con la lista de estudiantes
        Celda celda = new Celda();
        celda.setListaEstudiantes(estudiantes);

        // Verificamos que obtenerEstudianteAleatorio devuelva el único estudiante de la lista
        assertEquals(1, celda.obtenerEstudianteAleatorio().getId());
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
    @Test
    void testConstructor() {
        // Creamos una lista de estudiantes y una lista de recursos
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();

        // Agregamos algunos estudiantes y recursos a las listas
        AguaTest.MockEstudiante estudiante1 = new AguaTest.MockEstudiante(1, 1, 10, 0.5, 0.3, 0.2);
        AguaTest.MockEstudiante estudiante2 = new AguaTest.MockEstudiante(2, 1, 10, 0.5, 0.3, 0.2);
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);

        RecursosTest.MockRecursos recurso1 = new RecursosTest.MockRecursos(1, 1, 10, 0.2);
        RecursosTest.MockRecursos recurso2 = new RecursosTest.MockRecursos(2, 1, 10, 0.3);
        recursos.add(recurso1);
        recursos.add(recurso2);

        // Creamos una celda con las listas de estudiantes y recursos
        Celda celda = new Celda(estudiantes, recursos);

        // Verificamos que las listas se asignen correctamente
        assertEquals(estudiantes, celda.getListaEstudiantes());
        assertEquals(recursos, celda.getListaRecursos());

        // Verificamos que los contadores de estudiantes y recursos se inicialicen correctamente
        assertEquals(2, celda.getNumEstudiantes());
        assertEquals(2, celda.getNumRecursos());
    }
    @Test
    void testSetListaEstudiantes() {
        // Creamos una lista de estudiantes y una celda inicialmente vacía
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        Celda celda = new Celda();

        // Agregamos algunos estudiantes a la lista
        AguaTest.MockEstudiante estudiante1 = new AguaTest.MockEstudiante(1, 1, 10, 0.5, 0.3, 0.2);
        AguaTest.MockEstudiante estudiante2 = new AguaTest.MockEstudiante(2, 1, 10, 0.5, 0.3, 0.2);
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);

        // Actualizamos la lista de estudiantes en la celda
        celda.setListaEstudiantes(estudiantes);

        // Verificamos que la lista de estudiantes se haya actualizado correctamente
        assertEquals(estudiantes, celda.getListaEstudiantes());
        assertEquals(2, celda.getNumEstudiantes());
    }

    @Test
    void testSetListaRecursos() {
        // Creamos una lista de recursos y una celda inicialmente vacía
        ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
        Celda celda = new Celda();

        // Agregamos algunos recursos a la lista
        RecursosTest.MockRecursos recurso1 = new RecursosTest.MockRecursos(1, 1, 10, 0.2);
        RecursosTest.MockRecursos recurso2 = new RecursosTest.MockRecursos(2, 1, 10, 0.3);
        recursos.add(recurso1);
        recursos.add(recurso2);

        // Actualizamos la lista de recursos en la celda
        celda.setListaRecursos(recursos);

        // Verificamos que la lista de recursos se haya actualizado correctamente
        assertEquals(recursos, celda.getListaRecursos());
        assertEquals(2, celda.getNumRecursos());
    }
    @Test
    void testGetNumRecursos() {
        // Creamos una celda con dos recursos
        ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
        recursos.add(new RecursosTest.MockRecursos(1, 1, 10, 0.5));
        recursos.add(new RecursosTest.MockRecursos(2, 1, 10, 0.3));
        Celda celda = new Celda(new ListaEnlazada<>(), recursos);

        // Verificamos que el número de recursos sea 2
        assertEquals(2, celda.getNumRecursos());
    }

    @Test
    void testSetNumRecursos() {
        // Creamos una celda inicialmente sin recursos
        Celda celda = new Celda();

        // Establecemos el número de recursos en 3
        celda.setNumRecursos(3);

        // Verificamos que el número de recursos se haya actualizado correctamente
        assertEquals(3, celda.getNumRecursos());
    }

    @Test
    void testGetNumEstudiantes() {
        // Creamos una celda con tres estudiantes
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        estudiantes.add(new AguaTest.MockEstudiante(1, 1, 10, 0.5, 0.3, 0.2));
        estudiantes.add(new AguaTest.MockEstudiante(2, 1, 10, 0.5, 0.3, 0.2));
        estudiantes.add(new AguaTest.MockEstudiante(3, 1, 10, 0.5, 0.3, 0.2));
        Celda celda = new Celda(estudiantes, new ListaEnlazada<>());

        // Verificamos que el número de estudiantes sea 3
        assertEquals(3, celda.getNumEstudiantes());
    }

    @Test
    void testSetNumEstudiantes() {
        // Creamos una celda inicialmente sin estudiantes
        Celda celda = new Celda();

        // Establecemos el número de estudiantes en 2
        celda.setNumEstudiantes(2);

        // Verificamos que el número de estudiantes se haya actualizado correctamente
        assertEquals(2, celda.getNumEstudiantes());
    }
}
