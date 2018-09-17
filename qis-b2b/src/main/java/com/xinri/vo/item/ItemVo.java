package com.xinri.vo.item;

import com.xinri.po.item.ItemProduct;

import java.math.BigDecimal;

public class ItemVo extends ItemProduct {
    private String updatedTime;
    private String createdTime;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private Integer isSynchro;

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }

    public Integer getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(Integer isSynchro) {
        this.isSynchro = isSynchro;
    }
}
