package com.zy.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zy.miaosha.domain.MiaoshaUser;
import com.zy.miaosha.domain.MiaoshaUserService;
import com.zy.miaosha.redis.RedisService;

@Controller
@RequestMapping(value= "/goods")
public class GoodsController {
    
    @Autowired
    MiaoshaUserService userService;
    
    @Autowired
    RedisService redisService;
    
    @RequestMapping("/to_list") 
    public String list(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        return "goods_list";
    }
}
