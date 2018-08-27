package com.xinri.service.moduleInfo;

import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.RoleModuleInfos;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
public interface IRoleModuleInfosService extends IBaseService<RoleModuleInfos> {
    List<Long> getModuleIds(java.lang.Long id);
}
