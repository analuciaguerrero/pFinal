package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.estudiante.EstudianteNormal;
import com.example.demoJavafx.excepciones.ClaseErroneaException;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class GsonRecursos implements JsonSerializer<Recursos>, JsonDeserializer<Recursos> {
    private static final Logger log = LogManager.getLogger(GsonRecursos.class);

    @Override
    public JsonElement serialize(Recursos src, Type typeOfSrc, JsonSerializationContext context) {
        log.debug("Empezando serialización de recurso {} a Json", src.getTipo().getSimpleName());
        JsonObject jsonRecurso = new JsonObject();
        jsonRecurso.addProperty("tipo", src.getTipo().getSimpleName());
        jsonRecurso.add("propiedades", context.serialize(src));
        return jsonRecurso;
    }

    @Override
    public Recursos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        log.debug("Empezando deserialización de Recursos {} a Json", json.getAsJsonObject().get("tipo").getAsString());
        JsonObject jsonRecurso = json.getAsJsonObject();
        String tipo = jsonRecurso.get("tipo").getAsString();
        return switch (tipo) {
            case "Agua" -> context.deserialize(jsonRecurso.get("propiedades").getAsJsonObject(), Agua.class);
            case "Comida" -> context.deserialize(jsonRecurso.get("propiedades").getAsJsonObject(), Comida.class);
            case "Montaña" -> context.deserialize(jsonRecurso.get("propiedades").getAsJsonObject(), Montaña.class);
            case "Biblioteca" -> context.deserialize(jsonRecurso.get("propiedades").getAsJsonObject(), Biblioteca.class);
            case "Tesoro" -> context.deserialize(jsonRecurso.get("propiedades").getAsJsonObject(), Tesoro.class);
            case "Pozo" -> context.deserialize(jsonRecurso.get("propiedades").getAsJsonObject(), Pozo.class);
            default -> throw new ClaseErroneaException(Recursos.class.getSimpleName(), tipo);
        };
    }
}

