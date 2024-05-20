package com.example.demoJavafx.excepciones;

public class PadresException extends RuntimeException {
    public PadresException(String mensaje){
        super(mensaje);
        System.out.println("Se ha producido una excepción ya que el número de padres es distinto de 2");
    }
    public PadresException(){
        super();
        System.out.println("Se ha producido una excepción ya que el número de padres es distinto de 2");
    }
}
