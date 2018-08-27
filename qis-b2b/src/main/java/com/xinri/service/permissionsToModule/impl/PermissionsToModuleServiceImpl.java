package com.xinri.service.permissionsToModule.impl;

import com.qis.common.service.CrudService;
import com.xinri.dao.permissionsToModule.ModuleInfoPermissionsMapper;
import com.xinri.po.permissionsToModule.ModuleInfoPermissions;
import com.xinri.service.permissionsToModule.IPermissionsToModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "permissionsToModuleService")
public class PermissionsToModuleServiceImpl extends CrudService<ModuleInfoPermissionsMapper,ModuleInfoPermissions> implements IPermissionsToModuleService {

    @Override
    public int insert(ModuleInfoPermissions moduleInfoPermissions) {
        return dao.insertSelective(moduleInfoPermissions);
    }

    @Override
    public List<Long> getPermissionIds(Long id) {
        return dao.getPermissionIds(id);
    }


}