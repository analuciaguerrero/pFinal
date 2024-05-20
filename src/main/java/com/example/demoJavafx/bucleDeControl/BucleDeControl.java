package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.SeleccionarPartidaController;
import com.example.demoJavafx.entorno.*;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import com.example.demoJavafx.TableroController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static java.lang.Thread.sleep;

public class BucleDeControl implements Runnable {
    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);
    private DatosJuego dato;
    private ListaEnlazada<Estudiante> estudiantes;
    private ListaEnlazada<Recursos> recursos;
    private Tablero tablero;
    private Celda celda;
    private boolean turno;
    private final IntegerProperty turnoProperty = new SimpleIntegerProperty();
    public BucleDeControl(Tablero tablero, DatosJuego dato) {
        this.tablero = tablero;
        this.estudiantes = dato.getEstudiantes();
        this.recursos = dato.getRecursos();
        this.dato = dato;
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }

    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }

    public IntegerProperty getTurnoProperty() {
        return turnoProperty;
    }
    public void setTurnoProperty(Integer turnoProperty) {
        this.turnoProperty.set(turnoProperty);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public ListaEnlazada<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ListaEnlazada<Recursos> getRecursos() {
        return recursos;
    }

    public void setRecursos(ListaEnlazada<Recursos> recursos) {
        this.recursos = recursos;
    }

    public boolean isTurno() {
        return turno;
    }
    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public void actualizarTurnoProperty() {
        setTurnoProperty(dato.getTurnoActual());
    }

    public void evaluarMejoras() {
        if (!estudiantes.isVacia() && !recursos.isVacia()) {
            for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                Estudiante est = estudiantes.getElemento(i).getData();
                Celda celda = tablero.getCelda(est.getPosicionN(), est.getPosicionM());
                if (!celda.getListaRecursos().isVacia()) {
                    int numRec = celda.getListaRecursos().getNumeroElementos();
                    for (int j = 0; j != numRec; j++) {
                        Recursos rec = celda.getListaRecursos().getPrimero().getData();
                        if (est.isVivo()) {
                            rec.aplicarEfecto(est, celda, turnoProperty.get());
                            if (!est.isVivo()){
                                i -= 1;
                            }
                        }
                        celda.eliminarRecurso(rec);
                    }
                }
            }
        }
    }
    public void actualizarTiempoDeVidaEstudiante() {
        if (!estudiantes.isVacia()) {
            for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                Estudiante est = estudiantes.getElemento(i).getData();
                Celda celda = tablero.getCelda(est.getPosicionN(), est.getPosicionM());
                boolean estudianteMuerto = est.actualizarTiempoDeVida(celda, turnoProperty.get());
                if (estudianteMuerto) {
                    log.info("El estudiante " + est + " está muerto");
                    i -= 1;
                }
            }
        }
    }
    public void actualizarTiempoDeAparicionDeRecursos() {
        if (!recursos.isVacia()) {
            for (int i = 0; i != recursos.getNumeroElementos(); i++) {
                Recursos rec = recursos.getElemento(i).getData();
                boolean estudianteDesaparecido = rec.actualizarTurnos(dato, tablero.getCelda(rec.getPosicionN(), rec.getPosicionM()));
                if (estudianteDesaparecido) {
                    log.info("El recurso " + rec + " ha desaparecido");
                    i -= 1;
                }
            }
        }
    }
    public void moverEstudiantes() throws RecursosNoUtilizados {
        try {
            if (!estudiantes.isVacia()) {
                for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                    estudiantes.getElemento(i).getData().mover(dato, tablero);
                }
            }
        } catch (RecursosNoUtilizados e) {
            log.error("Se ha identificado una ruta vacía; existen recursos que no se han utilizado adecuadamente luego de que un estudiante alcanzara la celda.");
        }
    }
    public void evaluarReproduccion() {
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
                            boolean estudianteMuerto1 = estudiante.reproducirse(pareja, dato, celda, turnoProperty.get());
                            boolean estudianteMuerto2 = pareja.reproducirse(estudiante, dato, celda, turnoProperty.get());
                            if (estudianteMuerto1 || estudianteMuerto2) {
                                celda.eliminarEstudiante(estudiante);
                                celda.eliminarEstudiante(pareja);
                                log.info("Un estudiante o su pareja han muerto durante el proceso de reproducción en la celda [" + i + "][" + j + "].");
                            } else {
                                log.info("Reproducción exitosa entre el estudiante " + estudiante.getId() + " y su pareja en la celda [" + i + "][" + j + "].");
                            }
                        }
                    }
                }
            }
        }
    }
    public void evaluarClonacion(){
        if (!estudiantes.isVacia()) {
            int numEstudiantes = estudiantes.getNumeroElementos();
            for (int i = 0; i != numEstudiantes; i++) {
                Estudiante estudiante = estudiantes.getElemento(i).getData();
                Random a = new Random();
                int m = a.nextInt(101);
                if (m <= estudiante.getProbClonacion()) {
                    // Obtén la celda del estudiante
                    estudiante.clonar(dato, tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM()));
                }else {
                    log.error("No se puede clonar el estudiante, la celda está fuera del tablero.");
                }
            }
        }
    }
    public void evaluarDesaparicionEstudiantes() {
        if (!estudiantes.isVacia()) {
            for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                Estudiante est = estudiantes.getElemento(i).getData();
                Celda celda = tablero.getCelda(est.getPosicionN(), est.getPosicionM());
                int j = 1;
                while (celda.getListaEstudiantes().getNumeroElementos() > dato.getMaximoEstudiantesPorCelda()) {
                    for (int k = 0; k != celda.getListaEstudiantes().getNumeroElementos(); k++) {
                        Estudiante sobraEstudiante = celda.getListaEstudiantes().getElemento(k).getData();
                        if (celda.getListaEstudiantes().getNumeroElementos() > dato.getMaximoEstudiantesPorCelda() && sobraEstudiante.getTiempoDeVida() == j) {
                            celda.eliminarEstudiante(sobraEstudiante);
                            k -= 1;
                        }
                    }
                    j += 1;
                }
            }
        } else {
            log.info("No hay estudiantes en el tablero para evaluar su desaparición.");
        }
    }
    public void evaluarAparicionRecursos() {
        for (int i = 0; i != dato.getFilasDelTablero(); i++) {
            for (int j = 0; j != dato.getColumnasDelTablero(); j++) {
                Celda celda = tablero.getCelda(i, j);
                if (celda.getListaRecursos().getNumeroElementos() < dato.getMaximoRecursosPorCelda()) {
                    Random a = new Random();
                    int b = a.nextInt(1,100);

                    if (b <= dato.getProbRecurso()) {
                        int idRecurso;
                        if (dato.getHistorialRecursos().isVacia()) {
                            idRecurso = 1;
                        } else {
                            idRecurso = dato.getHistorialRecursos().getUltimo().getData().getId() + 1;
                        }
                        Random c = new Random();
                        int d = c.nextInt(1, (int) (dato.getProbAgua() + dato.getProbComida() + dato.getProbMontaña() + dato.getProbBiblioteca() + dato.getProbTesoro() + dato.getProbPozo()));
                        if (d <= dato.getProbAgua()) {
                            celda.agregarRecurso(new Agua(idRecurso, dato), true);
                        } else if (d <= dato.getProbAgua() + dato.getProbComida()) {
                            celda.agregarRecurso(new Comida(idRecurso, dato), true);
                        } else if (d <= dato.getProbAgua() + dato.getProbComida() + dato.getProbMontaña()) {
                            celda.agregarRecurso(new Montaña(idRecurso, dato), true);
                        } else if (d <= dato.getProbAgua() + dato.getProbComida() + dato.getProbMontaña() + dato.getProbBiblioteca()) {
                            celda.agregarRecurso(new Biblioteca(idRecurso, dato), true);
                        } else if (d <= dato.getProbAgua() + dato.getProbComida() + dato.getProbMontaña() + dato.getProbBiblioteca() + dato.getProbTesoro()) {
                            celda.agregarRecurso(new Tesoro(idRecurso, dato), true);
                        } else {
                            celda.agregarRecurso(new Pozo(idRecurso, dato), true);
                        }
                    }
                }
            }
        }
    }

    public void ejecuteBucle(){
        Platform.runLater(()-> {
            actualizarTiempoDeVidaEstudiante();
            actualizarTiempoDeAparicionDeRecursos();
            moverEstudiantes();
            evaluarMejoras();
            evaluarDesaparicionEstudiantes();
            evaluarReproduccion();
            evaluarClonacion();
            evaluarAparicionRecursos();
            dato.setTurnoActual(dato.getTurnoActual() + 1);
            turnoProperty.set(turnoProperty.get() + 1);
            log.debug("Ha pasado el turno " + turnoProperty.get());
        });
    }
    @Override
    public void run() {
        try {
            if (turno) {
                ejecuteBucle();
            } else {
                while (!dato.isPausado()) {
                    if (dato.getEstudiantes().getNumeroElementos() <= 1) {
                        dato.setPausado(true);
                        Platform.runLater(() -> TableroController.terminarPartida(dato));
                    } else {
                        ejecuteBucle();
                        sleep(1500);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Se ha interrumpido el bucle");
        }
    }

}
