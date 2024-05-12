package com.example.demoJavafx.zombieStudentsLife;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrizProperties {
    private static final Logger log = LogManager.getLogger(MatrizProperties.class);
    //Modelo de datos originalAgua
    protected Matriz original;

    private IntegerProperty columnas = new SimpleIntegerProperty();
    private IntegerProperty filas = new SimpleIntegerProperty();


    public MatrizProperties(Matriz original) {
        setOriginal(original);
    }

    public void commit() {
        original.setColumnas(columnas.get());
        original.setFilas(filas.get());
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");


        log.info("Fin del método de arranque de la aplicación para elegir los parámetros\"");
    }

    public void rollback() {
        columnas.set(original.getColumnas());
        filas.set(original.getFilas());
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");


        log.info("Fin del método de arranque de la aplicación para elegir los parámetros\"");
    }

    public Matriz getOriginal() {
        return original;

    }

    public void setOriginal(Matriz original) {
        this.original = original;
        rollback(); //Se inicializan los properties.
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");


        log.info("Fin del método de arranque de la aplicación para elegir los parámetros\"");

    }

    public Property<Number> columnasProperty() {
        return columnas;

    }

    public Property<Number> filasProperty() {
        return filas;

    }

}
