package com.zy.miaosha.service;

import com.zy.miaosha.domain.User;

public abstract interface UserService {
    
    User getById(int id);
    
    boolean tx();
}
