package com.nju.software.service;

import com.nju.software.Bean.User;
import com.nju.software.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findUserById(String id){
        return userDao.findUserById(id);
    }

    public void save(User user){
        userDao.save(user);
    }

    public User findUserByPhone(String phone){
        return userDao.findUserByPhone(phone);
    }

}
