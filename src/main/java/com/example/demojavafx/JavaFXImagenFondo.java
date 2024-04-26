package com.example.demojavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXImagenFondo extends Application {
    private final Image bgImage = new Image("file:src/main/java/com/example/demojavafx/imagenes/fondo.jpg");
    private final StackPane container = new StackPane();
    private final Scene scene = new Scene(container);
    private final Text label  = new Text("Hello word");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Asigna un titulo y dimensiones al "stage"
        primaryStage.setTitle("Práctica Final Ivan y Ana");
        primaryStage.setMinHeight(1050);
        primaryStage.setMinWidth(1830);
        primaryStage.setResizable(false);

        // Añado imagen de fondo a la ventana
        ImageView imageView = new ImageView();
        imageView.setImage(bgImage);
        imageView.setFitHeight(1050);
        imageView.setFitWidth(1830);
        container.getChildren().add(imageView);

        // Añado algo de texto a la ventana (la imagen estará primero)
        label.setFill(Color.RED);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 38));
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(label);

        // Mostrar ventana
        primaryStage.setScene(scene);
        primaryStage.show();
    }

} // class
