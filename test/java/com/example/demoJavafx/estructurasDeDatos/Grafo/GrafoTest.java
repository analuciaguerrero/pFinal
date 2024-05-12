package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demoJavafx.excepciones.*;
class GrafoTest {
    @Test
    public void testConstructorConUnVertice() {
        Vertice v1 = new Vertice(1,1); // Crear un vértice para usar en la prueba
        Grafo grafo = new Grafo(v1); // Crear el grafo con un vértice

        // Verificar que el vértice se agregó correctamente al grafo
        assertEquals(1, grafo.getVertices().getNumeroElementos());
        assertEquals(v1, grafo.getVertices().getElemento(0).getData());
    }

    @Test
    void addVertice() {
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        Vertice v2 = new Vertice(2, "B");

        try {
            grafo.addVertice(v1);
            assertEquals(1, grafo.getVertices().getNumeroElementos());
            grafo.addVertice(v2);
            assertEquals(2, grafo.getVertices().getNumeroElementos());
        } catch (ExistentVertix e) {
            fail("No debería lanzar ExistentVertix");
        }

        assertThrows(ExistentVertix.class, () -> grafo.addVertice(v1));
    }

    @Test
    void addArista() {
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        Vertice v2 = new Vertice(2, "B");
        Arista a1 = new Arista(v1, v2, 1);

        assertThrows(NonValidLink.class, () -> grafo.addArista(a1));

        try {
            grafo.addVertice(v1);
            grafo.addVertice(v2);
            grafo.addArista(a1);
            assertEquals(1, grafo.getAristas().getNumeroElementos());
        } catch (ExistentVertix | NonValidLink e) {
            fail("No debería lanzar excepciones");
        }
        Arista aristaMismoVertice = new Arista(v1, v1, 3);
        assertThrows(NonValidLink.class, () -> grafo.addArista(aristaMismoVertice));

    }
    @Test
    public void testAddAristaConVerticesIguales() throws ExistentVertix {
        // Crear un grafo con un vértice
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1,"A");
        grafo.addVertice(v1);

