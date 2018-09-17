package com.xinri.vo.item;

public class ItemProductVo {
    public String itemId;//商品ID
    public String modelName;//商品名称
    public String modelSpecification;//车型规格
    public String colorName;//颜色规格
    public String orgName;//组织名称
    public String picUrl;//图片地址
    public String rulePrice;//规则价格

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelSpecification() {
        return modelSpecification;
    }

    public void setModelSpecification(String modelSpecification) {
        this.modelSpecification = modelSpecification;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getRulePrice() {
        return rulePrice;
    }

    public void setRulePrice(String rulePrice) {
        this.rulePrice = rulePrice;
    }
}
