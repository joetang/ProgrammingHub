package com.model.mvc.web.core.view.support;

import org.json.JSONObject;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.io.PrintWriter;

public class JSONView implements View {
    private static final String DEFAULT_JSON_CONTENT_TYPE = "application/json";
    private static final String DEFAULT_JAVASCRIPT_TYPE = "text/javascript";
    private JSONObject jsonObject;

    public JSONView(JSONObject jsonObject) {
        super();
        this.jsonObject = jsonObject;
    }

    public JSONView() {
        super();
        this.jsonObject = null;
    }

    public String getContentType() {
        return DEFAULT_JSON_CONTENT_TYPE;
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (this.jsonObject == null) {
            this.jsonObject = JsonUtil.fromMap(model);
        }

        boolean scriptTag = false;
        String cb = request.getParameter("callback");
        if (cb != null) {
            scriptTag = true;
            response.setContentType(DEFAULT_JAVASCRIPT_TYPE);
        } else {
            response.setContentType(DEFAULT_JSON_CONTENT_TYPE);
        }

        PrintWriter out = response.getWriter();
        if (scriptTag) {
            out.write(cb + "(");
        }
        out.write(this.jsonObject.toString());
        if (scriptTag) {
            out.write(");");
        }
    }
}
