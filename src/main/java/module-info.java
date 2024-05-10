module com.example.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    requires java.desktop;
    requires java.logging;
    requires com.google.gson;
    requires org.apache.logging.log4j;


    opens com.example.demoJavafx to javafx.fxml;
    exports com.example.demoJavafx;
}