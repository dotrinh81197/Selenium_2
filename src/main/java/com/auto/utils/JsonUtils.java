package com.auto.utils;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> List<T> toList(String jsonPath, Type type) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    public static <T> T to(String jsonPath, Type type) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    public static <T> T to(String jsonPath, Class<T> clazz) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    private static JsonReader getJsonReader(String jsonPath) {
        try {
            JsonReader reader;
            reader = new JsonReader(new FileReader(jsonPath));
            return reader;
        } catch (FileNotFoundException e) {
            log.error(",e");
        }
        throw new RuntimeException("Cannot read json file from " + jsonPath);
    }

}