        // Intentar agregar una arista con vértices iguales, debería lanzar una excepción
        Arista arista = new Arista(v1, v1, 5);
        assertThrows(NonValidLink.class, () -> {
            grafo.addArista(arista);
        });
    }
    @Test
    public void testAddAristaConVerticeNoEncontrado() throws ExistentVertix {
        // Crear un grafo con algunos vértices
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        grafo.addVertice(v1);

        // Crear una arista que intenta unir un vértice existente con uno inexistente
        Vertice vNoExistente = new Vertice(2, "B");
        Arista arista = new Arista(v1, vNoExistente, 5);

        // Verificar que agregar esta arista lance la excepción NonValidLink
        assertThrows(NonValidLink.class, () -> grafo.addArista(arista));
    }

    @Test
    public void testAddAristaConVerticesNoEnGrafo() {
        // Crear un grafo vacío
        Grafo grafo = new Grafo();

        // Crear una arista con vértices que no están en el grafo, debería lanzar una excepción
        Vertice v1 = new Vertice(1,"A");
        Vertice v2 = new Vertice(2,"B");
        Arista arista = new Arista(v1, v2, 5);
        assertThrows(NonValidLink.class, () -> {
            grafo.addArista(arista);
        });
    }
    @Test
    public void testValidarArista_AristaExistente() throws ExistentVertix, NonValidLink {
        // Crear un grafo con algunos vértices y una arista existente
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        Vertice v2 = new Vertice(2, "B");
        grafo.addVertice(v1);
        grafo.addVertice(v2);
        Arista aristaExistente = new Arista(v1, v2, 5);
        grafo.addArista(aristaExistente);

        // Intentar agregar una nueva arista que ya existe entre los mismos vértices
        Arista nuevaArista = new Arista(v1, v2, 3);
        assertThrows(NonValidLink.class, () -> grafo.validarArista(nuevaArista));
    }




    @Test
    void delArista() {
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        Vertice v2 = new Vertice(2, "B");
        Arista a1 = new Arista(v1, v2, 1);

        assertThrows(NonexistentElement.class, () -> grafo.delArista(v1, v2));

        try {
            grafo.addVertice(v1);
            grafo.addVertice(v2);
            grafo.addArista(a1);
            grafo.delArista(v1, v2);
            assertEquals(0, grafo.getAristas().getNumeroElementos());
        } catch (ExistentVertix | NonValidLink | NonexistentElement e) {
            fail("No debería lanzar excepciones");
        }
    }

    @Test
    void delVertice() {
        // Crear un grafo con algunos vértices y aristas
        Grafo grafo = new Grafo();
        Vertice verticeA = new Vertice(1, "A");
        Vertice verticeB = new Vertice(2, "B");
        Vertice verticeC = new Vertice(3, "C");
        try {
            grafo.addVertice(verticeA);
            grafo.addVertice(verticeB);
            grafo.addVertice(verticeC);
            grafo.addArista(new Arista(verticeA, verticeB, 2));
            grafo.addArista(new Arista(verticeA, verticeC, 5));
            grafo.addArista(new Arista(verticeB, verticeC, 1));
        } catch (ExistentVertix | NonValidLink e) {
            fail("No debería lanzar una excepción aquí.");
        }

        // Eliminar un vértice del grafo
        try {
            grafo.delVertice(verticeA);
        } catch (NonexistentElement e) {
            fail("No debería lanzar una excepción aquí.");
        }

        // Verificar que el vértice se haya eliminado correctamente
        assertEquals(2, grafo.getVertices().getNumeroElementos(), "El grafo debería tener dos vértices después de eliminar uno.");
    }
    @Test
    public void testDelVertice() throws NonexistentElement, ExistentVertix, NonValidLink {
        // Crear un grafo con algunos vértices y aristas
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        Vertice v2 = new Vertice(2, "B");
        Vertice v3 = new Vertice(3, "C");
        grafo.addVertice(v1);
        grafo.addVertice(v2);
        grafo.addVertice(v3);
        grafo.addArista(new Arista(v1, v2, 5));
        grafo.addArista(new Arista(v2, v3, 3));

        // Eliminar un vértice existente y verificar
        grafo.delVertice(v2);
        assertEquals(2, grafo.getVertices().getNumeroElementos());
        assertEquals(0, grafo.getAristas().getNumeroElementos());
    }

    @Test
    public void testDelVerticeInexistente() throws ExistentVertix {
        // Crear un grafo con algunos vértices
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1,"A");
        Vertice v2 = new Vertice(2,"B");
        grafo.addVertice(v1);

        // Intentar eliminar un vértice que no está en el grafo, debería lanzar una excepción
        Vertice vInexistente = new Vertice(3,"C");
        assertThrows(NonexistentElement.class, () -> {
            grafo.delVertice(vInexistente);
        });
    }

    @Test
    void getStringVertices() {
        Grafo g1 = new Grafo();
        Vertice<Integer> v1 = new Vertice<>(1, 1);
        Vertice<Integer> v2 = new Vertice<>(2, 2);
        Vertice<Integer> v3 = new Vertice<>(3, 3);
        Vertice<Integer> v4 = new Vertice<>(4, 4);
        try {
            g1.addVertice(v1);
            g1.addVertice(v2);
            g1.addVertice(v3);
            g1.addVertice(v4);
        } catch (ExistentVertix ex) {
            ex.printStackTrace();
        }
        assertEquals("[1, 2, 3, 4]", g1.getStringVertices());
    }

    @Test
    void getStringAristas() {
        Grafo grafo = new Grafo();
        Vertice v1 = new Vertice(1, "A");
        Vertice v2 = new Vertice(2, "B");
        Arista a1 = new Arista(v1, v2, 1);

        try {
            grafo.addVertice(v1);
            grafo.addVertice(v2);
            grafo.addArista(a1);
            assertEquals("[{A, B}].", grafo.getStringAristas());
        } catch (ExistentVertix | NonValidLink e) {
            fail("No debería lanzar excepciones");
        }
    }

    @Test
    void printCodigoGrafo() {
        // Crear un grafo con algunos vértices y aristas
        Grafo grafo = new Grafo();
        Vertice verticeA = new Vertice(1, "A");
        Vertice verticeB = new Vertice(2, "B");
        Vertice verticeC = new Vertice(3, "C");
        try {
            grafo.addVertice(verticeA);
            grafo.addVertice(verticeB);
            grafo.addVertice(verticeC);
            grafo.addArista(new Arista(verticeA, verticeB, 2));
            grafo.addArista(new Arista(verticeA, verticeC, 5));
        } catch (ExistentVertix | NonValidLink e) {
            fail("No debería lanzar una excepción aquí.");
        }

        // Obtener el código DOT del grafo
        String codigoGrafo = grafo.printCodigoGrafo();

        // Verificar el formato del código DOT
        assertTrue(codigoGrafo.contains("n1 [label=\"1\"];"), "El vértice A debería estar representado en el código DOT.");
        assertTrue(codigoGrafo.contains("n2 [label=\"2\"];"), "El vértice B debería estar representado en el código DOT.");
        assertTrue(codigoGrafo.contains("n3 [label=\"3\"];"), "El vértice C debería estar representado en el código DOT.");
        assertTrue(codigoGrafo.contains("n1 -> n2 [label=\"2\"];"), "La arista de A a B debería estar representada en el código DOT.");
        assertTrue(codigoGrafo.contains("n1 -> n3 [label=\"5\"];"), "La arista de A a C debería estar representada en el código DOT.");
    }

    @Test
    void getCaminoMinimo() {
        Grafo g1 = new Grafo();
        Vertice<Integer> v1 = new Vertice<>(1, 1);
        Vertice<Integer> v2 = new Vertice<>(2, 2);
        Vertice<Integer> v3 = new Vertice<>(3, 3);
        Vertice<Integer> v4 = new Vertice<>(4, 4);
        Vertice<Integer> v5 = new Vertice<>(5, 5);
        Vertice<String> v6 = new Vertice<>(6, "hola");
        Vertice<Integer> v7 = new Vertice<>(7, 7);
        try {
            g1.addVertice(v1);
            g1.addVertice(v2);
            g1.addVertice(v3);
            g1.addVertice(v4);
            g1.addVertice(v6);
            g1.addVertice(v7);
        } catch (ExistentVertix ex) {
            ex.printStackTrace();
        }
        try {
            g1.addArista(new Arista(v1, v2, 3));
            g1.addArista(new Arista(v1, v3, 5));
            g1.addArista(new Arista(v4, v1, 1));
            g1.addArista(new Arista(v3, v4, 98));
            g1.addArista(new Arista(v3, v6));
            g1.addArista(new Arista(v7, v2));
        } catch (NonValidLink ex) {
            ex.printStackTrace();
        }
        assertThrows(NonexistentElement.class, () -> g1.getCaminoMinimo(v2, v3));
        assertDoesNotThrow(() -> g1.getCaminoMinimo(v3, v2));
        assertThrows(NonexistentElement.class, () -> g1.getCaminoMinimo(v1, v5));
        assertThrows(NonexistentElement.class, () -> g1.getCaminoMinimo(v7, v6));

    }

    @Test
    void getVertices() {
        // Prueba que el método devuelve la lista de vértices correctamente.
        Grafo grafo = new Grafo();
        ListaSimple<Vertice> vertices = grafo.getVertices();
        assertNotNull(vertices);
        assertTrue(vertices.isVacia());
    }

    @Test
    void getAristas() {
        // Prueba que el método devuelve la lista de aristas correctamente.
        Grafo grafo = new Grafo();
        ListaSimple<Arista> aristas = grafo.getAristas();
        assertNotNull(aristas);
        assertTrue(aristas.isVacia());
    }

    @Test
    void setVertices() {
        // Prueba que el método establece la lista de vértices correctamente.
        Grafo grafo = new Grafo();
        ListaSimple<Vertice> vertices = new ListaSimple<>();
        vertices.add(new Vertice(1, "A"));
        grafo.setVertices(vertices);
        assertEquals(vertices, grafo.getVertices());
    }

    @Test
    void setAristas() {
        // Prueba que el método establece la lista de aristas correctamente.
        Grafo grafo = new Grafo();
        ListaSimple<Arista> aristas = new ListaSimple<>();
        aristas.add(new Arista(new Vertice(1, "A"), new Vertice(2, "B"), 1));
        grafo.setAristas(aristas);
        assertEquals(aristas, grafo.getAristas());
    }

    @Test
    public void testValidarAristaConVerticesNoEnGrafo() {
        // Crear un grafo vacío
        Grafo grafo = new Grafo();

        // Crear una arista con vértices que no están en el grafo, debería lanzar una excepción
        Vertice v1 = new Vertice(1,"A");
        Vertice v2 = new Vertice(2,"B");
        Arista arista = new Arista(v1, v2, 5);
        assertThrows(NonValidLink.class, () -> {
            grafo.validarArista(arista);
        });
    }
}