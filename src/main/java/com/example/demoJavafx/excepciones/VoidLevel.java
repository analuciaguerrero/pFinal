package com.example.demoJavafx.excepciones;

public class VoidLevel extends Exception{
    public VoidLevel(String message){
        super(message);
        System.out.println("\nERROR. Ha introducido un valor numérico menor que 0 o el nivel pedido está vacío");
    }
}
