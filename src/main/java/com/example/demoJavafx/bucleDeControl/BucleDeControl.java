package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.XMenuPrincipalController;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import com.example.demoJavafx.zombieStudentsLife.Tablero;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;

public class BucleDeControl implements Runnable {
    private static final Logger log = LogManager.getLogger(BucleDeControl.class);
    private ListaEnlazada<Estudiante> estudiantes;
    private ListaEnlazada<Recursos> recursos;
    private DatosJuego dato;
    private Tablero tablero;
    private boolean turno;
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
    private void evaluarMejoras() {
        if (!estudiantes.isVacia() && !recursos.isVacia()) {
            for (int i = 0; i != estudiantes.getNumeroElementos(); i++) {
                Estudiante estudiante = estudiantes.getElemento(i).getData();
                Celda celda = tablero.getCelda(estudiante.getPosicion());
                if (!celda.getListaRecursos().isVacia()) {
                    int numeroRecursos = celda.getListaRecursos().getNumeroElementos();
                    for (int j = 0; j != numeroRecursos; j++) {
                        Recursos recurso = celda.getListaRecursos().getPrimero().getData();
                        if (estudiante.isVivo()) {
                            recurso.aplicarEfecto(estudiante, celda);
                            if (!estudiante.isVivo()) i -= 1;
                        }
                        celda.eliminarRecurso(recurso);
                    }
                }
            }
        }
    }
    public void actualizarTiempoDeVidaEstudiante() {
        ElementoLE<Celda> nodoCelda = tablero.getCelda().getPrimero();
        while (nodoCelda != null) {
            Celda celda = nodoCelda.getData();
            ListaEnlazada<Estudiante> listaEstudiantes = celda.getListaEstudiantes();
            ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
            int posicion = 1; // Posición del estudiante en la lista
            while (nodoEstudiante != null) {
                Estudiante estudiante = nodoEstudiante.getData();
                estudiante.actualizarTiempoDeVida(dato, celda); // Pasar los datos de juego y la celda
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
    }

    private void ejecutarBucle() {
        Platform.runLater(() -> {
            turnoProperty.set(turnoProperty.get() + 1);
            model.setTurno(model.getTurno() + 1);
            actualizarTVIndividuos();
            actualizarTARecursos();
            moverIndividuos();
            evaluarMejoras();
            evaluarReproduccion();
            evaluarClonacion();
            evaluarDesaparicionIndividuos();
            evaluarAparicionDeRecursos();
            log.debug("Ha pasado el turno " + turnoProperty.get());
        });
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
    public boolean condicionFinalizacion(){
        ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();
        for(int i=0;i<tablero.getFilas();i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                for (int k = 0; k < tablero.getCelda(i, j).getListaEstudiantes().getNumeroElementos(); k++) {
                    lista.add(new ElementoLE<>(tablero.getCelda(i, j).getListaEstudiantes().getElemento(k).getData()));
                }
            }
        }
        return lista.getNumeroElementos() == 1;
    }

}
