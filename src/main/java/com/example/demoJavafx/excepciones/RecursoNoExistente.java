package com.example.demoJavafx.excepciones;

public class RecursoNoExistente extends RuntimeException{
    public RecursoNoExistente(String mensaje){
        super(mensaje);
        System.out.println("El recurso no existe");
    }
    public RecursoNoExistente(){
        super();
        System.out.println("El recurso no existe");
    }
}

