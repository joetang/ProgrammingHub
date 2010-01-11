package com.model.mvc.web.registration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.model.mvc.domain.User;
import com.model.mvc.model.service.UserService;
import com.model.mvc.web.registration.form.RegistrationForm;
import com.model.mvc.web.core.controller.DefaultSimpleFormController;
import com.model.mvc.web.core.utils.UserSessionUtils;

public class RegistationController extends DefaultSimpleFormController {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RegistationController() {
        setCommandName("registrationForm");
        setCommandClass(RegistrationForm.class);
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors)
            throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();

        return this.showForm(request, errors, getFormView(), model);
    }


    protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        RegistrationForm registrationForm = (RegistrationForm) command;

        Map<String, Object> model = new HashMap<String, Object>();

        User newUser = new User();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        
        newUser.setUsername(registrationForm.getUsername());
        newUser.setPassword(registrationForm.getPassword());

    
        if(userService.createUser(newUser) != null){
            UserSessionUtils.setUserSession(request, UserSessionUtils.createUserSessionByUser(newUser));
            model.put("users", newUser);
            return new ModelAndView(getSuccessView(), model);
        }

        errors.reject("error.fail","Registratin Fail");
        return this.showForm(request, errors, getFormView(), model);       
    }
}
