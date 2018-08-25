package com.xinri.vo.columnData;

import com.qis.common.persistence.DataEntity;
import com.xinri.po.moduleInfo.ColumnDatas;

import java.util.Date;

public class ColumnDataVo extends ColumnDatas {
    private String name;
    private String code;
    private String descr;//描述
    private String guidId;//唯一标识符id
    private Integer isEffective; //1有效 0无效
    private Date effectiveDateStart; //生效日期
    private Date effectiveDateEnd;//失效日期


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getGuidId() {
        return guidId;
    }

    public void setGuidId(String guidId) {
        this.guidId = guidId;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public Date getEffectiveDateStart() {
        return effectiveDateStart;
    }

    public void setEffectiveDateStart(Date effectiveDateStart) {
        this.effectiveDateStart = effectiveDateStart;
    }

    public Date getEffectiveDateEnd() {
        return effectiveDateEnd;
    }

    public void setEffectiveDateEnd(Date effectiveDateEnd) {
        this.effectiveDateEnd = effectiveDateEnd;
    }

}
