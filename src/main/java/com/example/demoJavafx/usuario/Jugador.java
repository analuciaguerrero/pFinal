package com.example.demoJavafx.usuario;

import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;

import java.io.Serializable;

public class Jugador implements Serializable {
    public String nombre;
    public String dni;
    public String dificultad;
    public int puntuacion;
    public int partidasJugadas;
    public int partidasGanadas;
    public int partidasPerdidas;
    private ListaSimple<String> dificultades = new ListaSimple<>();
    private ListaSimple<Integer> puntosPartidas = new ListaSimple<>();

    public Jugador(String dni) {
        this.nombre = this.nombre;
        this.dni = dni;
        this.dificultad = "BAJA";
        this.puntuacion = 0;
        this.partidasJugadas = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    public ListaSimple<String> getDificultades() {
        return this.dificultades;
    }

    public void addDificultad(String dificultad) {
        this.dificultades.add(dificultad);
    }

    public ListaSimple<Integer> getPuntosPartida() {
        return this.puntosPartidas;
    }

    public void addPuntos(int puntos) {
        this.puntosPartidas.add(puntos);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void addPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDificultad() {
        return this.dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getPartidasJugadas() {
        return this.partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public void addPartidaJugada() {
        ++this.partidasJugadas;
    }

    public void addPartidaGanada() {
        ++this.partidasGanadas;
    }

    public void addPartidaPerdida() {
        ++this.partidasPerdidas;
    }

    public int getPartidasGanadas() {
        return this.partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return this.partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }
}

