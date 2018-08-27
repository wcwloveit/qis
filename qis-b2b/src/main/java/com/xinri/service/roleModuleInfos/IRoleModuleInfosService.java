package com.xinri.service.roleModuleInfos;

import com.qis.common.service.IBaseService;
import com.xinri.po.roleModuleInfos.RoleModuleInfos;

import java.util.List;

public interface IRoleModuleInfosService extends IBaseService<RoleModuleInfos>{
    List<Long> getModuleIds(java.lang.Long id);
}
