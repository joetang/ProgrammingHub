package com.model.mvc.web.main;

import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import com.model.mvc.web.core.view.support.JSONView;
import com.model.mvc.web.core.controller.DefaultController;

public class JsonTestingController extends DefaultController {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("p1", "hello1");
        model.put("p2", "hello2");
        model.put("p3", "hello3");
        model.put("p4", "hello4");


        return new ModelAndView(new JSONView(), model);
    }

}
