package com.example.demoJavafx.excepciones;
public class ExistentVertix extends Exception{
    public ExistentVertix(String message){
        super(message);
        System.out.println("ERROR. El vertice que va a añadir ya existe en el Grafo.");
    }
}
