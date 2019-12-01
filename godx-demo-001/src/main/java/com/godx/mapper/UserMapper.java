package com.godx.mapper;

import com.godx.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User findByName(String username);
    public User findById(Integer id);
}
