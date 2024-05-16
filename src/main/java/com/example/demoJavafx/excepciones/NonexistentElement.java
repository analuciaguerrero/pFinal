package com.example.demoJavafx.excepciones;

public class NonexistentElement extends Exception{
    public NonexistentElement(String message){
        super(message);
        System.out.println("\nERROR. Elemento inexistente");
    }
    public NonexistentElement(){
        super();
    }
}

