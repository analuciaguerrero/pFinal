package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.Nodo;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import com.example.demoJavafx.zombieStudentsLife.Tablero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Estudiante<Tipo extends Estudiante<Tipo>> {
    private int posicionN;
    private int posicionM;
    private int id;
    private int generacion;
    private int tiempoDeVida;
    private double probReproduccion;
    private double probClonacion;
    private double probMuerte;
    BST<Estudiante<Tipo>> arbolGenealogico;
    private static final Logger log = LogManager.getLogger(Estudiante.class);

    public Estudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte, int posicionN, int posicionM) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = 1-probReproduccion;
        this.posicionM = posicionM;
        this.posicionN = posicionN;
        if (probReproduccion < 0 || probReproduccion > 100 || probClonacion < 0 || probClonacion > 100) throw new ProbabilidadNoValida();
        this.arbolGenealogico = new BST<>();
        this.arbolGenealogico.raiz = new Nodo<>(this);
    }
    public Estudiante(int id) {
        this.id = id;
        this.arbolGenealogico = new BST<>();
        this.arbolGenealogico.raiz = new Nodo<>(this);
    }

    public Estudiante(int id, int tiempoDeVida) {
        this.id = id;
        this.tiempoDeVida = tiempoDeVida;
        this.arbolGenealogico = new BST<>();
        this.arbolGenealogico.raiz = new Nodo<>(this);
    }

    public Estudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
        this.id = id;
        this.generacion = generacion;
        this.tiempoDeVida = tiempoDeVida;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.probMuerte = 1-probReproduccion;
        this.arbolGenealogico = new BST<>();
        this.arbolGenealogico.raiz = new Nodo<>(this);
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
        this.probMuerte = 1 - probReproduccion;
        this.arbolGenealogico = new BST<>();
        this.arbolGenealogico.raiz = new Nodo<>(this);
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
    public void setPosicion(int[] posicion){
        try {
                if (posicion.length != 2) throw new TamañoArrayInvalido();
            posicionN = posicion[0];
            posicionM = posicion[1];
        } catch (TamañoArrayInvalido e) {
            log.error("Se ha intentado establecer la posición de un individuo con un array que no contiene 2 elementos");
        }
    }

    public int getId() {
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

    public void setTiempoDeVida(int tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
        log.info("El tiempo de vida se ha modificado");
    }

    public double getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(double probReproduccion) {
        this.probReproduccion = probReproduccion;
        if (probReproduccion < 0 || probReproduccion > 100) throw new ProbabilidadNoValida();
        log.info("La probabilidad de reproducción ha sido modificada");
    }

    public double getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(double probClonacion) {
        this.probClonacion = probClonacion;
        if (probClonacion < 0 || probClonacion > 100) throw new ProbabilidadNoValida();
        log.info("La probabilidad de clonación ha sido modificada");
    }

    public double getProbMuerte() {
        return probMuerte;
    }
    public abstract Class<Tipo> getTipo();
    public int getNumTipo() {
        int grado = -1;
        switch (this.getClass().getSimpleName()) {
            case "EstudianteBasico":
                grado = 0;
                break;
            case "EstudianteNormal":
                grado = 1;
                break;
            case "EstudianteAvanzado":
                grado = 2;
                break;
            default:
                log.error("El grado del tipo no es ninguno de los requeridos");
        }
        return grado;
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

            Constructor<? extends Estudiante> constructor = getClass().getConstructor(this.getTipo());
            dato.getHistorialEstudiantes().add(constructor.newInstance(this));

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            log.error("No se ha podido crear una nueva instancia del estudiante para el historial de estudiantes");
        }
    }

    public void morir(DatosJuego dato, Celda celda) {
        celda.getListaEstudiantes().del(this);
        dato.getEstudiantes().del(this);
    }
    public abstract void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes;
    protected void cambiarDePosicion (int nuevaPosicionN, int nuevaPosicionM, Tablero tablero) {
        Celda celda = tablero.getCelda(nuevaPosicionN, nuevaPosicionM);
        Celda celdaActual = tablero.getCelda(getPosicion());
        celdaActual.getListaEstudiantes().del(this);
        setPosicionN(nuevaPosicionN);
        setPosicionM(nuevaPosicionM);
        celda.getListaEstudiantes().add(this);
    }
    protected void moverseAleatorio(Tablero tablero) {
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
        } catch (IndexOutOfBoundsException e) {
            moverseAleatorio(tablero);
        }
    }
    public <Tipo extends Estudiante<Tipo>> void reproducirse (Estudiante pareja, DatosJuego dato, Celda celda) {
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

            Class<Tipo> hijoTipo = estudiante1.getTipo();
            if (m <= probMejora) {
                hijoTipo = estudiante1.getTipo();
            } else {
                hijoTipo = estudiante2.getTipo();
            }
            try {
                Constructor<Tipo> constructor = hijoTipo.getConstructor(int.class, int.class, int.class, float.class, float.class);
                int id = dato.getHistorialEstudiantes().getUltimo().getData().getId() + 1;
                Tipo hijo = constructor.newInstance(id, getPosicionN(), getPosicionM(), dato.getProbReproduccionEstudiante(), dato.getProbClonacionEstudiante());
                hijo.add(dato, celda);
            } catch (Exception e) {
                log.error("No se ha podido crear una instancia para el estudiante hijo");
                e.printStackTrace();
            }
        } else {
            pareja.morir(dato, celda);
            this.morir(dato, celda);
        }
    }
    public void clonar(DatosJuego dato, Celda celda) {
        try {
            Constructor<? extends Estudiante> constructor = getClass().getConstructor(this.getTipo());
            Estudiante clon = constructor.newInstance(this);
            clon.add(dato, celda);
        } catch (Exception e) {
            log.error("No se ha podido crear un clon del estudiante");
        }
    }
    public void actualizarTiempoDeVida(DatosJuego dato, Celda celda){
        tiempoDeVida--;
        if (tiempoDeVida==0){
            morir(dato,celda);
        }
        log.info("El tiempo de vida ha sido actualizado");
    }
    public BST<Estudiante<Tipo>> getArbolGenealogico() {
        return arbolGenealogico;
    }

    public void setArbolGenealogico(BST<Estudiante<Tipo>> arbolGenealogico) {
        this.arbolGenealogico = arbolGenealogico;
    }

}