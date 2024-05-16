package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.bucleDeControl.BucleDeControl;
import com.example.demoJavafx.bucleDeControl.BucleDeControlProperties;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZombieStudentsLifeTest {
    @Test
    public void testConstructorWithData() {
        // Creamos una instancia de DatosJuego para pasar al constructor
        DatosJuego datosJuego = new DatosJuego();

        // Creamos una instancia de ZombieStudentsLife utilizando el constructor con datos
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego);

        // Verificamos si la instancia no es nula
        assertNotNull(zombieStudentsLife);
    }

    @Test
    public void testStart() {
        // Creamos una instancia de DatosJuego
        DatosJuego datosJuego = new DatosJuego();

        // Creamos una instancia de ZombieStudentsLife
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego);

        // Iniciamos el bucle de control
        zombieStudentsLife.start(true); // Pasamos true para indicar que es el turno del bucle

        // Verificamos si el bucle de control se ha iniciado correctamente
        assertTrue(zombieStudentsLife.getBucle().isTurno());
    }

    @Test
    public void testCargarJuego() {
        // Creamos una instancia de DatosJuego
        DatosJuego datosJuego = new DatosJuego();

        // Creamos una instancia de ZombieStudentsLife
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego);

        // Cargamos el juego
        ZombieStudentsLife juegoCargado = zombieStudentsLife.CargarJuego();

        // Verificamos si el juego cargado no es nulo
        assertNotNull(juegoCargado);
    }
    @Test
    public void testConstructorWithDataAndTablero() {
        // Creamos una instancia de DatosJuego y Tablero
        DatosJuego datosJuego = new DatosJuego();
        Tablero tablero = new Tablero(5, 5, datosJuego); // Se crea un tablero 5x5 para el test

        // Creamos una instancia de ZombieStudentsLife utilizando el constructor con datos y tablero
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego, tablero);

        // Verificamos si la instancia no es nula
        assertNotNull(zombieStudentsLife);

        // Verificamos si los objetos de DatosJuego y Tablero se inicializan correctamente
        assertEquals(datosJuego, zombieStudentsLife.getDato());
        assertEquals(tablero, zombieStudentsLife.getTablero());
    }

    @Test
    public void testSetDato() {
        // Creamos una instancia de DatosJuego y Tablero
        DatosJuego datosJuego = new DatosJuego();
        Tablero tablero = new Tablero(5, 5, datosJuego); // Se crea un tablero 5x5 para el test

        // Creamos una instancia de ZombieStudentsLife
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego, tablero);

        // Creamos una instancia de DatosJuego diferente
        DatosJuego nuevoDatosJuego = new DatosJuego();

        // Establecemos el nuevo objeto de DatosJuego
        zombieStudentsLife.setDato(nuevoDatosJuego);

        // Verificamos si el objeto de DatosJuego se ha actualizado correctamente
        assertEquals(nuevoDatosJuego, zombieStudentsLife.getDato());
    }

    @Test
    public void testSetTablero() {
        // Creamos una instancia de DatosJuego y Tablero
        DatosJuego datosJuego = new DatosJuego();
        Tablero tablero = new Tablero(5, 5, datosJuego); // Se crea un tablero 5x5 para el test

        // Creamos una instancia de ZombieStudentsLife
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego, tablero);

        // Creamos una instancia de Tablero diferente
        Tablero nuevoTablero = new Tablero(8, 8, datosJuego); // Se crea un tablero 8x8 para el test

        // Establecemos el nuevo objeto de Tablero
        zombieStudentsLife.setTablero(nuevoTablero);

        // Verificamos si el objeto de Tablero se ha actualizado correctamente
        assertEquals(nuevoTablero, zombieStudentsLife.getTablero());
    }
    @Test
    public void testSetBucle() {
        // Creamos una instancia de DatosJuego y Tablero
        DatosJuego datosJuego = new DatosJuego();
        Tablero tablero = new Tablero(5, 5, datosJuego); // Se crea un tablero 5x5 para el test

        // Creamos una instancia de ZombieStudentsLife
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego, tablero);

        // Creamos una instancia de BucleDeControl
        BucleDeControl bucle = new BucleDeControl(tablero, datosJuego);

        // Establecemos el nuevo objeto de BucleDeControl
        zombieStudentsLife.setBucle(bucle);

        // Verificamos si el objeto de BucleDeControl se ha establecido correctamente
        assertEquals(bucle, zombieStudentsLife.getBucle());
    }

    @Test
    public void testSetPropiedad() {
        // Creamos una instancia de DatosJuego y Tablero
        DatosJuego datosJuego = new DatosJuego();
        Tablero tablero = new Tablero(5, 5, datosJuego); // Se crea un tablero 5x5 para el test

        // Creamos una instancia de ZombieStudentsLife
        ZombieStudentsLife zombieStudentsLife = new ZombieStudentsLife(datosJuego, tablero);

        // Creamos una instancia de BucleDeControlProperties
        BucleDeControlProperties propiedad = new BucleDeControlProperties();

        // Establecemos el nuevo objeto de BucleDeControlProperties
        zombieStudentsLife.setPropiedad(propiedad);

        // Verificamos si el objeto de BucleDeControlProperties se ha establecido correctamente
        assertEquals(propiedad, zombieStudentsLife.getPropiedad());
    }
}