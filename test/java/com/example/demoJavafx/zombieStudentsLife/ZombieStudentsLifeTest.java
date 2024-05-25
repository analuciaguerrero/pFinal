package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.bucleDeControl.BucleDeControl;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.Nodo;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Grafo;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Mapa;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ZombieStudentsLifeTest {

    private DatosJuego datosJuego;
    private Tablero tablero;
    private ZombieStudentsLife zombieStudentsLife;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        tablero = new Tablero(5, 5, datosJuego);
        zombieStudentsLife = new ZombieStudentsLife(datosJuego, tablero);
    }

    @Test
    void testGetSetDato() {
        DatosJuego newDato = new DatosJuego();
        zombieStudentsLife.setDato(newDato);
        assertEquals(newDato, zombieStudentsLife.getDato());
    }

    @Test
    void testGetSetTablero() {
        Tablero newTablero = new Tablero();
        zombieStudentsLife.setTablero(newTablero);
        assertEquals(newTablero, zombieStudentsLife.getTablero());
    }

    @Test
    void testGetSetBucle() {
        BucleDeControl newBucle = new BucleDeControl(tablero, datosJuego);
        zombieStudentsLife.setBucle(newBucle);
        assertEquals(newBucle, zombieStudentsLife.getBucle());
    }

    @Test
    void testGetArbolGenealogico() {
        assertNotNull(zombieStudentsLife.getArbolGenealogico());
    }

    @Test
    void testGetGrafoDeOperaciones() {
        assertNotNull(zombieStudentsLife.getGrafoDeOperaciones());
    }

    @Test
    void testStart() {
        BucleDeControl bucle = new BucleDeControl(tablero, datosJuego);
        zombieStudentsLife.setBucle(bucle);
        zombieStudentsLife.start(true);
        assertTrue(bucle.isTurno());
    }

    @Test
    void testAddPadres() {
        Estudiante estudiante = new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        ListaSimple<Estudiante> padres = new ListaSimple<>();
        padres.add(new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        padres.add(new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        estudiante.setPadres(padres);

        Nodo<Estudiante> hijo = new Nodo<>(estudiante);

        try {
            Method method = ZombieStudentsLife.class.getDeclaredMethod("addPadres", Nodo.class);
            method.setAccessible(true);
            method.invoke(zombieStudentsLife, hijo);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Reflection failed");
        }

        assertNotNull(hijo.getDerecha());
        assertNotNull(hijo.getIzquierda());
    }

    @Test
    void testCrearArbolGenealogico() {
        Estudiante estudiante = new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        estudiantes.add(estudiante);
        datosJuego.setEstudiantes(estudiantes);

        Mapa<Estudiante, BST<Estudiante>> arbolGenealogico = null;

        try {
            Method method = ZombieStudentsLife.class.getDeclaredMethod("crearArbolGenealogico");
            method.setAccessible(true);
            arbolGenealogico = (Mapa<Estudiante, BST<Estudiante>>) method.invoke(zombieStudentsLife);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Reflection failed");
        }

        assertNotNull(arbolGenealogico);
        assertEquals(estudiante, arbolGenealogico.get(estudiante).getRaiz().getDato());
    }


    @Test
    void testCrearGrafoDeOperaciones() {
        Estudiante estudiante = new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        ListaEnlazada<Estudiante> historialEstudiantes = new ListaEnlazada<>();
        historialEstudiantes.add(estudiante);
        datosJuego.setHistorialEstudiantes(historialEstudiantes);

        Grafo<String> grafoDeOperaciones = null;

        try {
            Method method = ZombieStudentsLife.class.getDeclaredMethod("crearGrafoDeOperaciones");
            method.setAccessible(true);
            grafoDeOperaciones = (Grafo<String>) method.invoke(zombieStudentsLife);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Reflection failed");
        }

        assertNotNull(grafoDeOperaciones);
        assertNotNull(grafoDeOperaciones.getNodoGrafo("Nacer"));
        assertNotNull(grafoDeOperaciones.getNodoGrafo("Morir"));
    }

    @Test
    void testInformacion() {
        zombieStudentsLife.informacion();
        assertNotNull(zombieStudentsLife.getArbolGenealogico());
        assertNotNull(zombieStudentsLife.getGrafoDeOperaciones());
    }

    @Test
    void testFinalizarPartida() {
        zombieStudentsLife.finalizarPartida();
        assertNotNull(zombieStudentsLife.getArbolGenealogico());
        assertNotNull(zombieStudentsLife.getGrafoDeOperaciones());
    }
}