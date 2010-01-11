package com.model.mvc.model.service.impl;

import com.model.mvc.domain.User;
import com.model.mvc.model.dao.UserDao;
import com.model.mvc.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        if (userDao.createUser(user)) {
            return user;
        }
        return null;
    }
}
