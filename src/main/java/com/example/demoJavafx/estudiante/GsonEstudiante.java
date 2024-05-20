package com.example.demoJavafx.estudiante;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ElementoLS;
import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.google.gson.*;
import java.lang.reflect.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demoJavafx.excepciones.ClaseErroneaException;

public class GsonEstudiante implements JsonSerializer<Estudiante>, JsonDeserializer<Estudiante> {
    private static final Logger log = LogManager.getLogger();

    @Override
    public JsonElement serialize(Estudiante src, Type typeOfSrc, JsonSerializationContext context) {
        log.trace(STR."Empezando serialización del estudiante \{src.getTipo().getSimpleName()} a Json");
        JsonObject jsonEstudiante = new JsonObject();
        ListaSimple<Estudiante> padres = src.getPadres();
        if (padres.getPrimero() != null) {
            JsonArray padresJson = new JsonArray(2);
            padresJson.add(serialize(padres.getPrimero().getData(), typeOfSrc, context));
            padresJson.add(serialize(padres.getElemento(1).getData(), typeOfSrc, context));
            jsonEstudiante.add("padres", padresJson);
        }
        jsonEstudiante.addProperty("tipo", src.getTipo().getSimpleName());
        jsonEstudiante.add("propiedades", context.serialize(src));
        return jsonEstudiante;
    }

    @Override
    public Estudiante deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        log.trace(STR."Empezando deserialización del estudiante \{json.getAsJsonObject().get("tipo").getAsString()} a Json");
        JsonObject jsonEstudiante = json.getAsJsonObject();
        String tipo = jsonEstudiante.get("tipo").getAsString();

        Class<?> claseEstudiante = switch (tipo) {
            case "EstudianteBasico" ->
                    EstudianteBasico.class;
            case "EstudianteNormal" ->
                    EstudianteNormal.class;
            case "EstudianteAvanzado" ->
                    EstudianteAvanzado.class;
            default -> throw new ClaseErroneaException(Estudiante.class.getSimpleName(), tipo);
        };
        Estudiante estudiante = context.deserialize(jsonEstudiante.get("propiedades").getAsJsonObject(), claseEstudiante);
        ListaSimple<Estudiante> lista = new ListaSimple<>(2);
        if (jsonEstudiante.has("padres")) {
            JsonArray padres = jsonEstudiante.get("padres").getAsJsonArray();
            for (int i = 0; i != 2; i++) {
                Estudiante padre = deserialize(padres.get(i).getAsJsonObject(), typeOfT, context);
                ElementoLS<Estudiante> el = new ElementoLS<>(padre);
                lista.add(el);
            }
            estudiante.setPadres(lista);
        }
        return estudiante;
    }
}
