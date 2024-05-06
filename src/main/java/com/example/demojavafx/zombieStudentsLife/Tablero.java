package com.example.demojavafx.zombieStudentsLife;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demojavafx.estudiante.Estudiante;
import com.example.demojavafx.entorno.*;
import com.example.demojavafx.excepciones.MasDe3Estudiantes;
import java.util.Random;
public class Tablero {
    protected int tamañoN;
    protected int tamañoM;
    protected ListaEnlazada<Celda> celdas;
    private int numEstudiantes;
    private int numRecursos;
    protected static final int maxEstudiantesPorCelda = 3;
    protected static final int maxRecursosPorCelda = 3;

    protected static final double probAgua = 0.15; // Probabilidad de aparición del 15%
    protected static final double probComida = 0.20; // Probabilidad de aparición del 20%
    protected static final double probMontaña = 0.10; // Probabilidad de aparición del 10%
    protected static final double probTesoro = 0.15; // Probabilidad de aparición del 15%
    protected static final double probBiblioteca = 0.20; // Probabilidad de aparición del 20%
    protected static final double probPozo = 0.20; // Probabilidad de aparición del 20%

    public Tablero(int tamañoN, int tamañoM) {
        this.tamañoN = tamañoN;
        this.tamañoM = tamañoM;
        celdas = new ListaEnlazada<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < tamañoN; i++) {
            for (int j = 0; j < tamañoM; j++) {
                celdas.add(new Celda());
            }
        }
    }

    public void evaluarMejoras() {
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            Celda celda = nodoCelda.getData();
            celda.evaluarMejoras();
            nodoCelda = nodoCelda.getSiguiente();
        }
    }

    public void evaluarReproduccion() {
        // Seleccionar dos estudiantes aleatoriamente para la reproducción
        Random rand = new Random();
        int indice1 = rand.nextInt(celdas.getNumeroElementos());
        int indice2 = rand.nextInt(celdas.getNumeroElementos());
        while (indice1 == indice2) {
            indice2 = rand.nextInt(celdas.getNumeroElementos());
        }
        Estudiante estudiante1 = obtenerEstudianteEnIndice(indice1);
        Estudiante estudiante2 = obtenerEstudianteEnIndice(indice2);

        try {
            // Verificar si los estudiantes pueden reproducirse
            if (estudiante1.puedeReproducirse() && estudiante2.puedeReproducirse()) {
                // Realizar la reproducción
                if (rand.nextDouble() < estudiante1.getProbReproduccion() && rand.nextDouble() < estudiante2.getProbReproduccion()) {
                    // Se reproducen generando un nuevo estudiante
                    Estudiante nuevoEstudiante = estudiante1.reproducirse(estudiante2);
                    // Agregar el nuevo estudiante a la celda
                    int indiceCelda = rand.nextInt(celdas.getNumeroElementos());
                    ElementoLE<Celda> celda = celdas.getElemento(indiceCelda);
                    if (celda.getListaEstudiantes().getNumeroElementos() < maxEstudiantesPorCelda) {
                        celda.agregarEstudiante(nuevoEstudiante);
                    } else {
                        System.out.println("No se pudo agregar un nuevo estudiante: Máximo de estudiantes alcanzado en la celda.");
                    }
                }
            }
        } catch (MasDe3Estudiantes e) {
            System.out.println("No se pudo agregar un nuevo estudiante: " + e.getMessage());
        }
    }
    private Estudiante obtenerEstudianteEnIndice(int indice) {
        // Obtener el estudiante en la posición dada
        int contador = 0;
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            if (contador == indice) {
                return nodoCelda.getData().obtenerEstudianteAleatorio();
            }
            contador++;
            nodoCelda = nodoCelda.getSiguiente();
        }
        return null; // Si el índice es inválido o no se encuentra al estudiante
    }

    public void evaluarClonacion() {
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            Estudiante estudiante = nodoCelda.getData().obtenerEstudianteAleatorio();
            if (estudiante != null && Math.random() < estudiante.getProbClonacion()) {
                try {
                    // Clonar el estudiante
                    Estudiante clon = estudiante.clonar();
                    // Agregar el clon a la celda
                    agregarEstudiante(clon);
                } catch (MasDe3Estudiantes e) {
                    System.out.println("No se pudo clonar al estudiante: " + e.getMessage());
                }
            }
            nodoCelda = nodoCelda.getSiguiente();
        }
    }
    public void evaluarDesaparicionEstudiantes() {
        if (celdas.getNumeroElementos() > maxEstudiantesPorCelda) {
            // Eliminar estudiantes si hay más de los máximos permitidos por celda
            ElementoLE<Celda> nodoCelda = celdas.getPrimero();
            while (nodoCelda != null) {
                Celda celda = nodoCelda.getData();
                if (celda.getListaEstudiantes().getNumeroElementos() > maxEstudiantesPorCelda) {
                    celda.eliminarEstudianteAleatorio();
                }
                nodoCelda = nodoCelda.getSiguiente();
            }
        }
    }

    private Recursos generarNuevoRecurso() {
        Random rand = new Random();
        double probabilidad = rand.nextDouble(); // Genera un número aleatorio entre 0 y 1

        // Calcula el tipo de recurso basado en las probabilidades
        if (probabilidad < probAgua) {
            return new Agua(rand.nextInt(), 2);
        } else if (probabilidad < probAgua + probComida) {
            return new Comida(rand.nextInt(), 10);
        } else if (probabilidad < probAgua + probComida + probMontaña) {
            return new Montaña(rand.nextInt(), 2);
        } else if (probabilidad < probAgua + probComida + probMontaña + probTesoro) {
            return new Tesoro(rand.nextInt(), 0.40);
        } else if (probabilidad < probAgua + probComida + probMontaña + probTesoro + probBiblioteca) {
            return new Biblioteca(rand.nextInt(), 0.25);
        } else {
            return new Pozo(generarNuevoRecurso().getTurnosRestantes());
        }
    }
    public void evaluarAparicionRecursos() {
        Random rand = new Random();
        for (int i = 0; i < tamañoN; i++) {
            for (int j = 0; j < tamañoM; j++) {
                double probabilidad = rand.nextDouble();
                if (probabilidad < probAgua) {
                    celdas.agregarRecurso(new Agua());
                } else if (probabilidad < probAgua + probComida) {
                    celdas[i][j].agregarRecurso(new Comida());
                } else if (probabilidad < probAgua + probComida + probMontaña) {
                    celdas[i][j].agregarRecurso(new Montaña());
                } else if (probabilidad < probAgua + probComida + probMontaña + probTesoro) {
                    celdas[i][j].agregarRecurso(new Tesoro());
                } else if (probabilidad < probAgua + probComida + probMontaña + probTesoro + probBiblioteca) {
                    celdas[i][j].agregarRecurso(new Biblioteca());
                } else {
                    celdas[i][j].agregarRecurso(new Pozo());
                }
            }
        }
    }
}
