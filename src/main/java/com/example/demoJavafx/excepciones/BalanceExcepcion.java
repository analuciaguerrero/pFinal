package com.example.demoJavafx.excepciones;

public class BalanceExcepcion extends RuntimeException{
    public BalanceExcepcion(String mensaje){
        super(mensaje);
        System.out.println("No se ha podido equilibrar el balance ya que es más de dos de altura");
    }
    public BalanceExcepcion(){
        super();
        System.out.println("No se ha podido equilibrar el balance ya que es más de dos de altura");
    }
}
