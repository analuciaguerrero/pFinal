package com.example.demoJavafx;

import com.example.demoJavafx.bucleDeControl.BucleDeControl;
import com.example.demoJavafx.bucleDeControl.BucleDeControlProperties;
import com.example.demoJavafx.entorno.ParameterAguaProperties;
import com.example.demoJavafx.entorno.ParameterBibliotecaProperties;
import com.example.demoJavafx.entorno.ParameterComidaProperties;
import com.example.demoJavafx.entorno.ParameterMontanaProperties;
import com.example.demoJavafx.entorno.ParameterPozoProperties;
import com.example.demoJavafx.entorno.ParameterTesoroProperties;
import com.example.demoJavafx.estudiante.EstudianteProperties;
import com.example.demoJavafx.entorno.RecursosProperties;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class JuegoController implements Initializable {


    private Stage scene;

    private BucleDeControlProperties matrizModel;

    protected Celda[][] matriz;

    protected ListaEnlazada<Button> listaButton;

    protected RecursosProperties recursosModel;
    protected ParameterAguaProperties aguaModel;
    protected ParameterBibliotecaProperties bibliotecaModel;
    protected ParameterComidaProperties comidaModel;
    protected ParameterMontanaProperties montanaModel;
    protected ParameterPozoProperties pozoModel;
    protected ParameterTesoroProperties tesoroModel;
    //Individuos model
    protected EstudianteProperties individuoModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }





    protected void updateGUIwithModel() {
    }



    public void loadUserData(RecursosProperties parametrosRecursos, ParameterAguaProperties parametrosAgua,
                             ParameterBibliotecaProperties parametrosBiblioteca, ParameterComidaProperties parametrosComida,
                             ParameterMontanaProperties parametrosMontana, ParameterPozoProperties parametrosPozo,
                             ParameterTesoroProperties parametrosTesoro, EstudianteProperties parametrosInd,
                             BucleDeControlProperties parametroMatriz) {
        this.recursosModel = parametrosRecursos;
        this.aguaModel = parametrosAgua;
        this.bibliotecaModel = parametrosBiblioteca;
        this.comidaModel = parametrosComida;
        this.montanaModel = parametrosMontana;
        this.pozoModel = parametrosPozo;
        this.tesoroModel = parametrosTesoro;
        this.individuoModel = parametrosInd;
        this.matrizModel = parametroMatriz;


    }

    public void setStage(Stage s) {
        this.scene = s;
    }

    public void guardarButton() {

        matrizModel.commit();

    }
    public void cerrarButton(){
        scene.close();
    }
    public void cancelarButton(){

        matrizModel.rollback();
    }



    public void onButtonInfo(int f, int c,BucleDeControlProperties matrizModel) {
        //se llama desde recursos controller
        System.out.println("entra?");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("info-celda.fxml"));
        try {
            System.out.println("funcion");
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            stage.setScene(scene);
            stage.setTitle("Ver celda {fila: " + c +" columna: "+ f+"}");


            Label label = new Label(matrizModel.original.matriz[c][f].toString());

            VBox vbox = new VBox(label);

            ScrollPane s = new ScrollPane(vbox);

            Scene scene2 = new Scene(s,900,600);


            stage.setScene(scene2);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearJuego(BucleDeControlProperties modeloMatriz){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("juego.fxml"));
        try {
            Scene scene1 = new Scene(fxmlLoader.load(), 600, 400);
            int turno = modeloMatriz.original.turno;
            stage.setTitle("juego en turno: "+ turno);
            stage.setScene(scene1);

            JuegoController p = fxmlLoader.getController();
            p.loadUserData(recursosModel,aguaModel,bibliotecaModel,comidaModel,montanaModel,pozoModel,tesoroModel,individuoModel,matrizModel);


            int filas = modeloMatriz.getFilas();
            int columnas = modeloMatriz.getColumnas();

            System.out.println(filas);
            System.out.println(columnas);



            listaButton = new ListaEnlazada<>();


            GridPane mainGrid2 = new GridPane();
            ScrollPane scrollPane2 = new ScrollPane(mainGrid2);



            for (int j = 0; j < filas; j++) {
                for (int i = 0; i < columnas; i++) {
                    int numI = matrizModel.matriz[j][i].getListaIndividuo().getNumeroElementos();
                    int numR = matrizModel.matriz[j][i].getListaRecurso().getNumeroElementos();
                    String label = "nºInd: " + numI + "\n nºRec: " + numR;
                    Button b = new Button(label);
                    b.setId(i + "," + j);

                    listaButton.add(b);

                    int finalJ = j;
                    int finalI = i;

                    EventHandler e = new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            p.onButtonInfo(finalI, finalJ,matrizModel);
                        }
                    };
                    b.setOnAction(e);


                    VBox placeholder = new VBox(b);

                    b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    b.setMinSize(80,80);
                    placeholder.setMinHeight(80);
                    placeholder.setMinWidth(80);

                    placeholder.setMaxSize(100, 100);
                    placeholder.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    mainGrid2.add(placeholder, i, j);


                }
            }
            Button pauseB = new Button("Pausar");

            HBox hbox = new HBox(pauseB);
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            Button button = new Button("Empezar");

            EventHandler empezarHandler = new EventHandler() {
                @Override
                public void handle(Event event) {

                    try {
                        matrizModel.original.bucleEntero();
                        moverIndividuo(matrizModel);
                        for (int j = 0; j < filas; j++) {
                            for (int i = 0; i < columnas; i++) {
                                actualizarButton(i,j,modeloMatriz,listaButton);
                            }
                        }
                        stage.setTitle("juego en turno: "+ matrizModel.original.turno);




                    }catch (Superar3Individuos e){
                        e.printStackTrace();
                    }catch (Superar3Recursos e){
                        e.printStackTrace();
                    }

                }
            };
            button.setOnAction(empezarHandler);
            hbox.getChildren().add(button);


            RecursosController h = new RecursosController();

            EventHandler pauseHandler = new EventHandler() {
                @Override
                public void handle(Event event) {

                    button.setText("Siguiente turno");

                    String style = listaButton.getElemento(0).getData().getStyle();
                    for(int m=0;m<listaButton.getNumeroElementos();m++){
                        listaButton.getElemento(m).getData().setStyle("-fx-background-color: #ac5e5e; -fx-text-fill: black;");
                        String id = listaButton.getElemento(m).getData().getId();
                        String[] split = id.split(",");
                        int f = Integer.parseInt(split[0]);
                        int c = Integer.parseInt(split[1]);
                        AjustesMidJuegoController a = new AjustesMidJuegoController();
                        EventHandler y = new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                a.loadUserData(recursosModel, aguaModel,bibliotecaModel,comidaModel,montanaModel,
                                        pozoModel,tesoroModel,individuoModel,modeloMatriz,listaButton);
                                a.modificarCelda(f,c,matrizModel);
                            }
                        };
                        listaButton.getElemento(m).getData().setOnAction(y);
                    }

                    hbox.getChildren().remove(pauseB);

                    Button fin = new Button("Finalizar");
                    Button ajustes = new Button("Ajustes Propiedades");
                    Button play = new Button("Play");
                    play.setStyle("-fx-background-color: #3397cd; -fx-text-fill: white;");
                    hbox.getChildren().addAll(fin, ajustes, play);



                    EventHandler playHandler = new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            for(int m=0;m<listaButton.getNumeroElementos();m++){
                                listaButton.getElemento(m).getData().setStyle(style);
                                String id = listaButton.getElemento(m).getData().getId();
                                String[] split = id.split(",");
                                int f = Integer.parseInt(split[0]);
                                int c = Integer.parseInt(split[1]);
                                AjustesMidJuegoController a = new AjustesMidJuegoController();
                                JuegoController j = new JuegoController();
                                EventHandler y = new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        a.loadUserData(recursosModel, aguaModel,bibliotecaModel,comidaModel,montanaModel,
                                                pozoModel,tesoroModel,individuoModel,modeloMatriz,listaButton);
                                        //a.modificarCelda(f,c);
                                        j.onButtonInfo(f,c,matrizModel);
                                    }
                                };
                                listaButton.getElemento(m).getData().setOnAction(y);
                            }
                            hbox.getChildren().remove(play);
                            hbox.getChildren().remove(ajustes);
                            hbox.getChildren().remove(fin);

                            hbox.getChildren().add(pauseB);

                            //continuar el juego
                        }
                    };
                    play.setOnAction(playHandler);

                    EventHandler finHandler = new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            GuardarDatos g = new GuardarDatos();
                            g.guardarDatos(modeloMatriz.original,individuoModel.getOriginal(),aguaModel.getOriginalAgua(),bibliotecaModel.getOriginal(),comidaModel.getOriginal(),montanaModel.getOriginal(),pozoModel.getOriginal(),tesoroModel.getOriginal());
                            stage.close();

                            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMenuInicial.class.getResource("hello-view.fxml"));

                            HelloController h = new HelloController();

                            stage.setTitle("Hello!");

                            Label lab = new Label("Bienvenidos al juego de la vida");
                            lab.setStyle("-fx-font-size: 24px; -fx-text-fill: green;");

                            Button nuevoBtn = new Button("Nueva Partida");
                            Button cargarBtn = new Button("Cargar Partida");

                            EventHandler n = new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    stage.close();
                                    h.onMiBotonNuevaVentanaAjustesClick();
                                }
                            };

                            nuevoBtn.setOnAction(n);

                            EventHandler c = new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    stage.close();
                                    h.onMiBotonNuevaVentajaClick();
                                }
                            };

                            cargarBtn.setOnAction(c);


                            VBox vbox = new VBox(lab, nuevoBtn, cargarBtn);
                            vbox.setAlignment(Pos.CENTER);
                            vbox.setSpacing(20);

                            Scene scene = new Scene(vbox, 400, 200);


                            stage.setScene(scene);
                            stage.show();
                        }
                    };
                    fin.setOnAction(finHandler);

                    AjustesMidJuegoController l = new AjustesMidJuegoController();
                    EventHandler ajustesHandler = new EventHandler() {
                        @Override
                        public void handle(Event event) {


                            for(int i=0;i<h.modeloMatriz.getFilas();i++){
                                for (int j = 0; j < h.modeloMatriz.getColumnas(); j++) {
                                    for (int k = 0; k < listaButton.getNumeroElementos(); k++) {
                                        String label = i+","+j;
                                        if(listaButton.getElemento(k).getData().getId().equals(label)){
                                            System.out.println("if");
                                            int finalI = i;
                                            int finalJ = j;
                                            EventHandler e = new EventHandler() {
                                                @Override
                                                public void handle(Event event) {
                                                    l.modificarCelda(finalI, finalJ,matrizModel);
                                                }
                                            };
                                            listaButton.getElemento(k).getData().setOnAction(e);
                                        }
                                    }
                                }
                            }

                        }
                    };

                    ajustes.setOnAction(ajustesHandler);






                }
            };

            pauseB.setOnAction(pauseHandler);

            mainGrid2.add(hbox, columnas, filas);

            p.setStage(stage);
            stage.show();


            Scene scene2 = new Scene(scrollPane2, 600, 600);
            scene1 = scene2;
            stage.setScene(scene1);
            stage.show();



        }catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void actualizarButton(int c, int f,BucleDeControlProperties modeloMatriz,ListaEnlazada<Button> listaButton) {
        this.listaButton = listaButton;
        int aux = 0;
        Button res = new Button();
        res.setId("hola");

        System.out.println("nºelementos " + listaButton.getNumeroElementos());

        while (aux < listaButton.getNumeroElementos()) {
            String[] splitted = listaButton.getElemento(aux).getData().getId().split(",");

            int m = Integer.parseInt(splitted[0]);
            int n = Integer.parseInt(splitted[1]);

            if (n==f && m==c) {
                res = listaButton.getElemento(aux).getData();
            }
            aux++;
        }
        System.out.println(res.getId());
        int numI = modeloMatriz.original.matriz[f][c].getListaIndividuo().getNumeroElementos();
        int numR = modeloMatriz.original.matriz[f][c].getListaRecurso().getNumeroElementos();
        String nombre = "nºInd: " + numI + "\n nºRec: " + numR;
        res.setText(nombre);

    }

    public long getSegundos() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public void juega(BucleDeControl bucle) throws Superar3Recursos, Superar3Individuos {

        bucle.bucleEntero();



    }

    public void moverIndividuo(BucleDeControlProperties bucle) throws Superar3Individuos, Superar3Recursos {
        ListaEnlazada<Individuo> listaIndividuo = new ListaEnlazada<>();
        for (int j = 0; j < bucle.getFilas(); j++) {
            for (int i = 0; i < bucle.getColumnas(); i++) {
                for (int k = 0; k < matrizModel.matriz[j][i].getListaIndividuo().getNumeroElementos();k++){
                    listaIndividuo.add(matrizModel.matriz[j][i].getListaIndividuo().getElemento(k).getData());
                }
            }
        }
        BucleDeControl bucleDeControl = new BucleDeControl(bucle.getFilas(), bucle.getColumnas());
        Celda[][] matriz = bucleDeControl.matriz;
        for (int j = 0; j < bucle.getFilas(); j++) {
            for (int i = 0; i < bucle.getColumnas(); i++) {
                for (int k = 0; k < listaIndividuo.getNumeroElementos(); k++) {
                    Individuo ind = listaIndividuo.getElemento(k).getData();
                    if(j == ind.getPosN() && i == ind.getPosM()){
                        matriz[j][i].addIndividuo(ind);
                    }
                }
                for (int h = 0; h < matrizModel.matriz[j][i].getListaRecurso().getNumeroElementos();h++){
                    Recursos rec = matrizModel.matriz[j][i].getListaRecurso().getElemento(h).getData();
                    matriz[j][i].addRecurso(rec);
                }
            }
        }
        bucle.original.matriz = matriz;



    }

}

