package com.xinri.vo.log;

import com.xinri.po.logs.LoginLogs;

import java.util.Date;

public class LoginLogsVo extends LoginLogs {

    //用户IP
    private String ipAddress;

    //用户id
    private Long userId;

    //登录类别id 登入 登出
    private Long dataTypeId;

    //登录类别 登入 登出
    private String dataTypeName;

    //创建日期
    private Date createdTime ;

    //登录账号
    private String userNo;

    //姓名
    private String name;

    //系统用户名称
    private String sysName;

    //系统用户登录名
    private String account;

    //用户类别id  管理员 普通用户
    private Integer isEffective;

    //用户类别名称
    private String isEffectiveName;

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Long getDataTypeId() {
        return dataTypeId;
    }

    @Override
    public void setDataTypeId(Long dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public Integer getIsEffective() {
        return isEffective;
    }

    @Override
    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getIsEffectiveName() {
        return isEffectiveName;
    }

    public void setIsEffectiveName(String isEffectiveName) {
        this.isEffectiveName = isEffectiveName;
    }
}
