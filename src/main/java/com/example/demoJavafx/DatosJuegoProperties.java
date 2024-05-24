package com.example.demoJavafx;
import com.example.demoJavafx.excepciones.VentanaNoEsperada;
import javafx.beans.property.*;
import javafx.scene.control.Tab;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DatosJuegoProperties {

    protected DatosJuego dato;
    private IntegerProperty FilasDelTablero = new SimpleIntegerProperty();
    private IntegerProperty ColumnasDelTablero = new SimpleIntegerProperty();

    private ObjectProperty<Integer> TurnosVidaIniciales = new SimpleIntegerProperty().asObject();
    private ObjectProperty<Integer> TurnosRestantesAgua = new SimpleIntegerProperty().asObject();
    private ObjectProperty<Integer> TurnosRestantesComida = new SimpleIntegerProperty().asObject();
    private ObjectProperty<Integer> TurnosRestantesMontana = new SimpleIntegerProperty().asObject();
    private ObjectProperty<Integer> TurnosRestantesBiblioteca = new SimpleIntegerProperty().asObject();
    private ObjectProperty<Integer> TurnosRestantesTesoro = new SimpleIntegerProperty().asObject();
    private ObjectProperty<Integer> TurnosRestantesPozo = new SimpleIntegerProperty().asObject();

    private DoubleProperty ProbReproEstudiante = new SimpleDoubleProperty();
    private DoubleProperty ProbClonEstudiante= new SimpleDoubleProperty();
    private DoubleProperty ProbMejoraToNormal = new SimpleDoubleProperty();
    private DoubleProperty ProbMejoraToAvanzado = new SimpleDoubleProperty();
    private DoubleProperty ProbAgua = new SimpleDoubleProperty();
    private DoubleProperty ProbComida = new SimpleDoubleProperty();
    private DoubleProperty ProbMontaña = new SimpleDoubleProperty();
    private DoubleProperty ProbTesoro = new SimpleDoubleProperty();
    private DoubleProperty ProbBiblioteca = new SimpleDoubleProperty();
    private DoubleProperty ProbPozo = new SimpleDoubleProperty();
    private DoubleProperty AumentoTurnosAgua = new SimpleDoubleProperty();
    private DoubleProperty AumentoTurnosComida = new SimpleDoubleProperty();
    private DoubleProperty ReduccionTurnosMontaña = new SimpleDoubleProperty();
    private DoubleProperty AumentoProbRepro = new SimpleDoubleProperty();
    private DoubleProperty AumentoProbClon = new SimpleDoubleProperty();
    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);

    public DatosJuegoProperties(DatosJuego dato){
        setDatoInitialize(dato);
    }
    public DatosJuego getDato(){
        return dato;
    }

    public void setDato(DatosJuego dato) {
        this.dato = dato;
    }

    public void setDatoInitialize(DatosJuego dato){
        this.dato = dato;
        rollback(null);
    }
    public void commit(){
        dato.setFilasDelTablero(FilasDelTablero.get());
        dato.setColumnasDelTablero(ColumnasDelTablero.get());
        dato.setTurnosVidaIniciales(TurnosVidaIniciales.get());
        dato.setTurnosRestantesAgua(TurnosRestantesAgua.get());
        dato.setTurnosRestantesComida(TurnosRestantesComida.get());
        dato.setTurnosRestantesMontana(TurnosRestantesMontana.get());
        dato.setTurnosRestantesBiblioteca(TurnosRestantesBiblioteca.get());
        dato.setTurnosRestantesTesoro(TurnosRestantesTesoro.get());
        dato.setTurnosRestantesPozo(TurnosRestantesPozo.get());
        dato.setProbReproduccionEstudiante(ProbReproEstudiante.get());
        dato.setProbClonacionEstudiante(ProbClonEstudiante.get());
        dato.setProbMejorarANormal(ProbMejoraToNormal.get());
        dato.setProbMejorarAAvanzado(ProbMejoraToAvanzado.get());
        dato.setProbAgua(ProbAgua.get());
        dato.setProbComida(ProbComida.get());
        dato.setProbMontaña(ProbMontaña.get());
        dato.setProbTesoro(ProbTesoro.get());
        dato.setProbBiblioteca(ProbBiblioteca.get());
        dato.setProbPozo(ProbPozo.get());
        dato.setAumentoVidaAgua(AumentoTurnosAgua.get());
        dato.setAumentoVidaComida(AumentoTurnosComida.get());
        dato.setReduccionVidaMontaña(ReduccionTurnosMontaña.get());
        dato.setAumentoProbReproduccion(AumentoProbRepro.get());
        dato.setAumentoProbClonacion(AumentoProbClon.get());
    }
    private void rollbackEstudiantes() {
        TurnosVidaIniciales.set(dato.getTurnosVidaIniciales());
        ProbReproEstudiante.set(dato.getProbReproduccionEstudiante());
        ProbClonEstudiante.set(dato.getProbClonacionEstudiante());
        ProbMejoraToNormal.set(dato.getProbMejorarANormal());
        ProbMejoraToAvanzado.set(dato.getProbMejorarAAvanzado());
    }

    private void rollbackRecursos () {
        ProbAgua.set(dato.getProbAgua());
        ProbComida.set(dato.getProbComida());
        ProbMontaña.set(dato.getProbMontaña());
        ProbTesoro.set(dato.getProbTesoro());
        ProbBiblioteca.set(dato.getProbBiblioteca());
        ProbPozo.set(dato.getProbPozo());
        AumentoTurnosAgua.set(dato.getAumentoVidaAgua());
        AumentoTurnosComida.set(dato.getAumentoVidaComida());
        ReduccionTurnosMontaña.set(dato.getReduccionVidaMontaña());
        AumentoProbRepro.set(dato.getAumentoProbReproduccion());
        AumentoProbClon.set(dato.getAumentoProbClonacion());
        TurnosRestantesAgua.set(dato.getTurnosRestantesAgua());
        TurnosRestantesComida.set(dato.getTurnosRestantesComida());
        TurnosRestantesMontana.set(dato.getTurnosRestantesMontana());
        TurnosRestantesBiblioteca.set(dato.getTurnosRestantesBiblioteca());
        TurnosRestantesTesoro.set(dato.getTurnosRestantesTesoro());
        TurnosRestantesPozo.set(dato.getTurnosRestantesPozo());
    }

    private void rollbackTablero () {
        FilasDelTablero.set(dato.getFilasDelTablero());
        ColumnasDelTablero.set(dato.getColumnasDelTablero());
    }
    public void rollback(Tab tab){
        try {
            if (tab == null) {
                rollbackEstudiantes();
                rollbackRecursos();
                rollbackTablero();
            } else {
                switch (tab.getText()) {
                    case "Estudiantes":
                        rollbackEstudiantes();
                        break;
                    case "Recursos":
                        rollbackRecursos();
                        break;
                    case "Tablero":
                        rollbackTablero();
                        break;
                    default:
                        throw new VentanaNoEsperada();
                }
            }
        } catch (VentanaNoEsperada e) {
            log.error("Se ha intentado restablecer los valores de una pestaña no prevista");
        }
    }
    public ObjectProperty<Integer> TurnosVidaInicialesProperty() {
        return TurnosVidaIniciales;
    }

    public Property<Number> ProbReproEstudianteProperty() {
        return ProbReproEstudiante;
    }

    public Property<Number> ProbClonEstudianteProperty() {
        return ProbClonEstudiante;
    }

    public Property<Number> ProbMejorarANormalProperty() { return ProbMejoraToNormal; }

    public Property<Number> ProbMejorarAAvanzadoProperty() { return ProbMejoraToAvanzado; }

    public Property<Number> ProbAguaProperty() {
        return ProbAgua;
    }

    public Property<Number> ProbComidaProperty() {
        return ProbComida;
    }

    public Property<Number> ProbMontañaProperty() {
        return ProbMontaña;
    }

    public Property<Number> ProbTesoroProperty() {
        return ProbTesoro;
    }

    public Property<Number> ProbBibliotecaProperty() {
        return ProbBiblioteca;
    }

    public Property<Number> ProbPozoProperty() {
        return ProbPozo;
    }

    public ObjectProperty<Integer> TurnosRestantesAguaProperty() {
        return TurnosRestantesAgua;
    }

    public ObjectProperty<Integer> TurnosRestantesComidaProperty() {
        return TurnosRestantesComida;
    }

    public ObjectProperty<Integer> TurnosRestantesMontanaProperty() {
        return TurnosRestantesMontana;
    }

    public ObjectProperty<Integer> TurnosRestantesBibliotecaProperty() {
        return TurnosRestantesBiblioteca;
    }

    public ObjectProperty<Integer> TurnosRestantesTesoroProperty() {
        return TurnosRestantesTesoro;
    }

    public ObjectProperty<Integer> TurnosRestantesPozoProperty() {
        return TurnosRestantesPozo;
    }

    public Property<Number> AumentoTurnosAguaProperty() {
        return AumentoTurnosAgua;
    }

    public Property<Number> AumentoTurnosComidaProperty() {
        return AumentoTurnosComida;
    }

    public Property<Number> ReduccionTurnosMontañaProperty() {
        return ReduccionTurnosMontaña;
    }

    public Property<Number> AumentoProbReproProperty() {
        return AumentoProbRepro;
    }

    public Property<Number> AumentoProbClonProperty() {
        return AumentoProbClon;
    }

    public Property<Number> FilasDelTableroProperty() {
        return FilasDelTablero;
    }

    public Property<Number> ColumnasDelTableroProperty() {
        return ColumnasDelTablero;
    }
}
