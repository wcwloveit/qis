package com.xinri.vo.org;

public class OrgListVo {
    public Integer id;
    public String no;
    public String orgName;
    public String orgDescr;
    public String orgCode;
    public String orgStatus;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescr() {
        return orgDescr;
    }

    public void setOrgDescr(String orgDescr) {
        this.orgDescr = orgDescr;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }
}
