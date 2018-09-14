package com.xinri.vo.moduleInfo;

import java.util.List;

public class RoleModuleInfoCVo {
    private Long roleId;
    private Long moduleId;
    private List<RoleModuleInfoColVo> mList;

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

    public List<RoleModuleInfoColVo> getmList() {
        return mList;
    }

    public void setmList(List<RoleModuleInfoColVo> mList) {
        this.mList = mList;
    }
}
