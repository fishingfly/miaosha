package com.zy.miaosha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.miaosha.domain.Goods;
import com.zy.miaosha.domain.MiaoshaGoods;
import com.zy.miaosha.dao.GoodsMapper;
import com.zy.miaosha.vo.GoodsVo;

@Service
public class GoodsService {
    
    @Autowired
    private GoodsMapper goodsMapper;
    
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        g.setStockCount(goods.getStockCount() - 1);
        goodsMapper.reduceStock(g);
        
    }
    
    
    

}
