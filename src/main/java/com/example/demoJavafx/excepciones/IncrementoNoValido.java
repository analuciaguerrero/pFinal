package com.example.demoJavafx.excepciones;

public class IncrementoNoValido extends IllegalArgumentException {
    public IncrementoNoValido(String mensaje){
        super(mensaje);
        System.out.println("El incremento no es válido");
    }
    public IncrementoNoValido(){
        super();
        System.out.println("El incremento no es válido");
    }
}
