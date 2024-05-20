package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.bucleDeControl.BucleDeControl;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.Nodo;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Grafo;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Mapa;
import com.example.demoJavafx.estructurasDeDatos.Grafo.NodoGrafo;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.PadresException;
import com.example.demoJavafx.tablero.Tablero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZombieStudentsLife {
    private static final Logger log = LogManager.getLogger();
    private DatosJuego dato;
    private Tablero tablero;
    private BucleDeControl bucle;
    private Mapa<Estudiante, BST<Estudiante>> arbolGenealogico = new Mapa<>();
    private Grafo<String> grafoDeOperaciones = new Grafo<>();

    public ZombieStudentsLife(DatosJuego dato, boolean selec) {
        this.dato = dato;
        dato.setZombieStudentsLife(this);
        if (!selec) {
            tablero = new Tablero(dato.getFilasDelTablero(), dato.getColumnasDelTablero(), dato);
            bucle = new BucleDeControl(tablero, dato);
        }    }
    public ZombieStudentsLife(DatosJuego dato, Tablero tablero) {
        this.dato = dato;
        dato.setZombieStudentsLife(this);
        bucle = new BucleDeControl(tablero, dato);
    }
    public DatosJuego getDato() {
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public BucleDeControl getBucle() {
        return bucle;
    }

    public void setBucle(BucleDeControl bucle) {
        this.bucle = bucle;
    }

    public Mapa<Estudiante, BST<Estudiante>> getArbolGenealogico() {
        return arbolGenealogico;
    }
    public Grafo<String> getGrafoDeOperaciones() {
        return grafoDeOperaciones;
    }

    public void start(boolean turno) {
        if (turno) {
            bucle.setTurno(true);
        } else {
            bucle.setTurno(false);
        }
        Thread threadBucle = new Thread(bucle);
        threadBucle.start();
    }
    private void addPadres (Nodo<Estudiante> hijo) {
        try {
            if (hijo.getDato().getPadres() != null) {
                if (hijo.getDato().getPadres().getNumeroElementos() != 2) throw new PadresException();
                hijo.setDerecha(new Nodo<>(hijo.getDato().getPadres().getPrimero().getData()));
                hijo.setIzquierda(new Nodo<>(hijo.getDato().getPadres().getElemento(1).getData()));
                addPadres(hijo.getDerecha());
                addPadres(hijo.getIzquierda());
            }
        } catch (PadresException e) {
            log.error("Se ha producido una excepción ya que el número de padres es distinto de 2");
        }
    }
    private Mapa<Estudiante, BST<Estudiante>> crearArbolGenealogico () {
        Mapa<Estudiante, BST<Estudiante>> arbolGenealogico = new Mapa<>();
        int totalEstudiantes = dato.getEstudiantes().getNumeroElementos();
        for (int i = 0; i != totalEstudiantes; i ++) {
            Estudiante estudiante = dato.getEstudiantes().getElemento(i).getData();
            BST<Estudiante> arbolGenealogico2 = new BST<>(estudiante);
            addPadres(arbolGenealogico2.getRaiz());
            arbolGenealogico.put(estudiante, arbolGenealogico2);
        }
        return arbolGenealogico;
    }

    private Grafo<String> crearGrafoDeOperaciones () {
        Grafo<String> grafoDeOperaciones = new Grafo<>(false);
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Nacer"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Morir"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Agua"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Biblioteca"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Comida"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Montaña"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Tesoro"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Pozo"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("ActualizarTiempoDeVida"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("ActualizarProbabilidadReproducción"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("ActualizarProbabilidadClonación"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Moverse"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Reproducirse"));
        grafoDeOperaciones.addNodo(new NodoGrafo<>("Clonarse"));
        NodoGrafo<String> nodoEstudiantes= new NodoGrafo<>("Estudiantes");
        grafoDeOperaciones.addNodo(nodoEstudiantes);
        try {
            int totalEstudiantes = dato.getHistorialEstudiantes().getNumeroElementos();
            for (int i = 0; i != totalEstudiantes; i++) {
                Estudiante estudiante = dato.getHistorialEstudiantes().getElemento(i).getData();
                NodoGrafo<String> nodoEstudiante = new NodoGrafo<>(STR."Estudiante \{estudiante.getId()}");
                grafoDeOperaciones.addArista(1, nodoEstudiante, nodoEstudiantes);
                Cola<String> operacionesEstudiante = estudiante.getColaDeOperaciones();
                int numOperaciones = operacionesEstudiante.getNumeroDatos();
                Cola<String> colaAux = new Cola<>();
                for (int j = 0; j != numOperaciones; j++) {
                    String accionActual = operacionesEstudiante.poll();
                    colaAux.add(accionActual);
                    NodoGrafo<String> operacionNodo = new NodoGrafo<>(accionActual);
                    grafoDeOperaciones.addArista(1, operacionNodo, nodoEstudiante);
                    int indexOfFinOperacion = Math.min(accionActual.indexOf(","), accionActual.substring(8).indexOf(" ") + 8);
                    String accionReducida = accionActual.substring(8, indexOfFinOperacion);
                    grafoDeOperaciones.addArista(1, operacionNodo, grafoDeOperaciones.getNodoGrafo(accionReducida));
                    int indexOfInicioTurno = accionActual.indexOf("turno:") + 7;
                    String turno = accionActual.substring(indexOfInicioTurno);
                    NodoGrafo<String> nodoTurno = grafoDeOperaciones.getNodoGrafo(STR."Turno \{turno}");
                    if (nodoTurno == null) {
                        grafoDeOperaciones.addNodo(new NodoGrafo<>(STR."Turno \{turno}"), operacionNodo, 1);
                    } else {
                        grafoDeOperaciones.addArista(1, operacionNodo, nodoTurno);
                    }
                }
                for (int k = 0; k != numOperaciones; k++) {
                    operacionesEstudiante.add(colaAux.poll());
                }
            }
        } catch (Exception e) {
            log.error("No se ha hallado un nodo para enlazar la operación");
            e.printStackTrace();
        }
        return grafoDeOperaciones;
    }
    public void informacion () {
        arbolGenealogico = crearArbolGenealogico();
        grafoDeOperaciones = crearGrafoDeOperaciones();
    }
    public void finalizarPartida () {
        informacion();
    }
}