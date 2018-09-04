package com.xinri.vo.org;

import com.xinri.po.departments.Departments;
import com.xinri.po.organizations.Organizations;

public class OrgInfoVo extends Departments{

    private String parentOrgName;
    private String parentDeptName;

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

    public String getParentDeptName() {
        return parentDeptName;
    }

    public void setParentDeptName(String parentDeptName) {
        this.parentDeptName = parentDeptName;
    }
}
