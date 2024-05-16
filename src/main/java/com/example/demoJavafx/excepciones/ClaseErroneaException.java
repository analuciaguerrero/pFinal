package com.example.demoJavafx.excepciones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClaseErroneaException extends RuntimeException {
    private static final Logger log = LogManager.getLogger();
    public ClaseErroneaException(String subclase, String superclase) {
        log.error("El tipo de superclase " + superclase + " no es válido (" + subclase + ")");
    }
}
