package com.example.demojavafx.zombieStudentsLife;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demojavafx.estudiante.Estudiante;
import com.example.demojavafx.entorno.Agua;
import com.example.demojavafx.entorno.Comida;
import com.example.demojavafx.entorno.Montaña;
import com.example.demojavafx.entorno.Pozo;
import com.example.demojavafx.entorno.Biblioteca;
import com.example.demojavafx.entorno.Tesoro;
import java.util.Random;

import static com.example.demojavafx.zombieStudentsLife.Tablero.*;

public class Celda {
    private ListaEnlazada<Estudiante> estudiantes;
    private ListaEnlazada<Recursos> recursos;
    private int numEstudiantes;
    private int numRecursos;

    protected static final int maxEstudiantesPorCelda = 3;
    protected static final int maxRecursosPorCelda = 3;

    public Celda() {
        estudiantes = new ListaEnlazada<>();
        recursos = new ListaEnlazada<>();
        numEstudiantes = 0;
        numRecursos = 0;
    }


    public void agregarEstudiante(Estudiante estudiante) {
        if (numEstudiantes < maxEstudiantesPorCelda) {
            estudiantes.add(estudiante);
            numEstudiantes++;
        }
    }

    //public Recursos[] getRecursos(){
        //return recursos;
    //}
    public void agregarRecurso(Recursos recurso) {
        if (numRecursos < maxRecursosPorCelda) {
            recursos.add(recurso);
            numRecursos++;
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        //estudiantes.delete(estudiante);
    }

    public void eliminarRecurso(Recursos recurso) {
        //recursos.delete(recurso);
    }

   // public void evaluarMejoras() {
        //for (Recursos recurso : recursos) {
            //for (Estudiante estudiante : estudiantes) {
               // recurso.aplicarEfecto(estudiante);
            //}
        //}
    //}

    //public void evaluarReproduccion() {
        //if (numEstudiantes >= 2) {
            // Seleccionar dos estudiantes aleatoriamente para la reproducción
            //Random rand = new Random();
            //int indice1 = rand.nextInt(numEstudiantes);
            //int indice2 = rand.nextInt(numEstudiantes);
            //while (indice1 == indice2) {
                //indice2 = rand.nextInt(numEstudiantes);
            //}
            //Estudiante estudiante1 = estudiantes[indice1];
            //Estudiante estudiante2 = estudiantes[indice2];
            // Verificar si los estudiantes pueden reproducirse
            //if (estudiante1.puedeReproducirse() && estudiante2.puedeReproducirse()) {
                // Realizar la reproducción
                //if (rand.nextDouble() < estudiante1.getProbReproduccion() && rand.nextDouble() < estudiante2.getProbReproduccion()) {
                    // Se reproducen generando un nuevo estudiante
                   // Estudiante nuevoEstudiante = estudiante1.reproducirse(estudiante2);
                    // Agregar el nuevo estudiante a la celda
                   // agregarEstudiante(nuevoEstudiante);
                //}
            //}
        //}

    //public void evaluarClonacion() {
        //for (int i = 0; i < numEstudiantes; i++) {
            //Estudiante estudiante = estudiantes[i];
            //if (Math.random() < estudiante.getProbClonacion()) {
                // Clonar el estudiante
                //Estudiante clon = estudiante.clonar();
                // Agregar el clon a la celda
                //agregarEstudiante(clon);
            //}
        //}
    //}

    //public void evaluarDesaparicionEstudiantes() {
        //if (numEstudiantes > maxEstudiantesPorCelda) {
            // Eliminar estudiantes si hay más de los máximos permitidos por celda
            //for (int i = maxEstudiantesPorCelda; i < numEstudiantes; i++) {
                //eliminarEstudiante(estudiantes[i]);
            //}
        //}
    //}

    //private Recursos generarNuevoRecurso() {
        //Random rand = new Random();
        //double probabilidad = rand.nextDouble(); // Genera un número aleatorio entre 0 y 1

        // Calcula el tipo de recurso basado en las probabilidades
        //if (probabilidad < probAgua) {
            //return new Agua(rand.nextInt(), 2);
        //} else if (probabilidad < probAgua + probComida) {
            //return new Comida(rand.nextInt(), 10);
        //} else if (probabilidad < probAgua + probComida + probMontaña) {
            //return new Montaña(rand.nextInt(), 2);
        //} else if (probabilidad < probAgua + probComida + probMontaña + probTesoro) {
            //return new Tesoro(rand.nextInt(), 0.40);
        //} else if (probabilidad < probAgua + probComida + probMontaña + probTesoro + probBiblioteca) {
            //return new Biblioteca(rand.nextInt(), 0.25);
        //} else {
            //return new Pozo(generarNuevoRecurso().getTurnosRestantes());
        //}
    //}
}
