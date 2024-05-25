package com.example.demoJavafx;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import com.example.demoJavafx.estructurasDeDatos.Grafo.*;
import java.io.File;
public class MostrarPantallaFinal {
    public static void main(String[] args) {
        try {
            DatosJuego dato = DatosJuego.cargarArchivo(STR."\{args[0]}.json");
            if (dato == null) {
                System.out.println("La ruta del archivo no es la correcta, estas son las partidas que hay ya creadas:");
                File carp = new File("archivos");
                File[] archivos = carp.listFiles();
                if (archivos != null) {
                    String[] nom = new String[archivos.length];
                    for (int i = 0; i != archivos.length; i++) {
                        nom[i] = archivos[i].getName();
                    }
                    System.out.println(nom);
                } else {
                    System.out.println("No se ha encontrado ninguna partida");
                }
            } else {
                ZombieStudentsLife juego = new ZombieStudentsLife(dato, true);
                juego.informacion();
                Grafo<String> grafoOperaciones = juego.getGrafoDeOperaciones();
                System.out.print("\nEstudiante más longevo: ");
                Estudiante estudianteLongevo = getEstudianteLongevo(dato, grafoOperaciones);
                System.out.println(STR."Estudiante \{estudianteLongevo.getId()}");
                System.out.println("Operaciones que ha realizado:");
                System.out.println(estudianteLongevo.getColaDeOperaciones());
                System.out.print("Clonaciones ocurridas: ");
                System.out.println(getNumeroOperaciones(dato, grafoOperaciones, "clonarse"));
                System.out.print("Reproducciones ocurridas: ");
                System.out.println(getNumeroOperaciones(dato, grafoOperaciones, "reproducirse"));
                System.out.print("Estudiante con más reproducciones: ");
                System.out.println(STR."Estudiante \{getEstudianteMasOperacion(dato, grafoOperaciones, "reproducirse").getId()}");
                System.out.print("Estudiante con más clonaciones: ");
                System.out.println(STR."Estudiante \{getEstudianteMasOperacion(dato, grafoOperaciones, "clonarse").getId()}");
                System.out.print("Estudiante que ha bebido más agua: ");
                System.out.println(STR."Estudiante \{getEstudianteMasOperacion(dato, grafoOperaciones, "agua").getId()}");
                System.out.print("Estudiante con máximo tiempo de vida: ");
                Estudiante estudianteTVMaximo = getEstudianteTVMaximo(dato, grafoOperaciones);
                System.out.println(STR."Estudiante \{estudianteTVMaximo.getId()}, ha llegado a tener \{getTVMaximoEstudiante(grafoOperaciones, estudianteTVMaximo)} tiempo de vida");
                if (estudianteLongevo == estudianteTVMaximo) {
                    System.out.println("El estudiante más longevo y el que ha llegado a tener un tiempo de vida máximo coinciden\n");
                } else {
                    System.out.println("El estudiante más longevo y el que ha llegado a tener un tiempo de vida máximo no coinciden\n");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Estudiante getEstudianteLongevo (DatosJuego dato, Grafo<String> grafoOperaciones) {
        NodoGrafo<String> nodoEst = grafoOperaciones.getNodoGrafo("estudiantes");
        int idEstudianteLongevo = -1;
        int turnosEstudianteLongevo = -1;
        int numeroEstudiantes = nodoEst.getListaSalida().getNumeroElementos();
        for (int i = 0; i != numeroEstudiantes; i ++) {
            NodoGrafo<String> nodoEstAct = nodoEst.getListaSalida().getElemento(i).getData().getVertice(nodoEst);
            int operacionesEstudiante = nodoEstAct.getListaSalida().getNumeroElementos();
            int turnoN = 0;
            int turnoM = dato.getTurnoActual();
            for (int j = 0; j != operacionesEstudiante; j++) {
                String stringOperacion = nodoEstAct.getListaSalida().getElemento(j).getData().getVertice(nodoEstAct).getDato();
                if (!stringOperacion.equals("estudiantes")) {
                    int indexOfFinalOperacion = Math.min(stringOperacion.substring(8).indexOf(" ") + 8, stringOperacion.indexOf(","));
                    String operacionReducida = stringOperacion.substring(8, indexOfFinalOperacion);
                    if (operacionReducida.equals("nacer")) {
                        int indexOfInicioTurno = stringOperacion.indexOf("turno:") + 7;
                        turnoN = Integer.parseInt(stringOperacion.substring(indexOfInicioTurno));
                    } else if (operacionReducida.equals("morir")) {
                        int indexOfInicioTurno = stringOperacion.indexOf("turno:") + 7;
                        turnoM = Integer.parseInt(stringOperacion.substring(indexOfInicioTurno));
                    }
                }
                if (turnoM - turnoN > turnosEstudianteLongevo) {
                    turnosEstudianteLongevo = turnoM - turnoN;
                    idEstudianteLongevo = Integer.parseInt(nodoEstAct.getDato().substring(10));
                }
            }
        }
        Estudiante estudianteLongevo = null;
        for (int k = 0; k != numeroEstudiantes; k ++) {
            Estudiante estudianteActual = dato.getHistorialEstudiantes().getElemento(k).getData();
            if (estudianteActual.getId() == idEstudianteLongevo) {
                estudianteLongevo = estudianteActual;
            }
        }
        return estudianteLongevo;
    }

    private static int getNumeroOperaciones (DatosJuego dato, Grafo<String> grafoOperaciones, String operacion) {
        NodoGrafo<String> nodoOperacion = grafoOperaciones.getNodoGrafo(operacion);
        return nodoOperacion.getListaSalida().getNumeroElementos();
    }
    private static Estudiante getEstudianteMasOperacion (DatosJuego dato, Grafo<String> grafoOperaciones, String operacion) {
        int idEstudianteMasOperacion = -1;
        int operacionesMaximas = -1;
        NodoGrafo<String> nodoEst = grafoOperaciones.getNodoGrafo("estudiantes");
        int numeroEst = nodoEst.getListaSalida().getNumeroElementos();
        for (int i = 0; i != numeroEst; i ++) {
            NodoGrafo<String> nodoEstAct = nodoEst.getListaSalida().getElemento(i).getData().getVertice(nodoEst);
            int operaciones = 0;
            int operacionesEst = nodoEstAct.getListaSalida().getNumeroElementos();
            for (int j = 0; j != operacionesEst; j ++) {
                if (nodoEstAct.getListaSalida().getElemento(j).getData().getVertice(nodoEstAct).getDato().equals(operacion)) {
                    operaciones += 1;
                }
            }
            if (operaciones > operacionesMaximas) {
                operacionesMaximas = operaciones;
                idEstudianteMasOperacion = Integer.parseInt(nodoEstAct.getDato().substring(10));
            }
        }
        Estudiante estMasOp = null;
        for (int k = 0; k != numeroEst; k ++) {
            Estudiante estActual = dato.getHistorialEstudiantes().getElemento(k).getData();
            if (estActual.getId() == idEstudianteMasOperacion) {
                estMasOp = estActual;
            }
        }
        return estMasOp;
    }

    private static Estudiante getEstudianteTVMaximo (DatosJuego dato, Grafo<String> grafoOperaciones) {
        int idEstudianteTVMaximo = -1;
        int TVMaximo = -1;
        NodoGrafo<String> nodoEst = grafoOperaciones.getNodoGrafo("estudiantes");
        int numEst = nodoEst.getListaSalida().getNumeroElementos();
        for (int i = 0; i != numEst; i ++) {
            NodoGrafo<String> nodoEstAct = nodoEst.getListaSalida().getElemento(i).getData().getVertice(nodoEst);
            int opEst = nodoEstAct.getListaSalida().getNumeroElementos();
            for (int j = 0; j != opEst; j ++) {
                String opAct = nodoEstAct.getListaSalida().getElemento(j).getData().getVertice(nodoEstAct).getDato();
                if (!opAct.equals("estudiantes")) {
                    int indexOfFinalOp = Math.min(opAct.substring(8).indexOf(" ") + 8, opAct.indexOf(","));
                    String opRed = opAct.substring(8, indexOfFinalOp);
                    if (opRed.equals("actualizarTV")) {
                        int indexOfInicioTV = opAct.indexOf("(") + 1;
                        int indexOfFinalTV = opAct.indexOf(")");
                        int TVOp = Integer.parseInt(opAct.substring(indexOfInicioTV, indexOfFinalTV));
                        if (TVOp > TVMaximo) {
                            idEstudianteTVMaximo = Integer.parseInt(nodoEstAct.getDato().substring(10));
                        }
                    }
                }
            }
        }
        Estudiante estTVMaximo = null;
        for (int k = 0; k != numEst; k ++) {
            Estudiante estAct = dato.getHistorialEstudiantes().getElemento(k).getData();
            if (estAct.getId() == idEstudianteTVMaximo) {
                estTVMaximo = estAct;
            }
        }
        return estTVMaximo;
    }

    private static int getTVMaximoEstudiante (Grafo<String> grafoOperaciones, Estudiante est) {
        int TVMaximo = -1;
        NodoGrafo<String> nodoEstTVMaximo = grafoOperaciones.getNodoGrafo(STR."Estudiante \{est.getId()}");
        int opEst = nodoEstTVMaximo.getListaSalida().getNumeroElementos();
        for (int j = 0; j != opEst; j ++) {
            String opAct = nodoEstTVMaximo.getListaSalida().getElemento(j).getData().getVertice(nodoEstTVMaximo).getDato();
            if (!opAct.equals("estudiantes")) {
                int indexOfFinalOp = Math.min(opAct.substring(8).indexOf(" ") + 8, opAct.indexOf(","));
                String opRed = opAct.substring(8, indexOfFinalOp);
                if (opRed.equals("actualizarTV")) {
                    int indexOfInicioTV = opAct.indexOf("(") + 1;
                    int indexOfFinalTV = opAct.indexOf(")");
                    int TVOp = Integer.parseInt(opAct.substring(indexOfInicioTV, indexOfFinalTV));
                    if (TVOp > TVMaximo) {
                        TVMaximo = TVOp;
                    }
                }
            }
        }
        return TVMaximo;
    }
}
