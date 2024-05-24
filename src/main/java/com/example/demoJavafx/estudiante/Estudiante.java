package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import com.google.gson.annotations.Expose;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Estudiante {
    @Expose
    private int posicionN;
    @Expose
    private int posicionM;
    @Expose
    private int id;
    @Expose (serialize = false)
    private IntegerProperty tiempoDeVidaProperty = new SimpleIntegerProperty();
    @Expose
    private int generacion;
    @Expose
    private int tiempoDeVida;
    @Expose
    private double probReproduccion;
    @Expose
    private double probClonacion;
    @Expose
    private double probMuerte;
    @Expose
    private boolean isVivo = true;
    @Expose
    private Cola<String> colaDeOperaciones = new Cola<>();
    @Expose (serialize = false)
    private ListaSimple<Estudiante> padres = new ListaSimple<>(2);
    private static final Logger log = LogManager.getLogger();
    public Estudiante(int id, int posicionN, int posicionM, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, int turno) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = 100-probReproduccion;
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        if (probReproduccion < 0 || probReproduccion > 100 || probClonacion < 0 || probClonacion > 100) throw new ProbabilidadNoValida();
        actualizarTiempoDeVidaProperty();
        colaDeOperaciones.add(STR."Acción: nacer, turno: \{turno}");
        log.debug(STR."Ha nacido el estudiante \{id}");
    }

    public Estudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, int turno) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = 100-probReproduccion;
        if (probReproduccion < 0 || probReproduccion > 100 || probClonacion < 0 || probClonacion > 100) throw new ProbabilidadNoValida();
        actualizarTiempoDeVidaProperty();
        colaDeOperaciones.add(STR."Acción: nacer, turno: \{turno}");
        log.debug(STR."Ha nacido el estudiante \{id}");
    }
    public Estudiante(){}
    public Estudiante(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.posicionN = estudiante.getPosicionN();
        this.posicionM = estudiante.getPosicionM();
        this.generacion = estudiante.getGeneracion();
        this.tiempoDeVida = estudiante.getTiempoDeVida();
        this.probReproduccion = estudiante.getProbReproduccion();
        this.probClonacion = estudiante.getProbClonacion();
        this.probMuerte = 100 - probReproduccion;
        actualizarTiempoDeVidaProperty();
        this.isVivo = estudiante.isVivo();
        this.colaDeOperaciones = estudiante.getColaDeOperaciones();
        log.debug(STR."Se ha creado una copia del estudiante \{id}");
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
        int[] posicion = new int[2];
        posicion[0] = posicionN;
        posicion[1] = posicionM;
        return posicion;
    }
    public void setPosicion(int[] posicion) throws TamañoArrayInvalido{
        try {
            if (posicion.length != 2) throw new TamañoArrayInvalido();
            posicionN = posicion[0];
            posicionM = posicion[1];
        } catch (TamañoArrayInvalido e) {
            log.error("El array no contiene 2 elementos y por tanto, no se ha podido establecer la posición del estudiante");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        log.info("El Id se ha modificado");
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
        log.info("La generación ha sido modificada");
    }

    public int getTiempoDeVida() {
        return tiempoDeVida;
    }

    public void setTiempoDeVida(int tiempoDeVida, int turno) {
        this.tiempoDeVida = tiempoDeVida;
        actualizarTiempoDeVidaProperty();
        colaDeOperaciones.add(STR."Acción: actualizarTV (\{getTiempoDeVida()}), turno: \{turno}");
        log.info("El tiempo de vida se ha modificado");
    }

    public double getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(double probReproduccion, int turno) {
        this.probReproduccion = probReproduccion;
        this.probMuerte = 100 - probReproduccion;
        if (probReproduccion < 0 || probReproduccion > 100) throw new ProbabilidadNoValida();
        colaDeOperaciones.add(STR."Acción: actualizar la probabilidad de reproducción (\{getProbReproduccion()}), turno: \{turno}");
        log.info("La probabilidad de reproducción ha sido modificada");
    }

    public double getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(double probClonacion, int turno) {
        this.probClonacion = probClonacion;
        if (probClonacion < 0 || probClonacion > 100) throw new ProbabilidadNoValida();
        colaDeOperaciones.add(STR."Acción: actualizar la probabilidad de clonación (\{getProbClonacion()}), turno: \{turno}");
        log.info("La probabilidad de clonación ha sido modificada");
    }

    public double getProbMuerte() {
        return probMuerte;
    }

    public IntegerProperty getTiempoDeVidaProperty() {
        return tiempoDeVidaProperty;
    }

    public void setTiempoDeVidaProperty(IntegerProperty tiempoDeVidaProperty) {
        this.tiempoDeVidaProperty = tiempoDeVidaProperty;
    }
    public void addOperacion(String operacion) {
        colaDeOperaciones.add(operacion);
    }
    public ListaSimple<Estudiante> getPadres() {
        return padres;
    }
    public void setPadres(Estudiante padre1, Estudiante padre2) {
        this.padres.setElemento(0, padre1);
        this.padres.setElemento(1, padre2);
    }
    protected void setPadres(ListaSimple<Estudiante> padres) {
        this.padres = padres;
    }
    public void actualizarTiempoDeVidaProperty () {
        tiempoDeVidaProperty.set(tiempoDeVida);
    }

    public boolean isVivo(){
        return isVivo;
    }
    public abstract Class<?> getTipo();
    public int getNumTipo() {
        int num = -1;
        switch (this.getClass().getSimpleName()) {
            case "EstudianteBasico":
                num = 0;
                break;
            case "EstudianteNormal":
                num = 1;
                break;
            case "EstudianteAvanzado":
                num = 2;
                break;
            default:
                log.error("El grado del tipo no es ninguno de los requeridos");
        }
        return num;
    }
    private double getProbMejorar(Estudiante estudiante, DatosJuego dato) {
        double probMejora = -1;
        switch (estudiante.getClass().getSimpleName()) {
            case "EstudianteNormal":
                probMejora = dato.getProbMejorarANormal();
                break;
            case "EstudianteAvanzado":
                probMejora = dato.getProbMejorarAAvanzado();
                break;
            default:
                log.error("El nombre del estudiante introducido no es válido");
        }
        return probMejora;
    }
    public void add(DatosJuego dato, Celda celda) {
        try {
            dato.getEstudiantes().add(this);
            celda.getListaEstudiantes().add(this);
            this.setPosicion(celda.getPosicion());
            Constructor<? extends Estudiante> constructor = getClass().getConstructor(Estudiante.class);
            dato.getHistorialEstudiantes().add(constructor.newInstance(this));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            log.error("No se ha podido crear una nueva instancia del estudiante para el historial de estudiantes");
        }
    }

    public void morir(DatosJuego dato, Celda celda) {
        celda.getListaEstudiantes().del(this);
        dato.getEstudiantes().del(this);
        isVivo = false;
        colaDeOperaciones.add(STR."Acción: morir, turno: \{dato.getTurnoActual()}");
        log.debug(STR."El estudiante \{this.getId()} ha muerto");
    }
    public abstract void mover(DatosJuego dato, Tablero tablero);
    protected void cambiarDePosicion (int nuevaPosicionN, int nuevaPosicionM, Tablero tablero) {
        Celda celdaNueva = tablero.getCelda(nuevaPosicionN, nuevaPosicionM);
        Celda celda = tablero.getCelda(getPosicionN(), getPosicionM());
        celda.getListaEstudiantes().del(this);
        celda.restablecerInterfazVisual();
        setPosicionN(nuevaPosicionN);
        setPosicionM(nuevaPosicionM);
        celdaNueva.getListaEstudiantes().add(this);
        celdaNueva.restablecerInterfazVisual();
    }
    protected void moverseAleatorio(Tablero tablero, int turno) {
        log.info("Inicio de un movimiento aleatorio");
        Random a = new Random();
        int movimiento = a.nextInt(1, 8);
        try {
            switch (movimiento) {
                case 1:
                    cambiarDePosicion(getPosicionN() + 1, getPosicionM(), tablero);
                    break;
                case 2:
                    cambiarDePosicion(getPosicionN() + 1, getPosicionM() - 1, tablero);
                    break;
                case 3:
                    cambiarDePosicion(getPosicionN(), getPosicionM() - 1, tablero);
                    break;
                case 4:
                    cambiarDePosicion(getPosicionN() - 1, getPosicionM() - 1, tablero);
                    break;
                case 5:
                    cambiarDePosicion(getPosicionN() - 1, getPosicionM(), tablero);
                    break;
                case 6:
                    cambiarDePosicion(getPosicionN() - 1, getPosicionM() + 1, tablero);
                    break;
                case 7:
                    cambiarDePosicion(getPosicionN(), getPosicionM() + 1, tablero);
                    break;
                case 8:
                    cambiarDePosicion(getPosicionN() + 1, getPosicionM() + 1, tablero);
                    break;
                default:
                    log.error("Se ha intentado hacer un movimiento aleatorio inválido");
            }
            this.getColaDeOperaciones().add(STR."Acción: moverse (\{getPosicionN()}, \{getPosicionM()}), turno: \{turno}");
            log.debug(STR."El estudiante se ha movido a \{getPosicionN()}, \{getPosicionM()}");
        } catch (IndexOutOfBoundsException e) {
            moverseAleatorio(tablero, turno);
        }
    }
    public <TipoDeDatos extends Estudiante> boolean reproducirse (Estudiante pareja, DatosJuego dato, Celda celda, int turno) {
        int num = getNumTipo();
        int numPareja = pareja.getNumTipo();
        Random a = new Random();
        int l = a.nextInt(1, 100);
        if (l <= probReproduccion) {
            double probMejora;
            Estudiante estudiante1 = this;
            Estudiante estudiante2 =  pareja;

            if (num > numPareja) {
                probMejora = getProbMejorar(this, dato);
            } else if (num < numPareja) {
                probMejora = getProbMejorar(pareja, dato);
                estudiante1 = pareja;
                estudiante2 = this;
            } else {
                probMejora = 100;
            }
            Random b = new Random();
            int m = b.nextInt(1, 100);
            Class<?> hijoTipo;
            if (m <= probMejora) {
                hijoTipo = estudiante1.getTipo();
            } else {
                hijoTipo = estudiante2.getTipo();
            }
            try {
                Constructor<?> constructor = hijoTipo.getConstructor(int.class, int.class, int.class, int.class, int.class, double.class, double.class, int.class);
                int id = dato.getHistorialEstudiantes().getUltimo().getData().getId() + 1;
                TipoDeDatos hijo = (TipoDeDatos) constructor.newInstance(id, getPosicionN(), getPosicionM(), this.getGeneracion()+1, dato.getTurnosVidaIniciales(), dato.getProbReproduccionEstudiante(), dato.getProbClonacionEstudiante(), dato.getTurnoActual());
                hijo.setPadres(this, pareja);
                hijo.add(dato, celda);
                colaDeOperaciones.add(STR."Acción: reproducirse (con estudiante\{pareja.getId()}), turno: \{turno}");
                log.debug(STR."El estudiante \{this} se ha reproducido con el estudiante \{pareja.getId()}");
                return false; //No mueren
            } catch (Exception e) {
                log.error("No se ha podido crear una instancia para el estudiante hijo");
                return false; //No mueren
            }
        } else {
            return true; //Sí mueren
        }
    }
    public void clonar(DatosJuego dato, Celda celda) {
        try {
            Constructor<? extends Estudiante> constructor = getClass().getConstructor(Estudiante.class);
            Estudiante clon = constructor.newInstance(this);
            clon.setId(dato.getHistorialEstudiantes().getUltimo().getData().getId() + 1);
            colaDeOperaciones.add(STR."Acción: clonarse, turno: \{dato.getTurnoActual()}");
            log.debug(STR."El estudiante \{this.getId()} se ha clonado");
            clon.add(dato, celda);
            clon.getColaDeOperaciones().add(STR."Acción: nacer, turno: \{dato.getTurnoActual()}");
            log.debug(STR."El estudiante \{clon.getId()} ha nacido");
        } catch (Exception e) {
            log.error("No se ha podido crear un clon del estudiante");
        }
    }
    public boolean actualizarTiempoDeVida(Celda celda, int turno){
        tiempoDeVida-=1;
        actualizarTiempoDeVidaProperty();
        probReproduccion -= 10;
        if (probReproduccion < 0) probReproduccion = 0;
        probClonacion -= 10;
        if (probClonacion < 0) probClonacion = 0;
        colaDeOperaciones.add(STR."Acción: actualizar tiempo de vida (\{getTiempoDeVida()}), turno: \{turno}");
        log.debug("El tiempo de vida ha sido actualizado");
        if (tiempoDeVida<=0){
            celda.eliminarEstudiante(this);
            return true;
        }
        return false;
    }
    public Cola<String> getColaDeOperaciones() {
        return colaDeOperaciones;
    }

    public void setColaOperaciones(Cola<String> colaDeOperaciones) {
        this.colaDeOperaciones = colaDeOperaciones;
    }
}