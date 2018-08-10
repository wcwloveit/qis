package com.xinri.po.roleLog;

import com.qis.common.persistence.DataEntity;


public class OperationRecord  extends DataEntity<OperationRecord> {
    private Long i;
    private String code;
    private String name;
    private String operation;
    private String time;
    private String remark;

    public void setI(Long i) {
        this.i = i;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getI() {
        return i;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getOperation() {
        return operation;
    }

    public String getTime() {
        return time;
    }

    public String getRemark() {
        return remark;
    }
}
