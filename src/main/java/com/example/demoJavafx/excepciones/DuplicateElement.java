package com.example.demoJavafx.excepciones;

public class DuplicateElement extends Exception{
    public DuplicateElement(String message){
        super(message);
        System.out.println("\nERROR. El elemento a añadir ya existe en el arbol");
    }
}

