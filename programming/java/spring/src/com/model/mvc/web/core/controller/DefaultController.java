package com.model.mvc.web.core.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.model.mvc.Configuration;

public abstract class DefaultController extends WebApplicationObjectSupport implements Controller{
    protected transient final Log logger = LogFactory.getLog(getClass());
    private Configuration configuration;
    private String successView;

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
       return new ModelAndView(successView);
    }
}
