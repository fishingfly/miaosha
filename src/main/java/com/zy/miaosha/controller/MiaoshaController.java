package com.zy.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zy.miaosha.domain.MiaoshaOrder;
import com.zy.miaosha.domain.MiaoshaUser;
import com.zy.miaosha.domain.OrderInfo;
import com.zy.miaosha.redis.RedisService;
import com.zy.miaosha.result.CodeMsg;
import com.zy.miaosha.service.GoodsService;
import com.zy.miaosha.service.MiaoshaService;
import com.zy.miaosha.service.OrderService;
import com.zy.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private MiaoshaService miaoshaService;
    
    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user, @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        // 判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }
        // 判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderbyUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEAT_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
    

}
