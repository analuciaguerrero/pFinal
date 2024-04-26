package com.example.demojavafx.estudiante;
public class Estudiante {
    private int id;  //Identificacion del estudiante
    private int generacion; //Turno en el que se creo
    private int vida; //Turnos de vidas que le quedan
    private float probreproduccion; //Probabilidad de reproduccion
    private float probclonacion; //Probabilidad de clonacion
    private float probmuerte= 1-probreproduccion; //Probabilidad de muerte
    private String tipo; //Tipo de estudiante

    public Estudiante(int id, int vida, float probreproduccion, float probclonacion, String tipo){
        this.id = id;
        this.vida= vida;
        this.probreproduccion= probreproduccion;
        this.probclonacion= probreproduccion;
        this.tipo= tipo;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        id= this.id;
    }
    //public int getGeneracion(){}    Depende del turno
    //public void setGeneracion(){}

    public int getvida(){
        return this.vida;
    }
    public void setVida(int vida){
        vida= this.vida;
    }
    public float getProbreproduccion(){
        return this.probreproduccion;
    }
    public void setProbreproduccion(float probreproduccion){
        probreproduccion= this.probreproduccion;
    }
    public float getProbclonacion(){
        return this.probclonacion;
    }
    public void setProbclonacion(float probclonacion){
        probclonacion= this.probclonacion;
    }

    public void setProbmuerte(float probmuerte){
        probmuerte=this.probmuerte;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(int param){
        if(param==1){
            tipo= "basico";
        }
        else if(param==2){
            tipo="normal";
        }
        else if(param==3){
            tipo="avanzado";
        }
    }

    public void bajarvida(int numvidas){
        int newvida= this.getvida()-numvidas;
        setVida(newvida);
    }
    public void subirvida(int numvidas){
        int newvida= this.getvida()+numvidas;
        setVida(newvida);
    }
    public void bajarprobreproduccion(float prob){
        float newprobreproduccion = (float) (this.getProbreproduccion()-prob);
        setProbreproduccion(newprobreproduccion);
    }
    public void bajarprobclonacion(float prob){
        float newprobclonacion = (float) (this.getProbclonacion()-prob);
        setProbreproduccion(newprobclonacion);
    }
    public void subirprobreproduccion(float prob){
        float newprobreproduccion = (float) (this.getProbreproduccion()+prob);
        setProbreproduccion(newprobreproduccion);
    }
    public void subirprobclonacion(float prob){
        float newprobclonacion = (float) (this.getProbclonacion()+prob);
        setProbreproduccion(newprobclonacion);
    }
    public void mover(){  //En funcion del tablero y recursos
        if (getTipo()=="basico"){}
        else if(getTipo()=="normal"){}
        else if(getTipo()=="avanzado"){}
    }
    public void subirtipo(){
        if (getTipo()=="basico"){
            setTipo(2);  //Tipo normal
        }
        else if(getTipo()=="normal"){
            setTipo(3); //Tipo avanzado
        }
        else if (getTipo()=="avanzado"){
            setTipo(3); //Tipo maximo
        }
    }

    public void matar(){ //muerte instantanea
        setProbmuerte(1);
    }
}
