package com.xinri.vo.users;

import com.xinri.po.user.SysUser;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
public class SysUserVo extends SysUser {
    String birth;
    String dept;
    String role;
    String startBirth;
    String endBirth;

    public String getStartBirth() {
        return startBirth;
    }

    public void setStartBirth(String startBirth) {
        this.startBirth = startBirth;
    }

    public String getEndBirth() {
        return endBirth;
    }

    public void setEndBirth(String endBirth) {
        this.endBirth = endBirth;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
