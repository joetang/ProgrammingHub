package com.model.mvc.web.core.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.model.mvc.Configuration;

public abstract class DefaultSimpleFormController extends SimpleFormController {
    protected transient final Log logger = LogFactory.getLog(getClass());
    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
