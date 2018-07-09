package com.zy.miaosha.vo;

import java.util.Date;

import com.zy.miaosha.domain.Goods;
import com.zy.miaosha.util.String2DateUtil;

public class GoodsVo extends Goods{
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Double miaoshaPrice;
    
    public Integer getStockCount() {
        return stockCount;
    }
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = String2DateUtil.stringTransferToDate(startDate);
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = String2DateUtil.stringTransferToDate(endDate);
    }
    public Double getMiaoshaPrice() {
        return miaoshaPrice;
    }
    public void setMiaoshaPrice(Double miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }
}
