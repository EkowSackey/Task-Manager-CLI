package main.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.models.Project;
import main.repository.ProjectList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class FileUtils {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static void saveProjects(Path path, List<Project> projects) throws IOException {

        Path parent = path.getParent();

        if (parent != null){
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        )){
            gson.toJson(projects, writer);
        }
    }

    public static List<Project> loadProjects(Path path) throws IOException{

        try(var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            var listType = new TypeToken<List<Project>>(){}.getType();
            return gson.fromJson(reader, listType);
        }
    }
}
