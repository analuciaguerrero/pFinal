package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.Nodo;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Estudiante<Tipo extends Estudiante<Tipo>> {
    @Expose
    private int posicionN;
    @Expose
    private int posicionM;
    @Expose
    private int id;
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

    public Estudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion) {
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
    public void setPosicion(int[] posicion) throws TamañoArrayInvalido{
        try {
                if (posicion.length != 2) throw new TamañoArrayInvalido();
            posicionN = posicion[0];
            posicionM = posicion[1];
        } catch (TamañoArrayInvalido e) {
            log.error("El array no contiene 2 elementos y por tanto, no se ha podido establecer la posición del estudiante");
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
    public boolean isVivo(){
        return isVivo;
    }
    public abstract Class<Tipo> getTipo();
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

            Class<? extends Estudiante> claseEstudiante = this.getTipo();
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
    }
    public abstract void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes;
    protected void cambiarDePosicion (int nuevaPosicionN, int nuevaPosicionM, Tablero tablero) {
        Celda celdaNueva = tablero.getCelda(nuevaPosicionN, nuevaPosicionM);
        Celda celdaActual = tablero.getCelda(getPosicionN(), getPosicionM());
        if (celdaActual != null) {
            // Eliminar el estudiante de la celda actual
            celdaActual.eliminarEstudiante(this);
        }

        // Actualizar la posición del estudiante
        setPosicionN(nuevaPosicionN);
        setPosicionM(nuevaPosicionM);

        if (celdaNueva != null) {
            // Agregar el estudiante a la nueva celda
            celdaNueva.agregarEstudiante(this);
        }
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
    private boolean estudianteYaMovido(Estudiante estudiante, ListaSimple<Integer> lista) {
        int contador = 0;
        boolean estudianteMovido = false;
        while (contador < lista.getNumeroElementos() && !estudianteMovido) {
            if (estudiante.getId() == lista.getDato(contador)) {
                estudianteMovido = true;
            }
            contador++;
        }
        return estudianteMovido;
    }
    public <Tipo extends Estudiante<Tipo>> boolean reproducirse (Estudiante pareja, DatosJuego dato, Celda celda) {
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
                Constructor<Tipo> constructor = hijoTipo.getConstructor(int.class, int.class, int.class, double.class, double.class);
                int id = dato.getHistorialEstudiantes().getUltimo().getData().getId() + 1;
                Tipo hijo = constructor.newInstance(id, getPosicionN(), getPosicionM(), dato.getProbReproduccionEstudiante(), dato.getProbClonacionEstudiante());
                hijo.add(dato, celda);
                return false; //No mueren
            } catch (Exception e) {
                log.error("No se ha podido crear una instancia para el estudiante hijo");
                e.printStackTrace();
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
            clon.add(dato, celda);
        } catch (Exception e) {
            log.error("No se ha podido crear un clon del estudiante");
        }
    }
    public boolean actualizarTiempoDeVida(DatosJuego dato, Celda celda){
        tiempoDeVida--;
        log.info("El tiempo de vida ha sido actualizado");
        if (tiempoDeVida<=0){
            celda.eliminarEstudiante(this);
            return true;
        }
        return false;
    }
    public BST<Estudiante<Tipo>> getArbolGenealogico() {
        return arbolGenealogico;
    }

    public void setArbolGenealogico(BST<Estudiante<Tipo>> arbolGenealogico) {
        this.arbolGenealogico = arbolGenealogico;
    }

}