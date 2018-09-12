package com.xinri.vo.redis;

import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class Module extends ModuleInfoes {
    @Override
    public String toString() {
        return "Module{" +
                "permissionList=" + permissionList +
                '}';
    }

    List<Permissions> permissionList;

    public List<Permissions> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permissions> permissionList) {
        this.permissionList = permissionList;
    }
}
