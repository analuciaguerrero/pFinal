package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.entorno.*;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.estudiante.EstudianteNormal;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.excepciones.MasDe3Recursos;
import com.example.demoJavafx.zombieStudentsLife.Celda;

import java.util.Random;

import static com.example.demoJavafx.game.Game.tablero;

public class Tablero {
    protected int fila; //Número de filas
    protected int columna; //Número de columnas
    protected ListaEnlazada<Celda> celdas; //Matriz de celdas
    protected static final int maxEstudiantesPorCelda = 3;
    protected static final int maxRecursosPorCelda = 3;

    public Tablero(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        celdas = new ListaEnlazada<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Crear las celdas y agregarlas al tablero
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                celdas.add(new Celda());
            }
        }
    }
    // Obtener una celda específica del tablero
    public Celda getCelda(int fila, int columna) {
        int indice = fila * this.columna + columna;
        return celdas.getElemento(indice).getData();
    }
    public int getFilas(){
        return fila;
    }
    public void setFilas(int fila){
        this.fila = fila;
    }
    public int getColumnas(){
        return columna;
    }
    public void setColumnas(int columna){
        this.columna = columna;
    }
    public ListaEnlazada<Celda> getMatriz() {
        return celdas;
    }
    public void setMatriz(ListaEnlazada<Celda> celdas) {
        this.celdas = celdas;
    }
    public void crearTableroAleatorio(){
        Integer numeroCuadrados = tablero.getSquares().getNumeroElementos();
        if (numeroCuadrados == 1) {
            addIndividuo(tablero.getSquare(0));
            int tipoRecurso = generarEnteroAleatorio((int)2.0, (int)7.0);
            addRecursos(tablero.getSquare(0), (double) tipoRecurso);
        } else if (numeroCuadrados == 2) {
            int numerocuadrado = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado));
            int numerocuadrado2 = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado2));
            int tipoRecurso = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), (double) tipoRecurso);
            int tipoRecurso2 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado2), (double) tipoRecurso2);
            int tipoRecurso3 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), (double) tipoRecurso3);
        } else {
            int numIndividuos = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 2);
            int numAgua = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numComida = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numMontana = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numBiblioteca = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numTesoro = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numPozo = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            for (int i = 0; i < numIndividuos; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addIndividuo(tablero.getSquare(idSquareAleatorio));
            }
            for (int i = 0; i < numAgua; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 2.0);
            }
            for (int i = 0; i < numComida; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 3.0);
            }
            for (int i = 0; i < numMontana; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 4.0);
            }
            for (int i = 0; i < numBiblioteca; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 5.0);
            }
            for (int i = 0; i < numTesoro; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 6.0);
            }
            for (int i = 0; i < numPozo; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 7.0);
            }
        }
    }

    public void evaluarMejoras() {
        for (int i = 0; i < celdas.getNumeroElementos(); i++) {
            Celda celda = celdas.getElemento(i).getData();
            celda.evaluarMejoras();
        }
    }

    public void evaluarReproduccion() {
        // Seleccionar dos estudiantes aleatoriamente para la reproducción
        Random rand = new Random();
        int indice1 = rand.nextInt(celdas.getNumeroElementos());
        int indice2 = rand.nextInt(celdas.getNumeroElementos());
        while (indice1 == indice2) {
            indice2 = rand.nextInt(celdas.getNumeroElementos());
        }
        Estudiante estudiante1 = obtenerEstudianteEnIndice(indice1);
        Estudiante estudiante2 = obtenerEstudianteEnIndice(indice2);

        try {
            // Verificar si los estudiantes pueden reproducirse
            if (estudiante1.puedeReproducirse() && estudiante2.puedeReproducirse()) {
                // Realizar la reproducción
                if (rand.nextDouble() < estudiante1.getProbReproduccion() && rand.nextDouble() < estudiante2.getProbReproduccion()) {
                    // Se reproducen generando un nuevo estudiante
                    Estudiante nuevoEstudiante = estudiante1.reproducirse(estudiante2);
                    // Agregar el nuevo estudiante a la celda
                    int indiceCelda = rand.nextInt(celdas.getNumeroElementos());
                    ElementoLE<Celda> celda = celdas.getElemento(indiceCelda);
                    if (celda.getData().getListaEstudiantes().getNumeroElementos() < maxEstudiantesPorCelda) {
                        celda.getData().getListaEstudiantes().add(nuevoEstudiante);
                    } else {
                        throw new MasDe3Estudiantes(celda.getData().getListaEstudiantes());
                    }
                }
            }
        } catch (MasDe3Estudiantes e) {
            System.out.println("No se pudo agregar un nuevo estudiante: " + e.getMessage());
        }
    }

    private Estudiante obtenerEstudianteEnIndice(int indice) {
        // Obtener el estudiante en la posición dada
        int contador = 0;
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            if (contador == indice) {
                return nodoCelda.getData().obtenerEstudianteAleatorio();
            }
            contador++;
            nodoCelda = nodoCelda.getSiguiente();
        }
        return null; // Si el índice es inválido o no se encuentra al estudiante
    }

    public void evaluarClonacion() {
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        Random rand = new Random();
        while (nodoCelda != null) {
            Estudiante estudiante = nodoCelda.getData().obtenerEstudianteAleatorio();
            if (estudiante != null && Math.random() < estudiante.getProbClonacion()) {
                try {
                    // Clonar el estudiante
                    Estudiante clon = estudiante.clonar();

                    // Insertar el clon en la lista de estudiantes de la celda
                    ListaEnlazada<Estudiante> listaEstudiantes = nodoCelda.getData().getListaEstudiantes();
                    if (listaEstudiantes.getNumeroElementos() < maxEstudiantesPorCelda) {
                        ElementoLE<Estudiante> nuevoElemento = new ElementoLE<>(clon);
                        if (listaEstudiantes.isVacia()) {
                            listaEstudiantes.getEl().insertarmeEn(nuevoElemento);
                        } else {
                            listaEstudiantes.getUltimo().insertarmeEn(nuevoElemento);
                        }
                    } else {
                        throw new MasDe3Estudiantes(listaEstudiantes);
                    }
                } catch (MasDe3Estudiantes e) {
                    System.out.println("No se pudo clonar al estudiante: " + e.getMessage());
                }
            }
            nodoCelda = nodoCelda.getSiguiente();
        }
    }

    public void evaluarDesaparicionEstudiantes() {
        if (celdas.getNumeroElementos() > maxEstudiantesPorCelda) {
            // Eliminar estudiantes si hay más de los máximos permitidos por celda
            ElementoLE<Celda> nodoCelda = celdas.getPrimero();
            while (nodoCelda != null) {
                Celda celda = nodoCelda.getData();
                if (celda.getListaEstudiantes().getNumeroElementos() > maxEstudiantesPorCelda) {
                    celda.eliminarEstudianteAleatorio();
                }
                nodoCelda = nodoCelda.getSiguiente();
            }
        }
    }

    private Recursos generarNuevoRecurso(int posicionN, int posicionM) {
        Random rand = new Random();
        double probabilidad = rand.nextDouble(); // Genera una probabilidad aleatoria entre 0 y 1
        if (probabilidad < Agua.getProbAgua()) { // Utiliza la probabilidad definida en la clase Agua
            return new Agua(posicionN, posicionM, rand.nextInt(), probabilidad, 2, Agua.getProbAgua());
        } else if (probabilidad < Agua.getProbAgua() + Comida.getProbComida()) { // Utiliza la probabilidad definida en la clase Comida
            return new Comida(posicionN, posicionM, rand.nextInt(), probabilidad, 10, Comida.getProbComida());
        } else if (probabilidad < Agua.getProbAgua() + Comida.getProbComida() + Montaña.getProbMontaña()) { // Utiliza la probabilidad definida en la clase Montaña
            return new Montaña(posicionN, posicionM, rand.nextInt(), probabilidad, 2, Montaña.getProbMontaña());
        } else if (probabilidad < Agua.getProbAgua() + Comida.getProbComida() + Montaña.getProbMontaña() + Tesoro.getProbTesoro()) { // Utiliza la probabilidad definida en la clase Tesoro
            return new Tesoro(posicionN, posicionM, rand.nextInt(), probabilidad, 0.40, Tesoro.getProbTesoro());
        } else if (probabilidad < Agua.getProbAgua() + Comida.getProbComida() + Montaña.getProbMontaña() + Tesoro.getProbTesoro() + Biblioteca.getProbBiblioteca()) { // Utiliza la probabilidad definida en la clase Biblioteca
            return new Biblioteca(posicionN, posicionM, rand.nextInt(), probabilidad, 0.25, Biblioteca.getProbBiblioteca());
        } else { // El resto de la probabilidad se asigna a Pozo
            return new Pozo(generarNuevoRecurso(posicionN, posicionM).getTurnosRestantes());
        }
    }

    public void evaluarAparicionRecursos() throws MasDe3Recursos {
        Random rand = new Random();
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                double probabilidad = rand.nextDouble();
                Recursos nuevoRecurso = generarNuevoRecurso(i, j); // Llama al método con las coordenadas de la celda

                // Insertar el nuevo recurso en la lista de recursos de la celda
                ListaEnlazada<Recursos> listaRecursos = celdas.getElemento(i * columna + j).getData().getListaRecursos();
                if (listaRecursos.getNumeroElementos() < maxRecursosPorCelda) {
                    ElementoLE<Recursos> nuevoElemento = new ElementoLE<>(nuevoRecurso);
                    if (listaRecursos.isVacia()) {
                        listaRecursos.getEl().insertarmeEn(nuevoElemento);
                    } else {
                        listaRecursos.getUltimo().insertarmeEn(nuevoElemento);
                    }
                } else {
                    throw new MasDe3Recursos(listaRecursos);
                }
            }
        }
    }
    public void actualizarTiempoDeVida() {
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            Celda celda = nodoCelda.getData();
            ListaEnlazada<Estudiante> listaEstudiantes = celda.getListaEstudiantes();
            ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
            int posicion = 1; // Posición del estudiante en la lista
            while (nodoEstudiante != null) {
                Estudiante estudiante = nodoEstudiante.getData();
                estudiante.actualizarTiempoDeVida();
                if (estudiante.getTiempoDeVida() <= 0) {
                    // Eliminar estudiante si su tiempo de vida llega a cero o menos
                    listaEstudiantes.delete(posicion);
                } else {
                    posicion++; // Incrementar la posición si no se elimina el estudiante
                }
                nodoEstudiante = nodoEstudiante.getSiguiente();
            }
            nodoCelda = nodoCelda.getSiguiente();
        }
    }
    public void evaluarEliminacionRecursos() {
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            Celda celda = nodoCelda.getData();
            ListaEnlazada<Recursos> listaRecursos = celda.getListaRecursos();
            ElementoLE<Recursos> nodoRecurso = listaRecursos.getPrimero();
            int posicion = 1; // Posición del recurso en la lista
            while (nodoRecurso != null) {
                Recursos recurso = nodoRecurso.getData();
                recurso.actualizarRecursos(); // Disminuir el contador de turnos restantes
                if (recurso.getTurnosRestantes() <= 0) {
                    // Eliminar recurso si los turnos restantes llegan a cero o menos
                    listaRecursos.delete(posicion);
                } else {
                    posicion++; // Incrementar la posición si no se elimina el recurso
                }
                nodoRecurso = nodoRecurso.getSiguiente();
            }
            nodoCelda = nodoCelda.getSiguiente();
        }
    }
    public void moverEstudiantes() throws MasDe3Estudiantes {
        ElementoLE<Celda> nodoCelda = celdas.getPrimero();
        while (nodoCelda != null) {
            Celda celda = nodoCelda.getData();
            ListaEnlazada<Estudiante> listaEstudiantes = celda.getListaEstudiantes();
            ElementoLE<Estudiante> nodoEstudiante = listaEstudiantes.getPrimero();
            while (nodoEstudiante != null) {
                Estudiante estudiante = nodoEstudiante.getData();
                estudiante.mover(celdas);

                // Verificar el tipo de estudiante para actualizar la posición
                if (estudiante instanceof EstudianteBasico) {
                    EstudianteBasico estudianteBasico = (EstudianteBasico) estudiante;
                    estudianteBasico.mover(celdas);
                } else if (estudiante instanceof EstudianteNormal) {
                    EstudianteNormal estudianteNormal = (EstudianteNormal) estudiante;
                    estudianteNormal.mover(celdas);
                } else if (estudiante instanceof EstudianteAvanzado) {
                    EstudianteAvanzado estudianteAvanzado = (EstudianteAvanzado) estudiante;
                    estudianteAvanzado.mover(celdas);
                }
                nodoEstudiante = nodoEstudiante.getSiguiente();
            }
            nodoCelda = nodoCelda.getSiguiente();
        }
    }

}

