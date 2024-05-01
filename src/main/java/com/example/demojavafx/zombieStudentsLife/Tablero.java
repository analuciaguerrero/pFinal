package com.example.demojavafx.zombieStudentsLife;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tablero {
    ////MIRAR BIEN(REVISAR)
    GridPane tablero;
    String temaTablero;
    public ArrayList<Celda> celdas = new ArrayList<Celda>();

    public Tablero(GridPane tablero, String temaTablero) {
        this.tablero = tablero;
        this.temaTablero = temaTablero;

        makeTablero(this.tablero, temaTablero);
    }

    private void makeTablero(GridPane tableroJuego, String temaTablero){
        //int ancho = Integer.parseInt(DatosCompartidos.getAnchoMatriz());
        //int alto = Integer.parseInt(DatosCompartidos.getAltoMatriz());
        for(int i=0; i<25; i++){
            for(int j=0; j<25; j++){
                Celda celda = new Celda(i,j);
                celda.setNombre("Celda" + i + j);
                //celda.setPrefHeight(10);
                //celda.setPrefWidth(10);
                //celda.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTema(celda, temaTablero, i, j);
                //tableroJuego.add(celda,i,j,1,1);
                celdas.add(celda);
            }
        }
    }

    private void setTema(Celda celda, String temaTablero, int i, int j){
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (temaTablero){
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
        }

        if((i+j)%2==0){
            //celda.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            //celda.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

}
