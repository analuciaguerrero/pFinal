module com.example.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    requires java.desktop;
    requires java.logging;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires javafx.media;


    opens com.example.demoJavafx to javafx.fxml, com.google.gson;
    opens com.example.demoJavafx.estructurasDeDatos.ListaEnlazada to com.google.gson;
    opens com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras to com.google.gson;
    opens com.example.demoJavafx.estructurasDeDatos.ListaSimple to com.google.gson;
    opens com.example.demoJavafx.estudiante to com.google.gson;
    opens com.example.demoJavafx.entorno to com.google.gson;
    exports com.example.demoJavafx;
}