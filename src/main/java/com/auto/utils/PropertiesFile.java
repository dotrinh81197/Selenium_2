package com.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {
    static Properties prop = new Properties();
    //defining the project path
    static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        getProperties();
        setProperties();
        getProperties();
    }

    public static void getProperties() {
        try {
            InputStream input = new FileInputStream(projectPath + "/src/test/resources/configuration.properties");
            //Load properties file
            prop.load(input);
            //get values from properties file
            String browser = prop.getProperty("browser");
            System.out.println(browser);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

    public String getPropertyValue(String key){
        return prop.getProperty(key);
    }

    public static void setProperties() {
        try {
            OutputStream output = new FileOutputStream(projectPath + "/src/test/resources/configuration.properties");
            //Load properties file and set firefox
            prop.setProperty("browser", "firefox");
            //store values i properties file
            prop.store(output, "setting firefox");
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }
}
