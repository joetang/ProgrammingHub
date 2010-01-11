package com.model.mvc.web.main;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.model.mvc.web.core.controller.DefaultController;

public class ProfileController extends DefaultController {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();

        List<String> profiles = new ArrayList<String>();

        profiles.add("testing profile");

        model.put("profiles", profiles);

        return new ModelAndView(getSuccessView(), model);
    }
}
