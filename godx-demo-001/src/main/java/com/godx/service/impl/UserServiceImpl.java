package com.godx.service.impl;

import com.godx.entity.User;
import com.godx.mapper.UserMapper;
import com.godx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByName(String username) {
       User user = userMapper.findByName(username);
       return user;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
