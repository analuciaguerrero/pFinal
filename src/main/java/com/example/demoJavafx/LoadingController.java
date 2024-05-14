package com.example.demoJavafx;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingController {

    public ProgressBar progressBar;

    public void initialize() {
        // Crear una línea de tiempo para actualizar el progreso de la barra de progreso durante 15 segundos
        Timeline progressBarTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            // Actualizar el progreso de la barra de progreso
            progressBar.setProgress(progressBar.getProgress() + 0.01);
        }));
        progressBarTimeline.setCycleCount(30); // 0.5 segundos * 30 = 15 segundos
        progressBarTimeline.setOnFinished(event -> openNextScene()); // Cuando termine, abrir la siguiente escena
        progressBarTimeline.play(); // Iniciar la línea de tiempo para la ProgressBar
    }

    private void openNextScene() {
        // Lógica para cambiar a otra escena después de 15 segundos
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("otraVentana.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) progressBar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
