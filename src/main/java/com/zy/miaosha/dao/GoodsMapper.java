package com.zy.miaosha.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zy.miaosha.domain.Goods;
import com.zy.miaosha.domain.MiaoshaGoods;
import com.zy.miaosha.vo.GoodsVo;

@Mapper
public interface GoodsMapper {
    
    @Select("select  g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.stock_count, date_format(mg.start_date, '%Y-%c-%e %T') as start_date, date_format(mg.end_date, '%Y-%c-%e %T') as end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(long goodsId);

   @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{id}")
    public void reduceStock(MiaoshaGoods g);
    
}
