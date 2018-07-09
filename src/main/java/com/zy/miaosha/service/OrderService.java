package com.zy.miaosha.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.miaosha.dao.OrderMapper;
import com.zy.miaosha.domain.MiaoshaOrder;
import com.zy.miaosha.domain.MiaoshaUser;
import com.zy.miaosha.domain.OrderInfo;
import com.zy.miaosha.vo.GoodsVo;

@Service
public class OrderService {
    
    @Autowired
    private OrderMapper orderMapper;

    public MiaoshaOrder getMiaoshaOrderbyUserIdGoodsId(Long userId, long goodsId) {
        return orderMapper.getMiaoshaOrderbyUserIdGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderMapper.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderMapper.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }

}
