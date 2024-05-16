package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.SeleccionarPartidaController;
import com.example.demoJavafx.entorno.*;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ElementoLS;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static java.lang.Thread.sleep;

public class BucleDeControl implements Runnable {
    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);
    private ListaEnlazada<Estudiante> estudiantes;
    private ListaEnlazada<Recursos> recursos;
    private DatosJuego dato;
    private Tablero tablero;
    private Celda celda;
    private boolean turno;
    private BucleDeControlProperties propiedades;
    public BucleDeControl(Tablero tablero, DatosJuego dato) {
        this.tablero = tablero;
        this.estudiantes = dato.getEstudiantes();
        this.recursos = dato.getRecursos();
        this.dato = dato;
    }
    public Tablero getTablero(){
        return tablero;
    }
    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
    public DatosJuego getDatos(){
        return dato;
    }
    public void setDatos(DatosJuego dato){
        this.dato = dato;
    }
    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }
    public Celda getCelda(int posicionN, int posicionM) {
        ElementoLE<ListaEnlazada<Celda>> nodoFila = tablero.getCeldas().getPrimero();
        while (nodoFila != null) {
            ListaEnlazada<Celda> filaCeldas = nodoFila.getData();
            ElementoLE<Celda> nodoCelda = filaCeldas.getPrimero();
            while (nodoCelda != null) {
                Celda celda = nodoCelda.getData();
                if (celda.getPosicionN() == posicionN && celda.getPosicionM() == posicionM) {
                    return celda;
                }
                nodoCelda = nodoCelda.getSiguiente();
            }
            nodoFila = nodoFila.getSiguiente();
        }
        return null; // Si no se encuentra la celda, devolver null
    }

    public void evaluarMejoras() {
        if (!estudiantes.isVacia() && !recursos.isVacia()) {
            for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                Estudiante estudiante = estudiantes.getElemento(i).getData();
                Celda celda = tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM());
                if (!celda.getListaRecursos().isVacia()) {
                    ListaEnlazada<Recursos> listaRecursos = celda.getListaRecursos();
                    ElementoLE<Recursos> nodoRecurso = listaRecursos.getPrimero();
                    while (nodoRecurso != null) {
                        Recursos recurso = nodoRecurso.getData();
                        if (recurso.actualizarTurnos(dato, celda)) { // Actualizar el tiempo restante del recurso
                            log.info("Recurso " + recurso.getTipo() + " ha sido eliminado de la celda " + celda.getPosicionN() + ", " + celda.getPosicionM());
                        } else {
                            // Aplicar efecto del recurso al estudiante si está vivo
                            if (estudiante.isVivo()) {
                                recurso.aplicarEfecto(estudiante, celda);
                                if (!estudiante.isVivo()){
                                    i -= 1;
                                } else{
                                    estudiante.getColaDeOperaciones().push(new ElementoLDE<>("mejora"));
                                }
                            }
                        }
                        nodoRecurso = nodoRecurso.getSiguiente();
                    }
                }
            }
        }
    }
    public void actualizarTiempoDeVidaEstudiante() {
        ElementoLE<ListaEnlazada<Celda>> nodoFila = tablero.getCeldas().getPrimero();
        while (nodoFila != null) {
            ListaEnlazada<Celda> filaCeldas = nodoFila.getData();
            ElementoLE<Celda> nodoCelda = filaCeldas.getPrimero();
            while (nodoCelda != null) {
                Celda celda = nodoCelda.getData();
                ListaEnlazada<Estudiante> listaEstudiantes = celda.getListaEstudiantes();
                ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
                int posicion = 1; // Posición del estudiante en la lista
                while (nodoEstudiante != null) {
                    Estudiante estudiante = nodoEstudiante.getData();
                    estudiante.actualizarTiempoDeVida(dato, celda); // Actualizar el tiempo de vida del estudiante
                    if (estudiante.getTiempoDeVida() <= 0) {
                        // Eliminar estudiante si su tiempo de vida llega a cero o menos
                        listaEstudiantes.delete(posicion);
                        log.info("Estudiante " + estudiante.getId() + " ha muerto y ha sido eliminado de la celda " + celda.getPosicion()[0] + ", " + celda.getPosicion()[1]);
                    } else {
                        log.info("Tiempo de vida del estudiante " + estudiante.getId() + " actualizado en la celda " + celda.getPosicion()[0] + ", " + celda.getPosicion()[1] + ". Tiempo restante: " + estudiante.getTiempoDeVida());
                        posicion++; // Incrementar la posición si no se elimina el estudiante
                    }
                    nodoEstudiante = nodoEstudiante.getSiguiente();
                }
                nodoCelda = nodoCelda.getSiguiente();
            }
            nodoFila = nodoFila.getSiguiente();
        }
    }
    public void actualizarTiempoDeAparicionDeRecursos() {
        if (!recursos.isVacia()) {
            for (int i = 0; i != recursos.getNumeroElementos(); i++) {
                Recursos recurso = recursos.getElemento(i).getData();
                boolean desaparecer = recurso.actualizarTurnos(dato, tablero.getCelda(recurso.getPosicionN(), recurso.getPosicionM()));
                if (desaparecer) {
                    log.info("El recurso " + recurso + " ha desaparecido");
                    i -= 1;
                }
            }
        }
    }
    public void moverEstudiantes() throws MasDe3Estudiantes,RecursosNoUtilizados {
        try {
            if (!estudiantes.isVacia()) {
                for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                    estudiantes.getElemento(i).getData().mover(dato, tablero);
                }
            }
        } catch (RecursosNoUtilizados | MasDe3Estudiantes e) {
            log.error("Se ha identificado una ruta vacía; existen recursos que no se han utilizado adecuadamente luego de que un estudiante alcanzara la casilla.");
        }
    }
    public void evaluarReproduccion() throws MasDe3Estudiantes {
        if (!estudiantes.isVacia()) {
            for (int i = 0; i != tablero.getFilas(); i++) {
                for (int j = 0; j != tablero.getColumnas(); j++) {
                    Celda celda = tablero.getCelda(i, j);
                    ListaEnlazada<Estudiante> estudianteListaEnlazada = celda.getListaEstudiantes();
                    int numEstudiantes = estudianteListaEnlazada.getNumeroElementos();
                    if (numEstudiantes >= 2 ) {
                        for (int m = 1 ; m < numEstudiantes; m += 2) {
                            Estudiante estudiante = estudiantes.getElemento(m - 1).getData();
                            Estudiante pareja = estudiantes.getElemento(m).getData();
                            boolean morir1 = estudiante.reproducirse(pareja, dato, celda);
                            boolean morir2 = pareja.reproducirse(estudiante, dato, celda);
                            if (morir1 || morir2) {
                                celda.eliminarEstudiante(estudiante);
                                celda.eliminarEstudiante(pareja);
                                log.info("Un estudiante o su pareja han muerto durante el proceso de reproducción en la celda [" + i + "][" + j + "].");
                            } else {
                                log.info("Reproducción exitosa entre el estudiante " + estudiante.getId() + " y su pareja en la celda [" + i + "][" + j + "].");
                            }
                            estudiante.getColaDeOperaciones().push(new ElementoLDE<>("reproducción"));
                            pareja.getColaDeOperaciones().push(new ElementoLDE<>("reproducción"));

                        }
                    }
                }
            }
        }
    }
    public void evaluarClonacion() throws MasDe3Estudiantes {
        if (!estudiantes.isVacia()) {
            int numEstudiantes = estudiantes.getNumeroElementos();
            for (int i = 0; i != numEstudiantes; i++) {
                Estudiante estudiante = estudiantes.getElemento(i).getData();
                Random a = new Random();
                int m = a.nextInt(101);
                if (m <= estudiante.getProbClonacion()) {
                    // Obtén la celda del estudiante
                    Celda celdaEstudiante = tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM());
                    if (celdaEstudiante != null) {
                        estudiante.clonar(dato, celdaEstudiante);
                        estudiante.getColaDeOperaciones().push(new ElementoLDE<>("clonación"));
                    } else {
                        log.error("No se puede clonar el estudiante, la celda está fuera del tablero.");
                    }
                }
            }
        }
    }
    public void evaluarDesaparicionEstudiantes() {
        if (!estudiantes.isVacia()) {
            for (int i = 0; i < tablero.getFilas(); i++) {
                for (int j = 0; j < tablero.getColumnas(); j++) {
                    Celda celda = tablero.getCelda(i, j);
                    ListaEnlazada<Estudiante> estudianteListaEnlazada = celda.getListaEstudiantes();
                    int numEstudiantes = estudianteListaEnlazada.getNumeroElementos();
                    int maxEstudiantesCelda = Math.min(numEstudiantes, dato.getMaximoEstudiantesPorCelda()); // Considerar el máximo de estudiantes permitido por celda
                    for (int m = 0; m < maxEstudiantesCelda; m++) {
                        Estudiante estudiante = estudianteListaEnlazada.getElemento(m).getData();
                        boolean muerte = estudiante.actualizarTiempoDeVida(dato, celda); // Llamar al método con la instancia de DatosJuego y Celda
                        if (muerte) {
                            celda.eliminarEstudiante(estudiante);
                            log.info("El estudiante " + estudiante.getId() + " ha muerto en la celda [" + i + "][" + j + "].");
                            m--; // Ajustar el índice para evitar saltar estudiantes después de eliminar uno.
                        }
                    }
                }
            }
        } else {
            log.info("No hay estudiantes en el tablero para evaluar su desaparición.");
        }
    }
    public Recursos generarNuevoRecurso(int id, int posicionN, int posicionM, DatosJuego dato) {
        Random rand = new Random();
        double probabilidad = rand.nextDouble(); // Genera una probabilidad aleatoria entre 0 y 1
        double probAgua = Agua.getProbAgua();
        double probComida = Comida.getProbComida();
        double probMontaña = Montaña.getProbMontaña();
        double probTesoro = Tesoro.getProbTesoro();
        double probBiblioteca = Biblioteca.getProbBiblioteca();
        if (probabilidad < probAgua) {
            log.info("Nuevo recurso generado: Agua");
            return new Agua(id, posicionN, posicionM, rand.nextInt(), probabilidad, dato.getAumentoVidaAgua(), probAgua);
        } else if (probabilidad < probAgua + probComida) {
            log.info("Nuevo recurso generado: Comida");
            return new Comida(id, posicionN, posicionM, rand.nextInt(), probabilidad, dato.getAumentoVidaComida(), probComida);
        } else if (probabilidad < probAgua + probComida + probMontaña) {
            log.info("Nuevo recurso generado: Montaña");
            return new Montaña(id, posicionN, posicionM, rand.nextInt(), probabilidad, dato.getReduccionVidaMontaña(), probMontaña);
        } else if (probabilidad < probAgua + probComida + probMontaña + probTesoro) {
            log.info("Nuevo recurso generado: Tesoro");
            return new Tesoro(id, posicionN, posicionM, rand.nextInt(), probabilidad, dato.getAumentoProbReproduccion(), probTesoro);
        } else if (probabilidad < probAgua + probComida + probMontaña + probTesoro + probBiblioteca) {
            log.info("Nuevo recurso generado: Biblioteca");
            return new Biblioteca(id, posicionN, posicionM, rand.nextInt(), probabilidad, dato.getAumentoProbClonacion(), probBiblioteca);
        } else { // El resto de la probabilidad se asigna a Pozo
            log.info("Nuevo recurso generado: Pozo");
            return new Pozo(generarNuevoRecurso(id, posicionN, posicionM, dato).getTurnosRestantes());
        }
    }

    public void evaluarAparicionRecursos() throws MasDe3Recursos {
        Random rand = new Random();
        int filas = dato.getFilasDelTablero();
        int columnas = dato.getColumnasDelTablero();
        int id = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                double probabilidad = rand.nextDouble();
                Recursos nuevoRecurso = generarNuevoRecurso(id++, i, j, dato);
                log.info("Aparición de nuevo recurso en la celda ({}, {}): {}", i, j, nuevoRecurso);

                // Insertar el nuevo recurso en la lista de recursos de la celda
                Celda celda = tablero.getCelda(i, j);
                ListaEnlazada<Recursos> listaRecursos = celda.getListaRecursos();
                if (listaRecursos.getNumeroElementos() < dato.getMaximoRecursosPorCelda()) {
                    listaRecursos.insertarFinal(new ElementoLE<>(nuevoRecurso));
                } else {
                    throw new MasDe3Recursos(listaRecursos);
                }
            }
        }
    }
    public boolean condicionFinalizacion(){
        ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();
        log.info("Evaluando condición de finalización del juego...");
        for(int i=0;i<tablero.getFilas();i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                ListaEnlazada<Estudiante> listaEstudiantes = tablero.getCelda(i, j).getListaEstudiantes();
                int numEstudiantes = listaEstudiantes.getNumeroElementos();
                log.info("Procesando celda ({}, {}), número de estudiantes: {}", i, j, numEstudiantes);
                for (int k = 0; k < numEstudiantes; k++) {
                    Estudiante estudiante = listaEstudiantes.getElemento(k).getData();
                    lista.add(new ElementoLE<>(estudiante));
                    log.info("Añadido estudiante a la lista de finalización: {}", estudiante);
                }
            }
        }
        int totalEstudiantes = lista.getNumeroElementos();
        log.info("Número total de estudiantes en el tablero: {}", totalEstudiantes);
        boolean finalizacion = totalEstudiantes == 1;
        log.info("Condición de finalización: {}", finalizacion ? "verdadera" : "falsa");
        return finalizacion;
    }
    public void ejecutarBucle() throws MasDe3Estudiantes, MasDe3Recursos {
        while (!condicionFinalizacion()) {
            actualizarTiempoDeVidaEstudiante();
            actualizarTiempoDeAparicionDeRecursos();
            moverEstudiantes();
            evaluarMejoras();
            evaluarDesaparicionEstudiantes();
            evaluarReproduccion();
            evaluarClonacion();
            evaluarAparicionRecursos();
            dato.setTurnoActual(dato.getTurnoActual() + 1);
            propiedades.getTurnoProperty().set(propiedades.getTurnoProperty().get() + 1);
        }
    }
    @Override
    public void run() {
        try {
            if (turno) {
                ejecutarBucle();
            } else {
                while (!dato.isPausado()) {
                    ejecutarBucle();
                    sleep(1500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("El bucle ha sido interrumpido mientras esperaba");
        }
    }

}
