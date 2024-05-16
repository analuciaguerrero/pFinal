package com.example.demoJavafx.entorno;
import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Recursos {
    private static final Logger log = LogManager.getLogger();
    @Expose
    private int id;
    @Expose
    private int posicionN;
    @Expose
    private int posicionM;
    @Expose
    private int turnosRestantes;
    @Expose
    private double probRecurso;
    @Expose (serialize = false)
    private IntegerProperty TurnosRestantesProperty = new SimpleIntegerProperty();

    public Recursos(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso) {
        this.id = id;
        this.posicionN = posicionN;
        this.posicionM = posicionM;
        this.turnosRestantes = turnosRestantes;
        this.probRecurso = probRecurso;
    }
    public Recursos(int id, int posicionN, int posicionM, int turnosRestantes){
        this.id = id;
        this.posicionN = posicionN;
        this.posicionM = posicionM;
        this.turnosRestantes = turnosRestantes;
    }
    public Recursos(int id, int posicionN, int posicionM, DatosJuego dato) {
        this.id = id;
        this.posicionN = posicionN;
        this.posicionM = posicionM;
        this.turnosRestantes = dato.getTurnosIniciales();
    }
    public Recursos(){}
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getPosicionN(){
        return posicionN;
    }
    public void setPosicionN(int posicionN){
        this.posicionN = posicionN;
    }
    public int getPosicionM(){
        return posicionM;
    }
    public void setPosicionM(int posicionM){
        this.posicionM = posicionM;
    }
    public int[] getPosicion () {
        int[] posicion = new int[2];
        posicion[0] = posicionN;
        posicion[1] = posicionM;
        return posicion;
    }
    public IntegerProperty getTurnosRestantesProperty () {
        return TurnosRestantesProperty;
    }

    public void setTurnosRestantesProperty(IntegerProperty turnosRestantesProperty) {
        this.TurnosRestantesProperty = turnosRestantesProperty;
    }

    public void actualizarTurnosRestantesProperty () {
        TurnosRestantesProperty.set(turnosRestantes);
    }


    public void setPosicion (int[] posicion) throws TamañoArrayInvalido {
        try {
            if (posicion.length != 2) throw new TamañoArrayInvalido();
            posicionN = posicion[0];
            posicionM = posicion[1];
        } catch (TamañoArrayInvalido e) {
            log.error("El array no contiene 2 elementos y por tanto, no se ha podido establecer la posición del recurso");
        }
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }
    public abstract Class<?> getTipo ();

    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }
    public double getProbRecurso() {
        return probRecurso;
    }

    public void setProbRecurso(double probRecurso) {
        this.probRecurso = probRecurso;
    }
    public void add(DatosJuego dato, Celda celda) {
        try {
            dato.getRecursos().add(this);
            celda.getListaRecursos().add(this);
            this.setPosicion(celda.getPosicion());

            Constructor<? extends Recursos> constructor = getClass().getConstructor(int.class, int.class, int.class, DatosJuego.class);
            dato.getHistorialRecursos().add(constructor.newInstance(id, posicionN, posicionM, dato));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            log.error("No se ha podido crear una nueva instancia de recursos para el historial de recursos");
        }
    }
    public void del(DatosJuego dato, Celda celda) {
        ListaEnlazada<Recursos> listaRecursos = dato.getRecursos();
        listaRecursos.del(this);
        celda.getListaRecursos().del(this);
    }
    public boolean actualizarTurnos(DatosJuego dato, Celda celda){
        turnosRestantes -= 1;
        if (turnosRestantes == 0) {
            del(dato, celda);
            return true;
        }
        return false;
    }

    public abstract void aplicarEfecto(Estudiante estudiante, Celda celda);
}
