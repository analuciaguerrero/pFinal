package com.example.demoJavafx;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TableroController extends GridPane {
    @FXML
    private GridPane tableroGridPane;

    public void crearTablero(int ancho, int largo) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                // Crear celda
                Rectangle celda = new Rectangle(50, 50);
                celda.setFill(Color.WHITE);
                celda.setStroke(Color.BLACK);

                // Añadir celda
                tableroGridPane.add(celda, i, j);
            }
        }
    }
}
