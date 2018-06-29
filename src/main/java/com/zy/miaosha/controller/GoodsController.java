package com.zy.miaosha.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(Model model, 
            @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
            @RequestParam(value=MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return "login";
        } 
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        MiaoshaUser user = userService.getByToken(token);
        model.addAttribute("user", user);
        return "goods_list";
    }
}
