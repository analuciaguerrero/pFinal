package com.example.demojavafx.excepciones;

public class NonexistentElement extends Exception{
    public NonexistentElement(String message){
        super(message);
        System.out.println("\nERROR. Elemento inexistente");
    }
}

