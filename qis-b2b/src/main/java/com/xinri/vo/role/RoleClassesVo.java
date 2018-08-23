package com.xinri.vo.role;

import com.xinri.po.role.RoleClasses;
import java.util.*;

public class RoleClassesVo extends RoleClasses {
    private String name;

    private String code;

    //描述
    private String descr;

    //创建日期
    private Date startCreated;

    @Override
    public String getDescr() {
        return descr;
    }

    @Override
    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getStartCreated() {
        return startCreated;
    }

    public void setStartCreated(Date startCreated) {
        this.startCreated = startCreated;
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




}
