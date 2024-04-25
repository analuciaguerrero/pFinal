package com.example.demojavafx.matriz;
import com.example.demojavafx.ParameterDataModel;
import javafx.beans.property.*;

public class ParameterDataModelMatrizProperties {
    protected ParameterDataModelMatriz original;

    private IntegerProperty columnas = new SimpleIntegerProperty();
    private IntegerProperty filas = new SimpleIntegerProperty();


    public ParameterDataModelMatrizProperties(ParameterDataModelMatriz original){
        setOriginal(original);
    }

    public void commit(){
        original.setColumna(columnas.get());
        original.setFila(filas.get());
    }

    public void rollback(){
        columnas.set(original.getColumna());
        filas.set(original.getFila());
    }

    public ParameterDataModelMatriz getOriginal(){
        return original;
    }

    public void setOriginal(ParameterDataModelMatriz original){
        this.original = original;
        rollback(); //Se inicializan los properties.

    }

    public Property<Number> columnasProperty() {
        return columnas;
    }

    public Property<Number> filasProperty() {
        return filas;
    }
}
