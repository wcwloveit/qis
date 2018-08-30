package com.xinri.service.moduleInfo;

import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IModuleInfoPermissionsService extends IBaseService<ModuleInfoPermissions> {
    int insert(ModuleInfoPermissions moduleInfoPermissions);

    List<Long> getPermissionIds(Long id);

    void relate(ModuleInfoPermissions moduleInfoPermission);

    Long getId(Long moduleId, Long permissionId);

    Long[] getIdsByModuleId(Long moduleId);

    Long[] getIdsByPermissionId(Long permissionId);

    Long[] getIds(Long moduleId, List<Long> ids);

    void deleteByPermissionId(Long permissionId);

    void deleteByModuleId(Long moduleId);
}

