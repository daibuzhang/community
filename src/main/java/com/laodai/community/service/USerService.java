package com.laodai.community.service;

import com.laodai.community.dao.UserMapper;
import com.laodai.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class USerService {

    @Autowired
    private UserMapper userMapper;


    public User findUserByID(int id){
        return userMapper.selectById(id);
    }
}
