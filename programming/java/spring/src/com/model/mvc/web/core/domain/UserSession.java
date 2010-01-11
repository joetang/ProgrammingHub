package com.model.mvc.web.core.domain;

import com.model.mvc.domain.User;

import java.io.Serializable;

public class UserSession implements Serializable {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
