package org.xTrFile.driver;
import org.apache.commons.lang3.SystemUtils;

import java.util.logging.Logger;
public class FindOS {

    private static Logger logger = Logger.getLogger(String.valueOf(FindOS.class));

    public static String getOperationSystemName(){

        logger.info(System.getProperty("os.name"));

        if(SystemUtils.IS_OS_WINDOWS){
            return "WINDOWS";
        }else if(SystemUtils.IS_OS_MAC){
            return "MAC";
        }else if (SystemUtils.IS_OS_LINUX){
            return "LINUX";
        }else
            return "null";



    }
}
