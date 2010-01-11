package com.model.mvc.web.core.utils;

import javax.servlet.http.HttpServletRequest;

import com.model.mvc.domain.User;
import com.model.mvc.web.core.constant.Constants;
import com.model.mvc.web.core.domain.UserSession;

public class UserSessionUtils {

    public static UserSession createUserSessionByUser(User user) {
        UserSession userSession = new UserSession();
        userSession.setUser(user);
        return userSession;
    }

    public static UserSession updateUserSessionByUser(HttpServletRequest request, User user) {
        getUserSession(request).setUser(user);
        return getUserSession(request);
    }

    public static void removeUserSession(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.USER_SESSION);
    }

    public static UserSession getUserSession(HttpServletRequest request) {
        return (UserSession) request.getSession().getAttribute(Constants.USER_SESSION);
    }

    public static void setUserSession(HttpServletRequest request, UserSession userSession) {
        request.getSession().setAttribute(Constants.USER_SESSION, userSession);
    }

    public static User getUserFromUserSession(HttpServletRequest request) {
        return getUserSession(request).getUser();
    }
}
