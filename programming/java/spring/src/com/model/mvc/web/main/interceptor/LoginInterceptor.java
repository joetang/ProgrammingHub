package com.model.mvc.web.main.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mvc.web.Constants;
import com.model.mvc.web.core.utils.UserSessionUtils;
import com.model.mvc.web.core.domain.UserSession;

public class LoginInterceptor extends DefaultInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

        logger.debug("LoginInterceptor : ....");

        UserSession userSession = UserSessionUtils.getUserSession(request);
        if (userSession != null)
            return true;
        
        response.sendRedirect("index.do");
		return false;

    }
}
