package com.feuji.utaf.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    /**
     * This method returns the value of the given key present in property file
     */

    private static final Logger logger = LogManager.getLogger(ReadPropertyFile.class);
    public static Properties readProperties(String fileName) {

        Properties properties = new Properties();
        try (InputStream input = ReadPropertyFile.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                logger.error("unable to find properties file");
                throw new RuntimeException("Unable to find properties file");
            }
            properties.load(input);
            logger.info("properties file loaded successfully");

        } catch (IOException e) {
            logger.error("Failed to load properties file ",e);
            throw new RuntimeException("Failed to load properties file" + e);
        }

        return properties;

    }
}
