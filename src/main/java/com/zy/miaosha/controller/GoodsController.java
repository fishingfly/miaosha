package com.zy.miaosha.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.context.webflux.SpringWebFluxContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import com.zy.miaosha.domain.MiaoshaUser;
import com.zy.miaosha.redis.GoodsKey;
import com.zy.miaosha.redis.RedisService;
import com.zy.miaosha.service.GoodsService;
import com.zy.miaosha.service.MiaoshaUserService;
import com.zy.miaosha.util.StringUtil;
import com.zy.miaosha.vo.GoodsVo;

@Controller
@RequestMapping(value= "/goods")
public class GoodsController {
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @RequestMapping(value="/to_list", produces="text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user) {
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }
    
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model,MiaoshaUser user,
            @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user", user);
        
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        System.out.println(goods.getStartDate());
        System.out.println(goods.getEndDate());
        long startAt =  (goods.getStartDate()).getTime();
        long endAt = (goods.getEndDate()).getTime();
        long now = System.currentTimeMillis();
        
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
    
    
}
