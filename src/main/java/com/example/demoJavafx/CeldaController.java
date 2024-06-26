package com.example.demoJavafx;

import com.example.demoJavafx.entorno.*;
import com.example.demoJavafx.estudiante.*;
import com.example.demoJavafx.tablero.Celda;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.text.Font;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class CeldaController implements Initializable {
    private static final Logger log = LogManager.getLogger();
    private Parent root;
    private boolean isListenerActive = true;
    private DatosJuego dato;
    private Celda celda;

    @FXML
    private VBox estudiantesVBox;
    @FXML
    private VBox recursosVBox;
    @FXML
    private Label alertaCasillaLabel;
    @FXML
    private ChoiceBox<String> estudiantesAddBox;
    @FXML
    private ChoiceBox<String> recursosAddBox;
    @FXML
    private Label elementoLabel;
    @FXML
    private Button botonQuitar;

    private final String[] tiposEstudiantes = {"+ Básico", "+ Normal", "+ Avanzado"};
    private final String[] tiposRecursos = {"+ Agua", "+ Comida", "+ Montaña", "+ Tesoro", "+ Biblioteca", "+ Pozo"};

    public CeldaController() {}

    public CeldaController(DatosJuego dato, Celda celda) throws IOException {
        this.dato = dato;
        this.celda = celda;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Celda.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        estudiantesVBox = (VBox) ((GridPane) ((VBox) root).getChildren().get(0)).getChildren().get(1);
        recursosVBox = (VBox) ((GridPane) ((VBox) root).getChildren().get(0)).getChildren().get(2);
        this.root = root;
        if (!celda.getListaEstudiantes().isVacia()) {
            for (int i = 0; i != celda.getListaEstudiantes().getNumeroElementos(); i++) {
                addEstudiantePrinc(celda.getListaEstudiantes().getElemento(i).getData().getTipo(), false, celda.getListaEstudiantes().getElemento(i).getData());
            }
        }
        if (!celda.getListaRecursos().isVacia()) {
            for (int i = 0; i != celda.getListaRecursos().getNumeroElementos(); i++) {
                addRecursoPrinc(celda.getListaRecursos().getElemento(i).getData().getTipo(), false, celda.getListaRecursos().getElemento(i).getData());
            }
        }
    }

    public Parent getRoot() {
        return root;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            alertaCasillaLabel.setText(String.format("Casilla %d, %d", celda.getPosicionN(), celda.getPosicionM()));
            estudiantesAddBox.getItems().setAll(tiposEstudiantes);
            recursosAddBox.getItems().setAll(tiposRecursos);
            estudiantesAddBox.setStyle("-fx-font-family: 'Bookman Old Style'; -fx-font-size: 12px;");
            recursosAddBox.setStyle("-fx-font-family: 'Bookman Old Style'; -fx-font-size: 12px;");
            estudiantesAddBox.setValue("Estudiante");
            recursosAddBox.setValue("Recurso");
            estudiantesAddBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (isListenerActive) {
                    this.addEstudiante(newValue);
                }
            });
            recursosAddBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (isListenerActive) {
                    this.addRecurso(newValue);
                }
            });
        } catch (Exception e) {
            log.trace("Se identificó una excepción pero no se llevó a cabo ninguna acción");
        }
    }

    public void addEstudiante(String tipoEstudiante) {
        switch (tipoEstudiante) {
            case "+ Básico":
                addEstudiante(EstudianteBasico.class);
                break;
            case "+ Normal":
                addEstudiante(EstudianteNormal.class);
                break;
            case "+ Avanzado":
                addEstudiante(EstudianteAvanzado.class);
                break;
            default:
                log.error("Se ha intentado incluir un tipo de estudiante que no estaba previsto");
        }
    }

    public <T extends Estudiante> void addEstudiante(Class<T> tipoEstudiante) {
        try {
            isListenerActive = false;
            if (celda.getListaEstudiantes().getNumeroElementos() >= 3) {
                alertaCasillaLabel.setText("¡Ya hay 3 estudiantes en la celda!");
                PauseTransition pausa = new PauseTransition(Duration.millis(2500));
                pausa.setOnFinished(event -> alertaCasillaLabel.setText(String.format("Casilla %d, %d", celda.getPosicionN(), celda.getPosicionM())));
                pausa.play();
                log.debug("Se ha intentado establecer un estudiante cuando ya habían 3 en la celda");
            } else {
                try {
                    addEstudiantePrinc(tipoEstudiante, true, null);
                } catch (Exception e) {
                    log.error("El tipo de estudiante no es válido");
                }
            }
            estudiantesAddBox.setValue("Estudiante");
            estudiantesAddBox.getSelectionModel().clearSelection();
            isListenerActive = true;
        } catch (Exception e) {
            log.warn("Excepción no esperada al añadir el estudiante");
        }
    }

    public <T extends Estudiante> void addEstudiantePrinc(Class<?> claseEstudiante, boolean nuevoEstudiante, Estudiante estudianteAñadir) {
        try {
            T estudiante;
            if (nuevoEstudiante) {
                Constructor<?> constructor = claseEstudiante.getConstructor(int.class, int.class, int.class, double.class, double.class, int.class);
                int id;
                if (dato.getHistorialEstudiantes().isVacia()) {
                    id = 1;
                } else {
                    id = dato.getHistorialEstudiantes().getUltimo().getData().getId() + 1;
                }
                estudiante = (T) constructor.newInstance(id, 1, dato.getTurnosVidaIniciales(), dato.getProbReproduccionEstudiante(), dato.getProbClonacionEstudiante(), dato.getTurnoActual());
                celda.agregarEstudiante(estudiante, true);
            } else {
                estudiante = (T) estudianteAñadir;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ElementoCelda.fxml"));
            loader.setController(this);
            HBox estudianteCelda = loader.load();
            Label estudianteLabel = (Label) estudianteCelda.lookup("#elementoLabel");
            estudianteLabel.setFont(new Font("Bookman Old Style", 12));
            String tipoEstudiante = claseEstudiante.getSimpleName().replace("Estudiante", "");
            estudianteLabel.textProperty().bind(estudiante.getTiempoDeVidaProperty().asString(tipoEstudiante + ": Vida: %d Id: " + estudiante.getId() + " Gen: " + estudiante.getGeneracion()));
            Button botonQuitar = (Button) estudianteCelda.lookup("#botonQuitar");
            botonQuitar.setOnAction(this::delEstudiante);
            estudiantesVBox.getChildren().add(estudianteCelda);
        } catch (Exception e) {
            log.error("No se ha creado una instancia del tipo solicitado de estudiante", e);
        }
    }

    public void delEstudiante(ActionEvent event) {
        int i = 0;
        boolean eliminado = false;
        while (i != dato.getEstudiantes().getNumeroElementos() && !eliminado) {
            String labelEstudianteText = ((Label) ((HBox) ((Button) event.getSource()).getParent().getParent()).getChildren().get(0)).getText();
            int indexOfIdLabel = labelEstudianteText.indexOf("Id:") + 4;
            int idLabel = (int) labelEstudianteText.charAt(indexOfIdLabel) - '0';
            int idEstudiante = dato.getEstudiantes().getElemento(i).getData().getId();
            if (idEstudiante == idLabel) {
                celda.eliminarEstudiante(dato.getEstudiantes().getElemento(i).getData());
                estudiantesVBox.getChildren().remove(((Button) event.getSource()).getParent().getParent());
                eliminado = true;
            }
            i++;
        }
    }

    public void addRecurso(String tipoRecurso) {
        try {
            isListenerActive = false;
            if (celda.getListaRecursos().getNumeroElementos() >= 3) {
                alertaCasillaLabel.setText("¡Ya hay 3 recursos!");
                PauseTransition pausa = new PauseTransition(Duration.millis(2500));
                pausa.setOnFinished(event -> alertaCasillaLabel.setText(String.format("Casilla %d, %d", celda.getPosicionN(), celda.getPosicionM())));
                pausa.play();
                log.debug("Se ha intentado crear un recurso cuando ya habían 3 en la celda");
            } else {
                switch (tipoRecurso) {
                    case "+ Agua":
                        addRecursoPrinc(Agua.class, true, null);
                        break;
                    case "+ Comida":
                        addRecursoPrinc(Comida.class, true, null);
                        break;
                    case "+ Montaña":
                        addRecursoPrinc(Montaña.class, true, null);
                        break;
                    case "+ Tesoro":
                        addRecursoPrinc(Tesoro.class, true, null);
                        break;
                    case "+ Biblioteca":
                        addRecursoPrinc(Biblioteca.class, true, null);
                        break;
                    case "+ Pozo":
                        addRecursoPrinc(Pozo.class, true, null);
                        break;
                    default:
                        log.error("Excepción no esperada al añadir el recurso");
                }
            }
            recursosAddBox.setValue("Recurso");
            recursosAddBox.getSelectionModel().clearSelection();
            isListenerActive = true;
        } catch (Exception e) {
            log.warn("Excepción no esperada al añadir el recurso");
        }
    }

    private <T extends Recursos> void addRecursoPrinc(Class<?> claseRecurso, boolean nuevoRecurso, T recursoAñadir) {
        try {
            T recurso;
            if (nuevoRecurso) {
                Constructor<?> constructor = claseRecurso.getConstructor(int.class, DatosJuego.class);
                int id;
                if (dato.getHistorialRecursos().isVacia()) {
                    id = 1;
                } else {
                    id = dato.getHistorialRecursos().getUltimo().getData().getId() + 1;
                }
                recurso = (T) constructor.newInstance(id, dato);
                celda.agregarRecurso(recurso, true);
            } else {
                recurso = recursoAñadir;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ElementoCelda.fxml"));
            loader.setController(this);
            HBox cajaRecurso = loader.load();
            Label labelRecurso = (Label) cajaRecurso.lookup("#elementoLabel");
            labelRecurso.setFont(new Font("Bookman Old Style", 12));
            String tipoRecurso = claseRecurso.getSimpleName();
            labelRecurso.textProperty().bind(recurso.getTurnosRestantesProperty().asString(String.format("%s: Vida: %d Id: %d", tipoRecurso, recurso.getTurnosRestantes(), recurso.getId())));
            Button botonQuitar = (Button) cajaRecurso.lookup("#botonQuitar");
            botonQuitar.setOnAction(this::delRecurso);
            recursosVBox.getChildren().add(cajaRecurso);
        } catch (Exception e) {
            log.error("No se ha creado una instancia del tipo solicitado de recurso", e);
        }
    }

    public void delRecurso(ActionEvent event) {
        String labelRecursoText = ((Label) ((HBox) ((Button) event.getSource()).getParent().getParent()).getChildren().get(0)).getText();
        int indexOfId = labelRecursoText.indexOf("Id:") + 4;
        int id = labelRecursoText.charAt(indexOfId) - '0';
        int i = 0;
        boolean eliminado = false;
        while (i != dato.getRecursos().getNumeroElementos() && !eliminado) {
            int idRecurso = dato.getRecursos().getElemento(i).getData().getId();
            if (idRecurso == id) {
                celda.eliminarRecurso(dato.getRecursos().getElemento(i).getData());
                recursosVBox.getChildren().remove(((Button) event.getSource()).getParent().getParent());
                eliminado = true;
            }
            i++;
        }
    }
}