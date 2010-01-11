package com.model.mvc.web.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FormDataPersistence {
    private static final Log logger = LogFactory.getLog(FormDataPersistence.class);

    private Map<String, String> countryList = null;

	public Map<String, String> getCountryList() {
        if (countryList == null || countryList.size() == 0)
            reloadCountryList();

        return countryList;
    }

    private void reloadCountryList() {
        logger.debug("FormDataPersistence: reload Country ...");
        countryList = new LinkedHashMap<String, String>();

        	countryList.put("HK", "HONG KONG");
        	countryList.put("JP", "JAPAN");
    }



}
