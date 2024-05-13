package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import static com.example.demoJavafx.zombieStudentsLife.Tablero.*;


public class Celda {
    private int posicionN;
    private int posicionM;
    private ListaEnlazada<Estudiante> listaEstudiantes;
    private ListaEnlazada<Recursos> listaRecursos;
    private DatosJuego dato;
    private Tablero tablero;
    private Button botonCasilla = new Button();
    private GridPane gridElementos = new GridPane();
    private static final Logger log = LogManager.getLogger(Celda.class);

    public Celda() {
        this.listaEstudiantes = new ListaEnlazada<>();
        this.listaRecursos = new ListaEnlazada<>();
    }
    public Celda(int posicionN, int posicionM) {
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        this.listaEstudiantes = new ListaEnlazada<>();
        this.listaRecursos = new ListaEnlazada<>();
    }
    public Celda(int posicionN, int posicionM, ListaEnlazada<Estudiante> listaEstudiantes, ListaEnlazada<Recursos> listaRecursos) {
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        this.listaEstudiantes = listaEstudiantes;
        this.listaRecursos = listaRecursos;
    }

    public Celda(ListaEnlazada<Estudiante> listaEstudiantes, ListaEnlazada<Recursos> listaRecursos) {
        this.listaEstudiantes = listaEstudiantes;
        this.listaRecursos = listaRecursos;
    }
    public int getPosicionN() {
        return posicionN;
    }

    public void setPosicionN(int posicionN) {
        this.posicionN = posicionN;
    }

    public int getPosicionM() {
        return posicionM;
    }

    public void setPosicionM(int posicionM) {
        this.posicionM = posicionM;
    }
    public int[] getPosicion () {
        return new int[]{posicionN, posicionM};
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
    public void agregarEstudiante(Estudiante estudiante) throws MasDe3Estudiantes {
        try{
            if (dato.get < dato.getMaximoEstudiantesPorCelda()) {
                listaEstudiantes.add(estudiante);
                numEstudiantes++;
            } else {
                throw new MasDe3Estudiantes(listaEstudiantes);
            }
        } catch(MasDe3Estudiantes e){
            log.error("Se ha intentado añadir más estudiantes de los permitidos");
        }
    }

    public void agregarRecurso(Recursos recurso) throws MasDe3Recursos {
        try{
            if (numRecursos < maxRecursosPorCelda) {
                listaRecursos.add(recurso);
                numRecursos++;
            } else {
                throw new MasDe3Recursos(listaRecursos);
            }
        } catch(MasDe3Recursos e){
            log.error("Se ha intentado añadir más recursos de los permitidos");
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        int pos = listaEstudiantes.getPosicion(new ElementoLE<>(estudiante));
        listaEstudiantes.delete(pos);
        numEstudiantes--;
        log.debug("Se ha querido eliminar un estudiante de una casilla en la que no estaba");
    }

    public void eliminarRecurso(Recursos recurso) {
        int pos = listaRecursos.getPosicion(new ElementoLE<>(recurso));
        listaRecursos.delete(pos);
        numRecursos--;
        log.debug("Se ha querido eliminar un recurso de una casilla en la que no estaba");
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

