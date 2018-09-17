package com.xinri.vo.item;

public class ItemChooseVO {
    public String modelSpecification;//车型规格
    public String modelName;//车型名称
    public String colorName;//颜色名称
    public String pic;//图片
    public String orgName;//组织名称

    public ItemChooseVO(String modelSpecification, String modelName, String colorName, String pic, String orgName) {
        this.modelSpecification = modelSpecification;
        this.modelName = modelName;
        this.colorName = colorName;
        this.pic = pic;
        this.orgName = orgName;
    }
}
