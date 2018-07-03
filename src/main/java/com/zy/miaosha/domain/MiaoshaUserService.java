package com.zy.miaosha.domain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.miaosha.dao.MiaoshaUserMapper;
import com.zy.miaosha.exception.GlobalException;
import com.zy.miaosha.redis.MiaoshaUserKey;
import com.zy.miaosha.redis.RedisService;
import com.zy.miaosha.result.CodeMsg;
import com.zy.miaosha.util.MD5Util;
import com.zy.miaosha.util.UUIDUtil;
import com.zy.miaosha.vo.LoginVo;

@Service
public class MiaoshaUserService {
    
    public static final String COOKIE_NAME_TOKEN = "token";
    
    @Autowired
    MiaoshaUserMapper miaoshaUserMapper;
    @Autowired
    RedisService redisService;
    
    public MiaoshaUser getById(long id) {
        return miaoshaUserMapper.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        
        // 判断手机号是否存在
        MiaoshaUser  miaoshaUser = getById(Long.parseLong(mobile));
        if (miaoshaUser == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPass = miaoshaUser.getPassword();
        String saltDB = miaoshaUser.getSalt();
        String passwordCal = MD5Util.formPassToDBPass(password, saltDB);
        if (!passwordCal.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, miaoshaUser);
        return true;
    }
    private void addCookie(HttpServletResponse response, String token, MiaoshaUser miaoshaUser) {
        redisService.set(MiaoshaUserKey.token, token, miaoshaUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        System.out.println(MiaoshaUserKey.token.getExpireSeconds());
        cookie.setMaxAge(MiaoshaUserKey.token.getExpireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        // 延长token有效期
        addCookie(response, token, user);
        return user;
        
    }
}

