package com.example.demoJavafx.bucleDeControl;
import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.SeleccionarPartidaController;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BucleDeControlProperties {
    private static final Logger log = LogManager.getLogger(SeleccionarPartidaController.class);

    protected BucleDeControl original;
    private DatosJuego dato;
    private IntegerProperty tableroColumnas = new SimpleIntegerProperty();
    private IntegerProperty tableroFilas = new SimpleIntegerProperty();
    private IntegerProperty turnoProperty = new SimpleIntegerProperty();

    public BucleDeControlProperties(BucleDeControl original) {
        setOriginal(original);
    }
    public BucleDeControlProperties(){}
    public void commit() {
        original.getTablero().setColumnas(tableroColumnas.get());
        original.getTablero().setFilas(tableroFilas.get());
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");
        log.info("Fin del método de arranque de la aplicación para elegir los parámetros\"");
    }

    public void rollback() {
        tableroColumnas.set(original.getTablero().getColumnas());
        tableroFilas.set(original.getTablero().getFilas());
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");
        log.info("Fin del método de arranque de la aplicación para elegir los parámetros\"");
    }

    public BucleDeControl getOriginal() {
        return original;
    }

    public void setOriginal(BucleDeControl original) {
        this.original = original;
        rollback(); // Se inicializan los valores.
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");
        log.info("Fin del método de arranque de la aplicación para elegir los parámetros\"");
    }

    public IntegerProperty tableroColumnasProperty() {
        return tableroColumnas;
    }

    public IntegerProperty tableroFilasProperty() {
        return tableroFilas;
    }

    public void actualizarTurnoProperty () {
        setTurnoProperty(dato.getTurnoActual());
    }
    public IntegerProperty getTurnoProperty() {
        return turnoProperty;
    }

    public void setTurnoProperty(Integer turnoProperty) {
        this.turnoProperty.set(turnoProperty);
    }
    public int getFilas() {
        return dato.getFilasDelTablero();
    }
    public int getColumnas() {
        return dato.getColumnasDelTablero();
    }
    public Celda getCelda(){
        return original.getCelda();
    }
}
