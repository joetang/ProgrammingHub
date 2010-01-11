package com.model.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.InitializingBean;

public class DefaultConfiguration implements Configuration, InitializingBean {
    private static final Log logger = LogFactory.getLog(DefaultConfiguration.class);
    private static Properties prop = null;
    private static Resource locations[];

    public void afterPropertiesSet() throws Exception {
        reload();
    }

    public void reload() {
        try {
            prop = new Properties();
            if (locations != null) {
                for (Resource location : locations) {
                    logger.debug("DefaultConfiguration - " + location);
                    prop.load(location.getInputStream());
                }
            }
        } catch (IOException e) {
            logger.debug("", e);
        }
    }

    public void setLocation(Resource location) {

        locations = (new Resource[]{
                location
        });

    }

    public void setLocations(Resource[] locationArray) {
        locations = locationArray == null ? new Resource[0] : locationArray;
    }

    private void checkInit() {
        if (prop == null) {
            reload();
        }
    }

    public String getString(String name) {
        String result = null;
        if (prop != null && name != null && name.length() > 0) {
            result = prop.getProperty(name);
        }
        return result;
    }

    public String getString(String name, String defaultValue) {
        String result = null;
        if (prop != null && name != null && name.length() > 0) {
            result = prop.getProperty(name, defaultValue);
        }
        return result;
    }

    public Integer getInteger(String name) {
        Integer result = null;
        if (prop != null && name != null && name.length() > 0) {
            try {
                result = Integer.parseInt(prop.getProperty(name));
            }
            catch (NumberFormatException e) {result = null;}
        }
        return result;
    }

    public int getInt(String name, int defaultValue) {
        if (prop != null && name != null && name.length() > 0) {
            try {
                return Integer.parseInt(prop.getProperty(name));
            }
            catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public Boolean getBoolean(String name) {
        Boolean result = null;
        if (prop != null && name != null && name.length() > 0) {
                result = Boolean.parseBoolean(prop.getProperty(name));
        }
        return result;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        if (prop != null && name != null && name.length() > 0) {
            return Boolean.parseBoolean(prop.getProperty(name, Boolean.toString(defaultValue)));
        }
        return defaultValue;
    }

    public List<String> getList(String name) {
        List<String> result = new ArrayList<String>();
        String resultStr = "";
        if (prop != null && name != null && name.length() > 0) {
            resultStr = prop.getProperty(name);
            logger.debug("getList ----------- >name" + name + ",  resultStr - " + resultStr);
            if (resultStr != null && resultStr.length() > 0) {
                String[] array = resultStr.split(",");
                for (String cur : array) {
                    result.add(cur);
                }
            }
        }
        return result;
    }
}
