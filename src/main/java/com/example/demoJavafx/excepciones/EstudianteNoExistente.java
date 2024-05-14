package com.example.demoJavafx.excepciones;

public class EstudianteNoExistente extends RuntimeException{
    public EstudianteNoExistente(String mensaje){
        super(mensaje);
        System.out.println("El estudiante no existe");
    }
    public EstudianteNoExistente(){
        super();
        System.out.println("El estudiante no existe");
    }
}
