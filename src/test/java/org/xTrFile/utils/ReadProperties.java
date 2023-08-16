package org.xTrFile.utils;
import org.xTrFile.driver.FindOS;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    static Properties properties = null;

    public static Properties readProp(String configFileName){

        properties = new Properties();
        FileInputStream fis;

        try{
            String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/properties/" + configFileName;

            if (FindOS.getOperationSystemName().equals("WINDOWS")){
                propertiesFilePath = propertiesFilePath.replace("/","\\");
            }

            fis = new FileInputStream(propertiesFilePath);
            properties.load(fis);

        }catch (IOException e){
            e.printStackTrace();
        }

        return properties;
    }
}
