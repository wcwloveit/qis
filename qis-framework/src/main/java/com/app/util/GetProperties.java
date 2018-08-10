package com.app.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alex_cmq on 2017/7/25.
 */
public class GetProperties {

    /**
     * 返回配置文件中的value
     * @param name key
     * @return
     * @throws IOException
     */
    public static String getProperties(String name) throws IOException{
        Properties prop = new Properties();
        prop.load(GetProperties.class.getResourceAsStream("/common.properties"));
        String DRIVERNAME = prop.getProperty(name);
        return DRIVERNAME;
    }


}
