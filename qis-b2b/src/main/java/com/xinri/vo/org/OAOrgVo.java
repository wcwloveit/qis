package com.xinri.vo.org;

public class OAOrgVo {
    public int id;

    public int parentOrgId ;

    public String  orgcode ;

    public String orgname ;

    public String orgremark ;

    public String canceled ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(int parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgremark() {
        return orgremark;
    }

    public void setOrgremark(String orgremark) {
        this.orgremark = orgremark;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }
}
