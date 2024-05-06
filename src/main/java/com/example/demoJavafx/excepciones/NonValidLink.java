package com.example.demoJavafx.excepciones;

public class NonValidLink extends Exception{
    public NonValidLink(String message){
        super(message);
        System.out.println("\nERROR. Error en la introducción de los vértices de la arista.");
    }
}
