package com.xinri.vo.moduleInfo;

import java.util.List;

public class RoleModuleInfoPCVo {
    private Long roleId;
    private List<RoleModuleInFoPerVo> mList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<RoleModuleInFoPerVo> getmList() {
        return mList;
    }

    public void setmList(List<RoleModuleInFoPerVo> mList) {
        this.mList = mList;
    }
}
