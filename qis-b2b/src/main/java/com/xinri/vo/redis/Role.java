package com.xinri.vo.redis;

import com.xinri.po.moduleInfo.ModuleInfoes;

import java.util.List;

public class Role {

    Boolean userOrGroup; //0个人 1组织

    Long id;//个人id 组织id

    Long roleId;

    List<ModuleInfoes> moduleInfoes;

}
