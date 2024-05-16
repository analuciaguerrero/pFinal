package com.example.demoJavafx;

import com.example.demoJavafx.entorno.GsonRecursos;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.GsonEstudiante;
import com.example.demoJavafx.tablero.Celda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatosJuego {

    private static final Logger log = LogManager.getLogger(DatosJuego.class);
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


    public int getTurnosVidaIniciales() {
        return TurnosVidaIniciales;
    }

    public void setTurnosVidaIniciales(int turnosVidaIniciales) {
        TurnosVidaIniciales = turnosVidaIniciales;
    }

    public double getProbReproduccionEstudiante() {
        return ProbReproduccionEstudiante;
    }

    public void setProbReproduccionEstudiante(double probReproduccionEstudiante){
        ProbReproduccionEstudiante = probReproduccionEstudiante;
    }

    public double getProbClonacionEstudiante() {
        return ProbClonacionEstudiante;
    }

    public void setProbClonacionEstudiante(double probClonacionEstudiante) {
        ProbClonacionEstudiante = probClonacionEstudiante;
    }
    public double getProbRecurso() {
        return ProbRecurso;
    }

    public void setProbRecurso(double probRecurso) {
        ProbRecurso = probRecurso;
    }
    public int getNumEstudiantes(){
        return numEstudiantes;
    }
    public void setNumEstudiantes(int numEstudiantes){
        this.numEstudiantes = numEstudiantes;
    }
    public int getNumRecursos(){
        return numRecursos;
    }
    public void setNumRecursos(int numRecursos){
        this.numRecursos = numRecursos;
    }
    public double getProbAgua() {
        return ProbAgua;
    }

    public void setProbAgua(double probAgua) {
        ProbAgua = probAgua;
    }

    public double getProbComida() {
        return ProbComida;
    }

    public void setProbComida(double probComida) {
        ProbComida = probComida;
    }

    public double getProbMontaña() {
        return ProbMontaña;
    }

    public void setProbMontaña(double probMontaña) {
        ProbMontaña = probMontaña;
    }

    public double getProbTesoro() {
        return ProbTesoro;
    }

    public void setProbTesoro(double probTesoro) {
        ProbTesoro = probTesoro;
    }

    public double getProbBiblioteca() {
        return ProbBiblioteca;
    }

    public void setProbBiblioteca(double probBiblioteca) {
        ProbBiblioteca = probBiblioteca;
    }

    public double getProbPozo() {
        return ProbPozo;
    }

    public void setProbPozo(double probPozo) {
        ProbPozo = probPozo;
    }

    public int getAumentoVidaAgua() {
        return AumentoVidaAgua;
    }

    public void setAumentoVidaAgua(int aumentoVidaAgua) {
        AumentoVidaAgua = aumentoVidaAgua;
    }

    public int getAumentoVidaComida() {
        return AumentoVidaComida;
    }

    public void setAumentoVidaComida(int aumentoVidaComida) {
        AumentoVidaComida = aumentoVidaComida;
    }

    public int getReduccionVidaMontaña() {
        return ReduccionVidaMontaña;
    }

    public void setReduccionVidaMontaña(int reduccionVidaMontaña) {
        ReduccionVidaMontaña = reduccionVidaMontaña;
    }

    public double getAumentoProbReproduccion() {
        return AumentoProbReproduccion;
    }

    public void setAumentoProbReproduccion(double aumentoProbReproduccion) {
        AumentoProbReproduccion = aumentoProbReproduccion;
    }

    public double getAumentoProbClonacion() {
        return AumentoProbClonacion;
    }

    public void setAumentoProbClonacion(double aumentoProbClonacion) {
        AumentoProbClonacion = aumentoProbClonacion;
    }

    public int getFilasDelTablero() {
        return FilasDelTablero;
    }

    public void setFilasDelTablero(int filasDelTablero) {
        FilasDelTablero = filasDelTablero;
    }

    public int getColumnasDelTablero() {
        return ColumnasDelTablero;
    }

    public void setColumnasDelTablero(int columnasDelTablero) {
        ColumnasDelTablero = columnasDelTablero;
    }

    public int getMaximoEstudiantesPorCelda() {
        return MaximoEstudiantesPorCelda;
    }

    public void setMaximoEstudiantesPorCelda(int maximoEstudiantesPorCelda) {
        MaximoEstudiantesPorCelda = maximoEstudiantesPorCelda;
    }

    public int getMaximoRecursosPorCelda() {
        return MaximoRecursosPorCelda;
    }

    public void setMaximoRecursosPorCelda(int maximoRecursosPorCelda) {
        MaximoRecursosPorCelda = maximoRecursosPorCelda;
    }

    public double getProbMejorarANormal() {
        return ProbMejorarANormal;
    }

    public void setProbMejorarANormal(double ProbMejorarANormal) {
        this.ProbMejorarANormal = ProbMejorarANormal;
    }

    public double getProbMejorarAAvanzado() {
        return ProbMejorarAAvanzado;
    }

    public void setProbMejorarAAvanzado(double ProbMejorarAAvanzado) {
        this.ProbMejorarAAvanzado = ProbMejorarAAvanzado;
    }

    public Boolean isPausado() {
        return isPausado;
    }

    public void setPausado(Boolean pausado) {
        isPausado = pausado;
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

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public int getTurnosIniciales() {
        return TurnosIniciales;
    }

    public void setTurnosIniciales(int turnosIniciales) {
        TurnosIniciales = turnosIniciales;
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
    }

    public ListaEnlazada<Recursos> getHistorialRecursos() {
        return HistorialRecursos;
    }

    public void setHistorialRecursos(ListaEnlazada<Recursos> historialRecursos) {
        HistorialRecursos = historialRecursos;
    }
    public void guardarArchivo(String rutaArchivo){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Estudiante.class, new GsonEstudiante())
                .registerTypeAdapter(Recursos.class, new GsonRecursos())
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(STR."archivos/\{rutaArchivo}.json")) {
            gson.toJson(this, writer);
            this.setSave(true);
        } catch (IOException e) {
            log.error("La ruta no existe");
        }
    }
    public static DatosJuego cargarArchivo(String rutaArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Estudiante.class, new GsonEstudiante())
                .registerTypeAdapter(Recursos.class, new GsonRecursos())
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        try (FileReader reader = new FileReader(STR."archivos/\{rutaArchivo}")) {
            return gson.fromJson(reader, DatosJuego.class);
        } catch (IOException e) {
            log.error("La ruta no existe");
            return null;
        }
    }
}
