package com.zy.miaosha.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.miaosha.domain.MiaoshaUser;
import com.zy.miaosha.redis.RedisService;
import com.zy.miaosha.result.Result;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisService redisService;
    
    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }

}
