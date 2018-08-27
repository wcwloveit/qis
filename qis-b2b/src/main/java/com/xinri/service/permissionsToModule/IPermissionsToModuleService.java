package com.xinri.service.permissionsToModule;

import com.qis.common.service.IBaseService;
import com.xinri.po.permissionsToModule.ModuleInfoPermissions;

import java.util.List;

public interface IPermissionsToModuleService extends IBaseService<ModuleInfoPermissions> {
    int insert(ModuleInfoPermissions moduleInfoPermissions);

    List<Long> getPermissionIds(Long id);
}
