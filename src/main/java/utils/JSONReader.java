package utils;

import com.google.gson.Gson;
import com.pl.edu.agh.tramsim.view.MainApplication;
import elements.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JSONReader<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONReader.class);

    private final Class<T> classType;

    public JSONReader(Class<T> classType) {
        this.classType = classType;
    }

    /**
     *
     * @param filePath  path to file in resource (e.g. 'routes/4.json')
     * @return  list of objects
     */
    public List<T> readJSONFromFile(String filePath) {
        ClassLoader classLoader = JSONReader.class.getClassLoader();
        Gson gson = new Gson();
        List<T> objects = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(classLoader.getResource(filePath).getPath()))) {
            stream.forEach(line -> objects.add(gson.fromJson(line, classType)));
        } catch (IOException ex) {
            LOGGER.warn("initStopsFromJSON(): " +  ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        return  objects;
    }

}
