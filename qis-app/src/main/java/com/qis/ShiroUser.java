package com.qis;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 *
 * @author 夏善勇
 */
public class ShiroUser implements Serializable{
    private static final long serialVersionUID=-1373760761780840081L;
    public Long id;
    public String loginName;
    public String name;
    public Integer type;//1.系统管理员 2.员工
    public Long orgId;//组织id
    public Long depId;//部门id，

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public ShiroUser(Long id, String loginName, String name, Integer type){
        this.id=id;
        this.loginName=loginName;
        this.name=name;
        this.type=type;

    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getLoginName(){
        return loginName;
    }
    public Integer getType(){
        return type;
    }
    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString(){
        return loginName;
    }
    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode(){
        return Objects.hashCode(loginName);
    }
    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(getClass()!=obj.getClass()){
            return false;
        }
        ShiroUser other=(ShiroUser)obj;
        if(loginName==null){
            if(other.loginName!=null){
                return false;
            }
        }else if(!loginName.equals(other.loginName)){
            return false;
        }
        return true;
    }
}