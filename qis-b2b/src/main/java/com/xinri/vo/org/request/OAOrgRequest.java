package com.xinri.vo.org.request;

import com.qis.common.persistence.DataEntity;
import com.xinri.po.organizations.Organizations;

public class OAOrgRequest extends DataEntity<OAOrgRequest> {
    private String supId;
    private String type; //sib同级，child下级
    private String name;
    private String descr;
    private String parentOrg;
    private String parentDept;
    private String oaNo;
    private String u9No;
    private String code;

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(String parentOrg) {
        this.parentOrg = parentOrg;
    }

    public String getParentDept() {
        return parentDept;
    }

    public void setParentDept(String parentDept) {
        this.parentDept = parentDept;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
    }

    public String getU9No() {
        return u9No;
    }

    public void setU9No(String u9No) {
        this.u9No = u9No;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
