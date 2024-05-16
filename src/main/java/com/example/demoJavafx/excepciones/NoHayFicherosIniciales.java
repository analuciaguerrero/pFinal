package com.example.demoJavafx.excepciones;

public class NoHayFicherosIniciales extends Exception{
    public NoHayFicherosIniciales(String mensaje){
        super(mensaje);
        System.out.println("No hay ficheros iniciales");
    }
    public NoHayFicherosIniciales(){
        super();
        System.out.println("No hay ficheros iniciales");
    }
}
