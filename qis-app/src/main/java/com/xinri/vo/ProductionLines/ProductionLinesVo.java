package com.xinri.vo.ProductionLines;

import com.xinri.po.production.ProductionLines;

import java.util.Date;

public class ProductionLinesVo extends ProductionLines {
    //产线名称
    private String name;
    //产线编号
    private String code;
    //备注
    private String descr;
    //组织id
    private Long useOrganizationId;
    //组织名称
    private String useOrganizationName;
    //系统id
    private Long useSystemId;
    //系统名称
    private String useSystemName;
    //创建日期
    private Date createdOn ;

    @Override
    public Long getUseOrganizationId() {
        return useOrganizationId;
    }

    @Override
    public void setUseOrganizationId(Long useOrganizationId) {
        this.useOrganizationId = useOrganizationId;
    }

    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescr() {
        return descr;
    }

    @Override
    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUseOrganizationName() {
        return useOrganizationName;
    }

    public void setUseOrganizationName(String useOrganizationName) {
        this.useOrganizationName = useOrganizationName;
    }

    @Override
    public Long getUseSystemId() {
        return useSystemId;
    }

    @Override
    public void setUseSystemId(Long useSystemId) {
        this.useSystemId = useSystemId;
    }

    public String getUseSystemName() {
        return useSystemName;
    }

    public void setUseSystemName(String useSystemName) {
        this.useSystemName = useSystemName;
    }
}
