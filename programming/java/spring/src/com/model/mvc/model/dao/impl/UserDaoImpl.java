package com.model.mvc.model.dao.impl;

import com.model.mvc.domain.User;
import com.model.mvc.model.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public boolean createUser(User user) {
        return true;
    }
}
