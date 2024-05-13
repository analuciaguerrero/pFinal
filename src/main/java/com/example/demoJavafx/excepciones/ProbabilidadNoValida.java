package com.example.demoJavafx.excepciones;

public class ProbabilidadNoValida extends IllegalArgumentException{
    public ProbabilidadNoValida(String mensaje){
        super(mensaje);
        System.out.println("La probabilidad introducida no es válida");
    }
    public ProbabilidadNoValida(){
        super();
        System.out.println("La probabilidad introducida no es válida");
    }
}
