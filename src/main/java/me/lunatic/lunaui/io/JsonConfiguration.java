package me.lunatic.lunaui.io;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Map;

public class JsonConfiguration extends MemoryConfiguration {

    private final File file;

    public JsonConfiguration(File file, boolean load) {
        this.file = file;
        try {
            Files.createParentDirs(file);
            Files.touch(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (load) this.load();
    }

    public void load() {
        this.values.clear();
        try {
            try (JsonReader reader = new JsonReader(new FileReader(this.file))) {
                Gson gson = new Gson();
                Map<String, Object> map = gson.fromJson(reader, Map.class);
                if (map != null) this.values.putAll(map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            try (Writer writer = new FileWriter(this.file)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(this.values, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}