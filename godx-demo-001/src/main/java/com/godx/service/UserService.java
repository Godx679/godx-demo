package com.godx.service;

import com.godx.entity.User;

public interface UserService {
    public User findByName(String username);
    public User findById(Integer id);
}
