package com.example.demoJavafx.excepciones;

public class CaminoNulo extends RuntimeException{
    public CaminoNulo(String message){
        super(message);
        System.out.println("\nERROR. El camino es nulo");
    }
    public CaminoNulo(){
        super();
    }
}
