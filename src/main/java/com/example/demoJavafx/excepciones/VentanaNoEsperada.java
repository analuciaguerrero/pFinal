package com.example.demoJavafx.excepciones;

public class VentanaNoEsperada extends RuntimeException{
    public VentanaNoEsperada(String mensaje){
        super(mensaje);
        System.out.println("La ventana no existe");
    }
    public VentanaNoEsperada(){
        super();
        System.out.println("La ventana no existe");
    }
}
