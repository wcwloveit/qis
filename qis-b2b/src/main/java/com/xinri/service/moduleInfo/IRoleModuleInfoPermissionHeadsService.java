package com.xinri.service.moduleInfo;

import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionHeads;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IRoleModuleInfoPermissionHeadsService extends IBaseService<RoleModuleInfoPermissionHeads> {

    List<Long> getIds(Long id);

    List<Long> getPerIds(Long moduleId, Long roleId);

}

