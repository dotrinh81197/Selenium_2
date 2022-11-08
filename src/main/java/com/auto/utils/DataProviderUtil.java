package com.auto.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;

public class DataProviderUtil {

    @DataProvider
    public static Object[][] getTypeUserData () {
        JsonObject object = JsonUtils.to("src/test/resources/data/invalidUser.json", JsonObject.class);
        JsonArray userList = object.getAsJsonArray("invalidUserNamePassword");

        Object[][] dataProvider = new Object[userList.size()][1];
        for(int i=0;i<userList.size();i++){
            dataProvider[i][0] = userList.get(i).getAsJsonObject().get("type").getAsString();
        }
        return dataProvider;
    }


}
