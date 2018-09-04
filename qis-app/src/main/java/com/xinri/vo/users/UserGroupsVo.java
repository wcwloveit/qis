package com.xinri.vo.users;

import com.xinri.po.user.UserGroups;

import java.util.*;

public class UserGroupsVo extends UserGroups {

    private String name;

    private String code;

    private String descr;

    //创建日期
    private Date createdTime ;

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
