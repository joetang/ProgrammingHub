package com.model.mvc;

public interface Configuration {

    public String getString(String name);

    public String getString(String name, String defaultValue);

    public Integer getInteger(String name);

    public int getInt(String name, int defaultValue);

    public Boolean getBoolean(String name);

    public boolean getBoolean(String name, boolean defaultValue);
}
