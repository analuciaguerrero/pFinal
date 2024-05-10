package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import java.util.Random;
import static com.example.demoJavafx.zombieStudentsLife.Tablero.*;


public class Celda {
    private int x;
    private int y;
    private ListaEnlazada<Estudiante> listaEstudiantes;
    private ListaEnlazada<Recursos> listaRecursos;
    private int numEstudiantes;
    private int numRecursos;
    private boolean ocupada;


    public Celda() {
        this.listaEstudiantes = new ListaEnlazada<>();
        this.listaRecursos = new ListaEnlazada<>();
        numEstudiantes = 0;
        numRecursos = 0;
    }

    public Celda(ListaEnlazada<Estudiante> listaEstudiantes, ListaEnlazada<Recursos> listaRecursos) {
        this.listaEstudiantes = listaEstudiantes;
        this.listaRecursos = listaRecursos;
        numEstudiantes = listaEstudiantes.getNumeroElementos();
        numRecursos = listaRecursos.getNumeroElementos();
    }

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.ocupada = false;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public boolean estaOcupada(){
        return ocupada;
    }
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }


    public void agregarEstudiante(Estudiante estudiante) throws MasDe3Estudiantes {
        if (numEstudiantes < maxEstudiantesPorCelda) {
            listaEstudiantes.add(estudiante);
            numEstudiantes++;
        } else {
            throw new MasDe3Estudiantes(listaEstudiantes);
        }
    }

    public ListaEnlazada<Recursos> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(ListaEnlazada<Recursos> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public ListaEnlazada<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ListaEnlazada<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public void agregarRecurso(Recursos recurso) throws MasDe3Recursos {
        if (numRecursos < maxRecursosPorCelda) {
            listaRecursos.add(recurso);
            numRecursos++;
        } else {
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
}

