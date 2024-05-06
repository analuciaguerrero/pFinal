package com.example.demoJavafx;
import javafx.beans.property.*;

public class VentanaPrincipalDataProperties {
    protected VentanaPrincipalDataModel original;
    private IntegerProperty velocidad = new SimpleIntegerProperty();
    private IntegerProperty vida = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty apellido= new SimpleStringProperty();

    public VentanaPrincipalDataProperties(VentanaPrincipalDataModel original) {
        setOriginal(original);
    }
    public void commit(){
        original.setVelocidad(velocidad.get());
        original.setVida(vida.get());
        original.setNombre(nombre.get());
        original.setApellido(apellido.get());
    }


    public void rollback(){
        velocidad.set(original.getVelocidad());
        vida.set(original.getVida());
        nombre.set((original.getNombre()));
        apellido.set((original.getApellido()));
    }
    public void setOriginal(VentanaPrincipalDataModel original){
        this.original=original;
        rollback();
    }
    public Property<Number> velocidadProperty() {
        return velocidad;
    }

    public Property<Number> vidaProperty() {
        return vida;
    }

    public Property<String> nombreProperty() {
        return nombre;
    }
    public Property<String> appelidoProperty(){return apellido;}
}
