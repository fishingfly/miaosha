package com.zy.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.miaosha.domain.User;
import com.zy.miaosha.rabbitmq.MQSender;
import com.zy.miaosha.redis.RedisService;
import com.zy.miaosha.redis.UserKey;
import com.zy.miaosha.result.Result;
import com.zy.miaosha.service.UserService;

@Controller
@RequestMapping(value= "/demo")
public class DemoController {
    
    @Autowired 
    UserService userService;
    
    @Autowired
    RedisService redisService;
    
    @Autowired
    private MQSender sender;
    
    @RequestMapping( value = "/")
    @ResponseBody
    public Result<String> home() {
        User user = userService.getById(1);
        return Result.success("一切正常");
    }
    
    @RequestMapping(value = "/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "thymeleaf");
        return "hello";
    }
    
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 2, User.class);
        return Result.success(user);
    }
    
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("xupu");
        boolean v1 = redisService.set(UserKey.getById,"" + 2, user);
        return Result.success(v1);
    }
    
    @RequestMapping(value = "/db")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }
    
    @RequestMapping(value = "/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        return Result.success(userService.tx());
    }
    
    @RequestMapping(value = "/mq")
    @ResponseBody
    public Result<String> mq() {
        sender.send("hello, zhouyu");
        return Result.success("yes");
    }
}
