package com.example.demoJavafx.estructurasDeDatos.Gson;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MyGsonTest {
    @Test
    public void testGuardarYRecuperarObjeto() {
        // Definir el objeto a guardar
        Persona personaOriginal = new Persona("John", 30);

        // Definir la ruta del archivo de prueba
        String rutaArchivo = "test.json";

        // Guardar el objeto en el archivo
        MyGson.guardarObjetoEnArchivo(rutaArchivo, personaOriginal);

        // Recuperar el objeto desde el archivo
        Persona personaRecuperada = MyGson.cargarObjetoDesdeArchivo(rutaArchivo, Persona.class);

        // Verificar que el objeto recuperado sea igual al original
        assertEquals(personaOriginal, personaRecuperada);

        // Borrar el archivo de prueba
        File file = new File(rutaArchivo);
        file.delete();
    }
}

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Persona persona = (Persona) obj;
        return edad == persona.edad && nombre.equals(persona.nombre);
    }
}