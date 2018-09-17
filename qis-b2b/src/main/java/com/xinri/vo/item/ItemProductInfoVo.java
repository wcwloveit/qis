package com.xinri.vo.item;

import java.math.BigDecimal;

public class ItemProductInfoVo {

    private Long id;
    private String orgCode;
    private String modelSpecification;
    private String modelName;
    private BigDecimal initialPrice;
    private String colorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getModelSpecification() {
        return modelSpecification;
    }

    public void setModelSpecification(String modelSpecification) {
        this.modelSpecification = modelSpecification;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
