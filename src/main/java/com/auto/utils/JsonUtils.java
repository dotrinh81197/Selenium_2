package com.auto.utils;

import com.auto.data.enums.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Hashtable;
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

    public static Object[][] jsonArrayToObjectArray(JsonArray jsonArray) {

        Object[][] data = new Object[0][1];
        int index = 0;
        Gson gson = new Gson();

        if (jsonArray.size() > 0) {
            data = new Object[jsonArray.size()][1];
            for (Object obj : jsonArray) {
                Hashtable<String, String> hashTable = new Hashtable<String, String>();
                data[index][0] = gson.fromJson((JsonElement) obj, hashTable.getClass());
                index++;
            }
        }
        return data;
    }

    public static Object[][] jsonToObject(String testData, String dataFilePath) {

        Object[][] data = new Object[0][1];

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dataFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

        boolean blnTCExist = jsonObject.has(testData);
        if (!blnTCExist) {
            return data;
        }

        JsonArray jsonArray = jsonObject.getAsJsonArray(testData);
        data = jsonArrayToObjectArray(jsonArray);
        return data;
    }

    public static Hashtable<String, String> getData(String dataType, Data path) {
        Hashtable<String, String> data;
        ObjectMapper oMapper = new ObjectMapper();
        Object[][] obj = JsonUtils.jsonToObject(dataType, path.value());
        return data = oMapper.convertValue(obj[0][0], Hashtable.class);
    }

    @DataProvider
    public Object[][] getDataItemPanelAvailable() {
        Object[][] data ;
        String DataFilePath = "src/test/resources/data/availableItemPanel.json";
        JsonObject object = JsonUtils.to(DataFilePath, JsonObject.class);

        JsonObject itemList = object.getAsJsonObject();
        JsonArray jsonArray = itemList.getAsJsonArray("listItemPanelAvailable");
        data = jsonArrayToObjectArray(jsonArray);

        return data;
    }
}
