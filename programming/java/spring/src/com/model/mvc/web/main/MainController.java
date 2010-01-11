package com.model.mvc.web.main;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mvc.model.service.UserService;
import com.model.mvc.web.core.controller.DefaultController;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MainController extends DefaultController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.debug("MainController : ....");

        Map<String, Object> model = new HashMap<String, Object>();

//        String user = UserService.createUser();
        String user = "test user";
        int count = getConfiguration().getInteger("web.index.show.count").intValue();

        List<String> users = new ArrayList<String>();

        for (int i=0; i<10; i++) {
            users.add(user + " " + i);
        }
        model.put("users",users);

        return new ModelAndView(getSuccessView(), model);
    }
}
