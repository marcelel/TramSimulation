package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.pl.edu.agh.tramsim.view.MainApplication;
import elements.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RouteJSONGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteJSONGenerator.class);

    public static void generateJSON(String lineNumber, String stops) {
        List<String> lines = Arrays.asList("{\n", "\t\"route\": [\n", "\n");
        Path file = Paths.get("src/main/resources/routes/" + lineNumber + ".json");
        String[] stopsNames = stops.split("\n");
        Stop stop;
        for (int i = 0; i < stopsNames.length; i++) {
            stop = new Stop(stopsNames[i], BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 1)));
            lines.add("\t" + new Gson().toJson(stop) + ",\n");
        }
        lines.add("\t]\n");
        lines.add("}\n");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            LOGGER.warn("generateJSON(): " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

}
