package com.zy.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.miaosha.dao.UserMapper;
import com.zy.miaosha.domain.User;

@Service("UserService")
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserMapper userMapper;

    @Override
    public User getById(int id) {
        return userMapper.getById(id);
    }

    @Override
    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("aaaa");
        userMapper.insert(u1);
        
        User u2 = new User();
        u2.setId(1);
        u2.setName("aaaa");
        userMapper.insert(u2);
        return true;
    }
    
    
    
    
}
