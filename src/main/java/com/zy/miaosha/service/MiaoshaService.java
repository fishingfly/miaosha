package com.zy.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.miaosha.dao.GoodsMapper;
import com.zy.miaosha.domain.MiaoshaUser;
import com.zy.miaosha.domain.OrderInfo;
import com.zy.miaosha.vo.GoodsVo;

@Service
public class MiaoshaService {
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        // 减少库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        // order_info miaoshs_order
        OrderInfo orderInfo = orderService.createOrder(user, goods);
        
        return orderInfo;
    }
}
