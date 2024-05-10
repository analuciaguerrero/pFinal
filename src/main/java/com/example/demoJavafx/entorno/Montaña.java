package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;

public class Montaña extends Recursos{
    private int reduccionVida;
    private static double probMontaña;

    public Montaña(int posicionN, int posicionM, int turnosRestantes, double probRecurso, int reduccionVida, double probMontaña) {
        super(posicionN, posicionM, turnosRestantes, probRecurso);
        this.reduccionVida = reduccionVida;
        Montaña.probMontaña = probMontaña;
    }
    public Montaña(){}
    public Montaña(double probMontaña){
        Montaña.probMontaña = probMontaña;
    }
    public int getReduccionVida(){
        return reduccionVida;
    }
    public void setReduccionVida(int reduccionVida){
        this.reduccionVida = reduccionVida;
    }
    public static double getProbMontaña(){
        return probMontaña;
    }
    public void setProbMontaña(double probMontaña){
        Montaña.probMontaña = probMontaña;
    }

    @Override
    public void aplicarEfecto(Estudiante estudiante) {
        estudiante.setTiempoDeVida(estudiante.getTiempoDeVida() - reduccionVida);
    }
}

