package com.zy.miaosha.controller;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.zy.miaosha.domain.MiaoshaUserService;
import com.zy.miaosha.redis.RedisService;
import com.zy.miaosha.result.CodeMsg;
import com.zy.miaosha.result.Result;
import com.zy.miaosha.util.ValidatorUtil;
import com.zy.miaosha.vo.LoginVo;

@Controller
@RequestMapping(value= "/login")
public class LoginController {
    
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    RedisService redisService;
    
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginvo) {
        log.info(loginvo.toString());
        miaoshaUserService.login(response, loginvo);
        return Result.success(true);
    }
    
}
