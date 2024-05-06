package com.example.demojavafx.zombieStudentsLife;

import com.example.demojavafx.entorno.Recursos;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demojavafx.estudiante.Estudiante;
import com.example.demojavafx.entorno.Agua;
import com.example.demojavafx.entorno.Comida;
import com.example.demojavafx.entorno.Montaña;
import com.example.demojavafx.entorno.Pozo;
import com.example.demojavafx.entorno.Biblioteca;
import com.example.demojavafx.entorno.Tesoro;
import com.example.demojavafx.excepciones.MasDe3Estudiantes;
import com.example.demojavafx.excepciones.MasDe3Recursos;

import java.util.Random;

import static com.example.demojavafx.zombieStudentsLife.Tablero.*;

public class Celda {
    private ListaEnlazada<Estudiante> listaEstudiantes;
    private ListaEnlazada<Recursos> listaRecursos;
    private int numEstudiantes;
    private int numRecursos;

    protected static final int maxEstudiantesPorCelda = 3;
    protected static final int maxRecursosPorCelda = 3;

    public Celda() {
        ListaEnlazada<Estudiante> listaEstudiantes = new ListaEnlazada<>();
        ListaEnlazada<Recursos> listaRecursos = new ListaEnlazada<>();
        numEstudiantes = 0;
        numRecursos = 0;
    }
    public Celda(ListaEnlazada<Estudiante> listaEstudiantes, ListaEnlazada<Recursos> listaRecursos){
        this.listaEstudiantes = listaEstudiantes;
        this.listaRecursos = listaRecursos;
        numEstudiantes = listaEstudiantes.getNumeroElementos();
        numRecursos = listaRecursos.getNumeroElementos();
    }


    public void agregarEstudiante(Estudiante estudiante) throws MasDe3Estudiantes {
        if (numEstudiantes < maxEstudiantesPorCelda) {
            listaEstudiantes.add(estudiante);
            numEstudiantes++;
        }else{
            throw new MasDe3Estudiantes(listaEstudiantes);
        }
    }
    public ListaEnlazada<Recursos> getListaRecursos(){
        return listaRecursos;
    }
    public void setListaRecursos(ListaEnlazada<Recursos> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }
    public ListaEnlazada<Estudiante> getListaEstudiantes(){
        return listaEstudiantes;
    }
    public void setListaEstudiantes(ListaEnlazada<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    public void agregarRecurso(Recursos recurso) throws MasDe3Recursos {
        if (numRecursos < maxRecursosPorCelda) {
            listaRecursos.add(recurso);
            numRecursos++;
        }else{
            throw new MasDe3Recursos(listaRecursos);
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        int pos = listaEstudiantes.getPosicion(new ElementoLE<>(estudiante));
        listaEstudiantes.delete(pos);
        numEstudiantes--;
    }

    public void eliminarRecurso(Recursos recurso) {
        int pos = listaRecursos.getPosicion(new ElementoLE<>(recurso));
        listaRecursos.delete(pos);
        numRecursos--;
    }
    public Estudiante obtenerEstudianteAleatorio() {
        if (!listaEstudiantes.isVacia()) {
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(listaEstudiantes.getNumeroElementos());
            return listaEstudiantes.getElemento(indiceAleatorio).getData();
        }
        return null;
    }
    public void eliminarEstudianteAleatorio() {
        if (!listaEstudiantes.isVacia()) {
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(listaEstudiantes.getNumeroElementos());
            listaEstudiantes.delete(indiceAleatorio);
        }
    }

   public void evaluarMejoras() {
       ElementoLE<Recursos> nodoRecurso = listaRecursos.getPrimero();
       while (nodoRecurso != null) {
           Recursos recurso = nodoRecurso.getData();
           ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
           while (nodoEstudiante != null) {
               Estudiante estudiante = nodoEstudiante.getData();
               recurso.aplicarEfecto(estudiante);
               nodoEstudiante = nodoEstudiante.getSiguiente();
           }
           nodoRecurso = nodoRecurso.getSiguiente();
       }
    }

    public void evaluarReproduccion() {
        if (numEstudiantes >= 2) {
            // Seleccionar dos estudiantes aleatoriamente para la reproducción
            Random rand = new Random();
            int indice1 = rand.nextInt(numEstudiantes);
            int indice2 = rand.nextInt(numEstudiantes);
            while (indice1 == indice2) {
                indice2 = rand.nextInt(numEstudiantes);
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
                        agregarEstudiante(nuevoEstudiante);
                    }
                }
            } catch (MasDe3Estudiantes e) {
                System.out.println("No se pudo agregar un nuevo estudiante: " + e.getMessage());
            }
        }
    }
    private Estudiante obtenerEstudianteEnIndice(int indice) {
        // Obtener el estudiante en la posición dada
        ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
        for (int i = 0; i < indice; i++) {
            nodoEstudiante = nodoEstudiante.getSiguiente();
        }
        return nodoEstudiante.getData();
    }

    public void evaluarClonacion() {
        ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
        while (nodoEstudiante != null) {
            Estudiante estudiante = nodoEstudiante.getData();
            if (Math.random() < estudiante.getProbClonacion()) {
                try {
                    // Clonar el estudiante
                    Estudiante clon = estudiante.clonar();
                    // Agregar el clon a la celda
                    agregarEstudiante(clon);
                } catch (MasDe3Estudiantes e) {
                    System.out.println("No se pudo clonar al estudiante: " + e.getMessage());
                }
            }
            nodoEstudiante = nodoEstudiante.getSiguiente();
        }
    }
    public void evaluarDesaparicionEstudiantes() {
        if (numEstudiantes > maxEstudiantesPorCelda) {
            // Eliminar estudiantes si hay más de los máximos permitidos por celda
            ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
            for (int i = 0; i < maxEstudiantesPorCelda; i++) {
                nodoEstudiante = nodoEstudiante.getSiguiente();
            }
            while (nodoEstudiante != null) {
                Estudiante estudiante = nodoEstudiante.getData();
                eliminarEstudiante(estudiante);
                nodoEstudiante = nodoEstudiante.getSiguiente();
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
}

