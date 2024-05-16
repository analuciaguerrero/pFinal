package com.example.demoJavafx.XControllers;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DatosJuego {

    //Listas con los estudiantes y recursos
    private ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
    private ListaEnlazada<Estudiante> HistorialEstudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Recursos> HistorialRecursos = new ListaEnlazada<>();

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