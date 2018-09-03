package com.xinri.vo.redis;

import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.role.Roles;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Redis {

    private String loginName;

    private String password;

    private String salt;

    List<ModuleInfoes> moduleInfoesList;

    List<Roles> roles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ModuleInfoes> getModuleInfoesList() {
        return moduleInfoesList;
    }

    public void setModuleInfoesList(List<ModuleInfoes> moduleInfoesList) {
        this.moduleInfoesList = moduleInfoesList;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
