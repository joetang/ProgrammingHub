package com.model.mvc.web.main;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mvc.web.core.controller.DefaultController;
import com.model.mvc.web.core.utils.UserSessionUtils;
import com.model.mvc.domain.User;

public class LoginController extends DefaultController {

        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = new User();
        user.setUsername("testing");
        UserSessionUtils.setUserSession(request, UserSessionUtils.createUserSessionByUser(user));

        return new ModelAndView(getSuccessView());
    }
}
