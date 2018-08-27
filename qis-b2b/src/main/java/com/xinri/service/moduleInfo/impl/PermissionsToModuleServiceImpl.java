package com.xinri.service.moduleInfo.impl;

import com.qis.common.service.CrudService;
import com.xinri.dao.moduleInfo.ModuleInfoPermissionsMapper;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.service.moduleInfo.IPermissionsToModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service(value = "permissionsToModuleService")
public class PermissionsToModuleServiceImpl extends CrudService<ModuleInfoPermissionsMapper, ModuleInfoPermissions> implements IPermissionsToModuleService {

    @Override
    public int insert(ModuleInfoPermissions moduleInfoPermissions) {
        return dao.insertSelective(moduleInfoPermissions);
    }

    @Override
    public List<Long> getPermissionIds(Long id) {
        return dao.getPermissionIds(id);
    }


}
