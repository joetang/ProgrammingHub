package com.model.mvc.web.core.view.support;

import org.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.Map;

public class JsonUtil {
    protected static final Log logger = LogFactory.getLog(JsonUtil.class);
    public static JSONObject fromMap(Map model) {
        JSONObject jsonObject = new JSONObject();
        try {
            Iterator ite = model.keySet().iterator();
            while (ite.hasNext()) {
                String key = (String) ite.next();
                jsonObject.put(key, model.get(key));
            }
        } catch (Exception e) {
            logger.error("call fromMap failed ");
        }
        return jsonObject;
    }
}
