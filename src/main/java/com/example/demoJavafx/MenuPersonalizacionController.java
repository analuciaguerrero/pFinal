package com.example.demoJavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class MenuPersonalizacionController {

    @FXML
    private Spinner<Integer> aguaTurnosRestantesSpinner;

    @FXML
    private Spinner<Integer> aguaAumentoVidaSpinner;

    @FXML
    private Spinner<Float> aguaProbabilidadSpinner;

    @FXML
    private Spinner<Integer> bibliotecaTurnosRestantesSpinner;

    @FXML
    private Spinner<Float> bibliotecaAumentoClonSpinner;

    @FXML
    private Spinner<Float> bibliotecaProbabilidadSpinner;

    @FXML
    private Spinner<Integer> comidaTurnosRestantesSpinner;

    @FXML
    private Spinner<Integer> comidaAumentoVidaSpinner;

    @FXML
    private Spinner<Float> comidaProbabilidadSpinner;

    @FXML
    private Spinner<Integer> montañaTurnosRestantesSpinner;

    @FXML
    private Spinner<Integer> montañaDisminuciónVidaSpinner;

    @FXML
    private Spinner<Float> montañaProbabilidadSpinner;

    @FXML
    private Spinner<Integer> pozoTurnosRestantesSpinner;

    @FXML
    private Spinner<Float> pozoProbabilidadSpinner;

    @FXML
    private Spinner<Integer> tesoroTurnosRestantesSpinner;

    @FXML
    private Spinner<Float> tesoroAumentoRepSpinner;

    @FXML
    private Spinner<Float> tesoroProbabilidadSpinner;

    @FXML
    private TextField tamañoTableroTextField;

    @FXML
    protected void guardarConfiguracion(ActionEvent event) {
        int aguaTurnosRestantes = aguaTurnosRestantesSpinner.getValue();
        int aguaAumentoVida = aguaAumentoVidaSpinner.getValue();
        float aguaProbabilidad = aguaProbabilidadSpinner.getValue();

        int bibliotecaTurnosRestantes = bibliotecaTurnosRestantesSpinner.getValue();
        float bibliotecaAumentoClon = bibliotecaAumentoClonSpinner.getValue();
        float bibliotecaProbabilidad = bibliotecaProbabilidadSpinner.getValue();

        int comidaTurnosRestantes = comidaTurnosRestantesSpinner.getValue();
        int comidaAumentoVida = comidaAumentoVidaSpinner.getValue();
        float comidaProbabilidad = comidaProbabilidadSpinner.getValue();

        int montañaTurnosRestantes = montañaTurnosRestantesSpinner.getValue();
        int montañaDisminuciónVida = montañaDisminuciónVidaSpinner.getValue();
        float montañaProbabilidad = montañaProbabilidadSpinner.getValue();

        int pozoTurnosRestantes = pozoTurnosRestantesSpinner.getValue();
        float pozoProbabilidad = pozoProbabilidadSpinner.getValue();

        int tesoroTurnosRestantes = tesoroTurnosRestantesSpinner.getValue();
        float tesoroAumentoRep = tesoroAumentoRepSpinner.getValue();
        float tesoroProbabilidad = tesoroProbabilidadSpinner.getValue();

        String tamañoTablero = tamañoTableroTextField.getText();

        // Aquí podrías guardar la configuración en un archivo XML, en una base de datos, etc.
        System.out.println("Configuración guardada:");
        System.out.println("Agua - Turnos Restantes: " + aguaTurnosRestantes + ", Aumento de Vida: " + aguaAumentoVida + ", Probabilidad: " + aguaProbabilidad);
        System.out.println("Biblioteca - Turnos Restantes: " + bibliotecaTurnosRestantes + ", Aumento de Clon: " + bibliotecaAumentoClon + ", Probabilidad: " + bibliotecaProbabilidad);
        System.out.println("Comida - Turnos Restantes: " + comidaTurnosRestantes + ", Aumento de Vida: " + comidaAumentoVida + ", Probabilidad: " + comidaProbabilidad);
        System.out.println("Montaña - Turnos Restantes: " + montañaTurnosRestantes + ", Disminución de Vida: " + montañaDisminuciónVida + ", Probabilidad: " + montañaProbabilidad);
        System.out.println("Pozo - Turnos Restantes: " + pozoTurnosRestantes + ", Probabilidad: " + pozoProbabilidad);
        System.out.println("Tesoro - Turnos Restantes: " + tesoroTurnosRestantes + ", Aumento de Rep: " + tesoroAumentoRep + ", Probabilidad: " + tesoroProbabilidad);
        System.out.println("Tamaño del Tablero: " + tamañoTablero);
    }
}