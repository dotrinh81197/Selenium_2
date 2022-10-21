package com.auto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static void savePropertiesToFile(Properties properties, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            log.error("Cannot save to file", e);
        }
    }
}
