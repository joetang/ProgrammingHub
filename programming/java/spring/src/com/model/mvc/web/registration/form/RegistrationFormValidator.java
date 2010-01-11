package com.model.mvc.web.registration.form;

import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import com.model.mvc.web.registration.form.RegistrationForm;

public class RegistrationFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return RegistrationForm.class.isAssignableFrom(clazz);
    }


    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "USER_NAME_REQUIRED", "User Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "PASSWORD_REQUIRED", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "AGE_REQUIRED", "age is required.");


    }

}
