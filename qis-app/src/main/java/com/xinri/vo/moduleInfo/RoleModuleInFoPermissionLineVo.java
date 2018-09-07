package com.xinri.vo.moduleInfo;

import com.xinri.po.moduleInfo.RoleModuleInfoPermissionLines;

public class RoleModuleInFoPermissionLineVo extends RoleModuleInfoPermissionLines {
    private String tname;
    private String tcode;
    private String udname;
    private String udno;
    private Long moduleId;//module_info_id 模块id
    private Long roleId;//角色id

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public String getUdname() {
        return udname;
    }

    public void setUdname(String udname) {
        this.udname = udname;
    }

    public String getUdno() {
        return udno;
    }

    public void setUdno(String udno) {
        this.udno = udno;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
