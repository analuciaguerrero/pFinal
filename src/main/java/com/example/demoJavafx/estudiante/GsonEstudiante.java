package com.example.demoJavafx.estudiante;
import com.google.gson.*;
import java.lang.reflect.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demoJavafx.excepciones.ClaseErroneaException;

public class GsonEstudiante implements JsonSerializer<Estudiante>, JsonDeserializer<Estudiante> {
    private static final Logger log = LogManager.getLogger();

    @Override
    public JsonElement serialize(Estudiante src, Type typeOfSrc, JsonSerializationContext context) {
        log.debug("Empezando serialización de estudiante {} a Json", src.getTipo().getSimpleName());
        JsonObject jsonEstudiante = new JsonObject();
        jsonEstudiante.addProperty("tipo", src.getTipo().getSimpleName());
        jsonEstudiante.add("propiedades", context.serialize(src));
        return jsonEstudiante;
    }

    @Override
    public Estudiante deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        log.debug("Empezando deserialización de Estudiante {} a Json", json.getAsJsonObject().get("tipo").getAsString());
        JsonObject jsonEstudiante = json.getAsJsonObject();
        String tipo = jsonEstudiante.get("tipo").getAsString();

        return switch (tipo) {
            case "EstudianteBasico" ->
                    context.deserialize(jsonEstudiante.get("propiedades").getAsJsonObject(), EstudianteBasico.class);
            case "EstudianteNormal" ->
                    context.deserialize(jsonEstudiante.get("propiedades").getAsJsonObject(), EstudianteNormal.class);
            case "EstudianteAvanzado" ->
                    context.deserialize(jsonEstudiante.get("propiedades").getAsJsonObject(), EstudianteAvanzado.class);
            default -> throw new ClaseErroneaException(Estudiante.class.getSimpleName(), tipo);
        };
    }
}
