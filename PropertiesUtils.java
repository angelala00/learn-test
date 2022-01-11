package com.anfu.cloud.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    private static String sysFile = "config.properties";
    private static Properties Sysproperties ;
    static {
        Sysproperties = PropertiesUtils.getProperties(sysFile);
    }

    public static Properties getProperties(String configPath) {
        Properties properties = new Properties();
        try {
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            ClassLoader.getSystemClassLoader().getResource(sysFile);
            properties.load(new InputStreamReader(cl.getResourceAsStream(sysFile), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getSysConfigSet(String key){
        return Sysproperties.getProperty(key);
    }

}
