package com.xinri.vo.dept;

public class OADepartmentVo {

    public int id ;

    public String departmentname ;

    public String departmentmark ;

    public String departmentcode ;

    public String departmentpinyin ;

    public int companyId ;

    public int parentDeptId ;

    public String bmfzr ;

    public String bmfgld ;

    public String canceled ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDepartmentmark() {
        return departmentmark;
    }

    public void setDepartmentmark(String departmentmark) {
        this.departmentmark = departmentmark;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getDepartmentpinyin() {
        return departmentpinyin;
    }

    public void setDepartmentpinyin(String departmentpinyin) {
        this.departmentpinyin = departmentpinyin;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(int parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getBmfzr() {
        return bmfzr;
    }

    public void setBmfzr(String bmfzr) {
        this.bmfzr = bmfzr;
    }

    public String getBmfgld() {
        return bmfgld;
    }

    public void setBmfgld(String bmfgld) {
        this.bmfgld = bmfgld;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }
}
