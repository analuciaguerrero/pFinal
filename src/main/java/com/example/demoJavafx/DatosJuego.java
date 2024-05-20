package com.example.demoJavafx;

import com.example.demoJavafx.entorno.GsonRecursos;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ElementoLS;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.GsonListaSimple;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.GsonCola;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.GsonEstudiante;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatosJuego {

    private static final Logger log = LogManager.getLogger();
    //Listas con los estudiantes y recursos
    @Expose
    private ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
    @Expose
    private ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
    @Expose
    private ListaEnlazada<Estudiante> HistorialEstudiantes = new ListaEnlazada<>();
    @Expose
    private ListaEnlazada<Recursos> HistorialRecursos = new ListaEnlazada<>();
    @Expose
    private int numRecursos; // Almacenar la cantidad de recursos
    @Expose
    private int numEstudiantes; //Almacenar la cantidad de estudiantes

    //Datos del tablero
    @Expose
    private int MaximoEstudiantesPorCelda = 3;
    @Expose
    private int MaximoRecursosPorCelda = 3;
    @Expose
    private int FilasDelTablero;
    @Expose
    private int ColumnasDelTablero;

    //Datos de los recursos
    @Expose
    private double ProbRecurso;
    @Expose
    private int TurnosIniciales;
    @Expose
    private double ProbAgua;
    @Expose
    private double ProbComida;
    @Expose
    private double ProbMontaña;
    @Expose
    private double ProbTesoro;
    @Expose
    private double ProbBiblioteca;
    @Expose
    private double ProbPozo;
    @Expose
    private int AumentoVidaAgua;
    @Expose
    private int AumentoVidaComida;
    @Expose
    private int ReduccionVidaMontaña;
    @Expose
    private double AumentoProbReproduccion;
    @Expose
    private double AumentoProbClonacion;

    //Datos del estudiante
    @Expose
    private int TurnosVidaIniciales;
    @Expose
    private double ProbReproduccionEstudiante;
    @Expose
    private double ProbClonacionEstudiante;
    @Expose
    private double ProbMejorarANormal;
    @Expose
    private double ProbMejorarAAvanzado;
    @Expose
    private Boolean isPausado = false;
    @Expose
    private boolean isSave = true;
    @Expose
    private String rutaArchivo;
    @Expose
    private ZombieStudentsLife zombieStudentsLife;
    @Expose
    private int turnoActual;


    public DatosJuego(int turnosVidaIniciales, double probReproduccionEstudiante, double probClonacionEstudiante, double probMejorarANormal, double probMejorarAAvanzado, double probRecurso, int turnosIniciales, double probAgua, double probComida, double probMontaña, double probTesoro, double probBiblioteca, double probPozo, int aumentoVidaAgua, int aumentoVidaComida, int reduccionVidaMontaña, double aumentoProbReproduccion, double aumentoProbClonacion, int filasDelTablero, int columnasDelTablero, int turno) {
        this.TurnosVidaIniciales = turnosVidaIniciales;
        this.ProbReproduccionEstudiante = probReproduccionEstudiante;
        this.ProbClonacionEstudiante = probClonacionEstudiante;
        this.ProbMejorarANormal = probMejorarANormal;
        this.ProbMejorarAAvanzado = probMejorarAAvanzado;
        this.ProbRecurso = probRecurso;
        this.ProbAgua = probAgua;
        this.ProbComida = probComida;
        this.ProbMontaña = probMontaña;
        this.ProbTesoro = probTesoro;
        this.ProbBiblioteca = probBiblioteca;
        this.ProbPozo = probPozo;
        this.TurnosIniciales = turnosIniciales;
        this.AumentoVidaAgua = aumentoVidaAgua;
        this.AumentoVidaComida = aumentoVidaComida;
        this.ReduccionVidaMontaña = reduccionVidaMontaña;
        this.AumentoProbReproduccion = aumentoProbReproduccion;
        this.AumentoProbClonacion = aumentoProbClonacion;
        this.FilasDelTablero = filasDelTablero;
        this.ColumnasDelTablero = columnasDelTablero;
        this.turnoActual = turno;
    }
    public DatosJuego(){}


    public int getTurnosVidaIniciales() {
        return TurnosVidaIniciales;
    }

    public void setTurnosVidaIniciales(int turnosVidaIniciales) {
        TurnosVidaIniciales = turnosVidaIniciales;
        setSave(false);
    }

    public double getProbReproduccionEstudiante() {
        return ProbReproduccionEstudiante;
    }

    public void setProbReproduccionEstudiante(double probReproduccionEstudiante){
        ProbReproduccionEstudiante = probReproduccionEstudiante;
        setSave(false);
    }

    public double getProbClonacionEstudiante() {
        return ProbClonacionEstudiante;
    }

    public void setProbClonacionEstudiante(double probClonacionEstudiante) {
        ProbClonacionEstudiante = probClonacionEstudiante;
        setSave(false);
    }
    public double getProbRecurso() {
        return ProbRecurso;
    }

    public void setProbRecurso(double probRecurso) {
        ProbRecurso = probRecurso;
        setSave(false);
    }
    public int getNumEstudiantes(){
        return numEstudiantes;
    }
    public void setNumEstudiantes(int numEstudiantes){
        this.numEstudiantes = numEstudiantes;
        setSave(false);
    }
    public int getNumRecursos(){
        return numRecursos;
    }
    public void setNumRecursos(int numRecursos){
        this.numRecursos = numRecursos;
        setSave(false);
    }
    public double getProbAgua() {
        return ProbAgua;
    }

    public void setProbAgua(double probAgua) {
        ProbAgua = probAgua;
        setSave(false);
    }

    public double getProbComida() {
        return ProbComida;
    }

    public void setProbComida(double probComida) {
        ProbComida = probComida;
        setSave(false);
    }

    public double getProbMontaña() {
        return ProbMontaña;
    }

    public void setProbMontaña(double probMontaña) {
        ProbMontaña = probMontaña;
        setSave(false);
    }

    public double getProbTesoro() {
        return ProbTesoro;
    }

    public void setProbTesoro(double probTesoro) {
        ProbTesoro = probTesoro;
        setSave(false);
    }

    public double getProbBiblioteca() {
        return ProbBiblioteca;
    }

    public void setProbBiblioteca(double probBiblioteca) {
        ProbBiblioteca = probBiblioteca;
        setSave(false);
    }

    public double getProbPozo() {
        return ProbPozo;
    }

    public void setProbPozo(double probPozo) {
        ProbPozo = probPozo;
        setSave(false);
    }

    public int getAumentoVidaAgua() {
        return AumentoVidaAgua;
    }

    public void setAumentoVidaAgua(int aumentoVidaAgua) {
        AumentoVidaAgua = aumentoVidaAgua;
        setSave(false);
    }

    public int getAumentoVidaComida() {
        return AumentoVidaComida;
    }

    public void setAumentoVidaComida(int aumentoVidaComida) {
        AumentoVidaComida = aumentoVidaComida;
        setSave(false);
    }

    public int getReduccionVidaMontaña() {
        return ReduccionVidaMontaña;
    }

    public void setReduccionVidaMontaña(int reduccionVidaMontaña) {
        ReduccionVidaMontaña = reduccionVidaMontaña;
        setSave(false);
    }

    public double getAumentoProbReproduccion() {
        return AumentoProbReproduccion;
    }

    public void setAumentoProbReproduccion(double aumentoProbReproduccion) {
        AumentoProbReproduccion = aumentoProbReproduccion;
        setSave(false);
    }

    public double getAumentoProbClonacion() {
        return AumentoProbClonacion;
    }

    public void setAumentoProbClonacion(double aumentoProbClonacion) {
        AumentoProbClonacion = aumentoProbClonacion;
        setSave(false);
    }

    public int getFilasDelTablero() {
        return FilasDelTablero;
    }

    public void setFilasDelTablero(int filasDelTablero) {
        FilasDelTablero = filasDelTablero;
        setSave(false);
    }

    public int getColumnasDelTablero() {
        return ColumnasDelTablero;
    }

    public void setColumnasDelTablero(int columnasDelTablero) {
        ColumnasDelTablero = columnasDelTablero;
        setSave(false);
    }

    public int getMaximoEstudiantesPorCelda() {
        return MaximoEstudiantesPorCelda;
    }

    public void setMaximoEstudiantesPorCelda(int maximoEstudiantesPorCelda) {
        MaximoEstudiantesPorCelda = maximoEstudiantesPorCelda;
        setSave(false);
    }

    public int getMaximoRecursosPorCelda() {
        return MaximoRecursosPorCelda;
    }

    public void setMaximoRecursosPorCelda(int maximoRecursosPorCelda) {
        MaximoRecursosPorCelda = maximoRecursosPorCelda;
        setSave(false);
    }

    public double getProbMejorarANormal() {
        return ProbMejorarANormal;
    }

    public void setProbMejorarANormal(double ProbMejorarANormal) {
        this.ProbMejorarANormal = ProbMejorarANormal;
        setSave(false);
    }

    public double getProbMejorarAAvanzado() {
        return ProbMejorarAAvanzado;
    }

    public void setProbMejorarAAvanzado(double ProbMejorarAAvanzado) {
        this.ProbMejorarAAvanzado = ProbMejorarAAvanzado;
        setSave(false);
    }

    public Boolean isPausado() {
        return isPausado;
    }

    public void setPausado(Boolean pausado) {
        isPausado = pausado;
        setSave(false);
    }
    public ZombieStudentsLife getZombieStudentsLife(){
        return zombieStudentsLife;
    }

    public void setZombieStudentsLife(ZombieStudentsLife zombieStudentsLife) {
        this.zombieStudentsLife = zombieStudentsLife;
    }

    public ListaEnlazada<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
        setSave(false);
    }

    public ListaEnlazada<Recursos> getRecursos() {
        return recursos;
    }

    public void setRecursos(ListaEnlazada<Recursos> recursos) {
        this.recursos = recursos;
        setSave(false);
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
        setSave(false);
    }

    public int getTurnosIniciales() {
        return TurnosIniciales;
    }

    public void setTurnosIniciales(int turnosIniciales) {
        TurnosIniciales = turnosIniciales;
        setSave(false);
    }
    public int generarEnteroAleatorio(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
    public Celda celdaAleatoria(int filas, int columnas) {
        int filaAleatoria = generarEnteroAleatorio(0, filas - 1);
        int columnaAleatoria = generarEnteroAleatorio(0, columnas - 1);
        return new Celda(filaAleatoria, columnaAleatoria);
    }
    public Estudiante obtenerEstudianteAleatorio() {
        ListaEnlazada<Estudiante> estudiantesDisponibles = new ListaEnlazada<>();
        // Agregar los estudiantes disponibles a la lista
        ElementoLE<Estudiante> nodoEstudiante = estudiantes.getPrimero();
        while (nodoEstudiante != null) {
            Estudiante estudiante = nodoEstudiante.getData();
            estudiantesDisponibles.add(estudiante);
            nodoEstudiante = nodoEstudiante.getSiguiente();
        }

        if (!estudiantesDisponibles.isVacia()) {
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(estudiantesDisponibles.getNumeroElementos());
            Estudiante estudianteAleatorio = estudiantesDisponibles.getElemento(indiceAleatorio).getData();
            log.info("Se ha obtenido aleatoriamente el estudiante: " + estudianteAleatorio.toString());
            return estudianteAleatorio;
        } else {
            log.warn("No hay estudiantes disponibles en la lista.");
        }
        return null;
    }
    public Recursos obtenerRecursoAleatorio() {
        ListaEnlazada<Recursos> recursosDisponibles = new ListaEnlazada<>();
        // Agregar los recursos disponibles a la lista
        ElementoLE<Recursos> nodoRecurso = recursos.getPrimero();
        while (nodoRecurso != null) {
            Recursos recurso = nodoRecurso.getData();
            recursosDisponibles.add(recurso);
            nodoRecurso = nodoRecurso.getSiguiente();
        }

        if (!recursosDisponibles.isVacia()) {
            Random rand = new Random();
            int indiceAleatorio = rand.nextInt(recursosDisponibles.getNumeroElementos());
            Recursos recursoAleatorio = recursosDisponibles.getElemento(indiceAleatorio).getData();
            log.info("Se ha obtenido aleatoriamente el recurso: " + recursoAleatorio.toString());
            return recursoAleatorio;
        } else {
            log.warn("No hay recursos disponibles en la lista.");
        }
        return null;
    }
    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ListaEnlazada<Estudiante> getHistorialEstudiantes() {
        return HistorialEstudiantes;
    }

    public void setHistorialEstudiantes(ListaEnlazada<Estudiante> historialEstudiantes) {
        HistorialEstudiantes = historialEstudiantes;
        setSave(false);
    }

    public ListaEnlazada<Recursos> getHistorialRecursos() {
        return HistorialRecursos;
    }

    public void setHistorialRecursos(ListaEnlazada<Recursos> historialRecursos) {
        HistorialRecursos = historialRecursos;
        setSave(false);
    }
    public void guardarArchivo(String rutaArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Recursos.class, new GsonRecursos())
                .registerTypeAdapter(Estudiante.class, new GsonEstudiante())
                .registerTypeAdapter(ElementoLS[].class, new GsonListaSimple())
                .registerTypeAdapter(Cola.class, new GsonCola())
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(STR."archivos/\{rutaArchivo}.json")) {
            this.setSave(true);
            gson.toJson(this, writer);
        } catch (IOException e) {
            log.error("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static DatosJuego cargarArchivo(String rutaArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Recursos.class, new GsonRecursos())
                .registerTypeAdapter(Estudiante.class, new GsonEstudiante())
                .registerTypeAdapter(ElementoLS[].class, new GsonListaSimple())
                .registerTypeAdapter(Cola.class, new GsonCola())
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        try (FileReader reader = new FileReader(String.format(STR."archivos/\{rutaArchivo}.json"))) {
            DatosJuego dato = gson.fromJson(reader, DatosJuego.class);
            return dato;
        } catch (IOException e) {
            log.error("Error al cargar el archivo: " + e.getMessage());
            return null;
        }
    }
}
