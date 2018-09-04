package com.xinri.vo.log;

import com.xinri.po.logs.LoginLogs;

import java.util.Date;

public class LoginLogsVo extends LoginLogs {

    //用户IP
    private String ipAddress;

    //用户id
    private Long userId;

    //类别id
    private Long dataTypeId;

    //创建日期
    private Date createdOn;

    //登录名
    private String userName;

    //姓名
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public Long getDataTypeId() {
        return dataTypeId;
    }

    @Override
    public void setDataTypeId(Long dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
}
