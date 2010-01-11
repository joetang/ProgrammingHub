package com.model.mvc.web.main.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultInterceptor extends HandlerInterceptorAdapter {
    protected transient final Log logger = LogFactory.getLog(getClass());

}
