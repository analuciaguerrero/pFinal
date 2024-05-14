package com.example.demoJavafx;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatosJuego {

    private static final Logger log = LogManager.getLogger(DatosJuego.class);
    //Listas con los estudiantes y recursos
    private ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
    private ListaEnlazada<Estudiante> HistorialEstudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Recursos> HistorialRecursos = new ListaEnlazada<>();
    private int numRecursos; // Almacenar la cantidad de recursos
    private int numEstudiantes; //Almacenar la cantidad de estudiantes

    //Datos del tablero
    private int MaximoEstudiantesPorCelda = 3;
    private int MaximoRecursosPorCelda = 3;
    private int FilasDelTablero;
    private int ColumnasDelTablero;

    //Datos de los recursos
    private double ProbRecurso;
    private int TurnosIniciales;
    private double ProbAgua;
    private double ProbComida;
    private double ProbMontaña;
    private double ProbTesoro;
    private double ProbBiblioteca;
    private double ProbPozo;
    private int AumentoVidaAgua;
    private int AumentoVidaComida;
    private int ReduccionVidaMontaña;
    private double AumentoProbReproduccion;
    private double AumentoProbClonacion;

    //Datos del estudiante
    private int TurnosVidaIniciales;
    private double ProbReproduccionEstudiante;
    private double ProbClonacionEstudiante;
    private double ProbMejorarANormal;
    private double ProbMejorarAAvanzado;


    private Boolean isPausado = false;
    private IntegerProperty turnoProperty = new SimpleIntegerProperty();



    public DatosJuego(int turnosVidaIniciales, double probReproduccionEstudiante, double probClonacionEstudiante, double probMejorarANormal, double probMejorarAAvanzado, double probRecurso, int turnosIniciales, double probAgua, double probComida, double probMontaña, double probTesoro, double probBiblioteca, double probPozo, int aumentoVidaAgua, int aumentoVidaComida, int reduccionVidaMontaña, double aumentoProbReproduccion, double aumentoProbClonacion, int filasDelTablero, int columnasDelTablero, int Turno) {
        TurnosVidaIniciales = turnosVidaIniciales;
        ProbReproduccionEstudiante = probReproduccionEstudiante;
        ProbClonacionEstudiante = probClonacionEstudiante;
        ProbMejorarANormal = probMejorarANormal;
        ProbMejorarAAvanzado = probMejorarAAvanzado;
        ProbRecurso = probRecurso;
        ProbAgua = probAgua;
        ProbComida = probComida;
        ProbMontaña = probMontaña;
        ProbTesoro = probTesoro;
        ProbBiblioteca = probBiblioteca;
        ProbPozo = probPozo;
        TurnosIniciales = turnosIniciales;
        AumentoVidaAgua = aumentoVidaAgua;
        AumentoVidaComida = aumentoVidaComida;
        ReduccionVidaMontaña = reduccionVidaMontaña;
        AumentoProbReproduccion = aumentoProbReproduccion;
        AumentoProbClonacion = aumentoProbClonacion;
        FilasDelTablero = filasDelTablero;
        ColumnasDelTablero = columnasDelTablero;
        turnoProperty.set(Turno);
        turnoProperty.set(0);
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

    public IntegerProperty getTurnoProperty() {
        return turnoProperty;
    }

    public int getTurno() {
        return turnoProperty.get();
    }

    public void setTurno(int turno) {
        turnoProperty.set(turno);
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
}
