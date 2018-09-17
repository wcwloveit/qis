package com.xinri.vo.item;

import com.xinri.po.item.ItemColor;

public class ItemColorInfoVo extends ItemColor {
    private Long id;
    private String colorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }



}

