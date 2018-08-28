package com.xinri.service.moduleInfo.impl;

import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.dao.moduleInfo.ModuleInfoPermissionsMapper;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("moduleInfoPermissionsService")
public class ModuleInfoPermissionsServiceImpl extends CrudService<ModuleInfoPermissionsMapper, ModuleInfoPermissions> implements IModuleInfoPermissionsService {
    @Override
    public int insert(ModuleInfoPermissions moduleInfoPermissions) {
        return dao.insertSelective(moduleInfoPermissions);
    }

    @Override
    public List<Long> getPermissionIds(Long id) {
        return dao.getPermissionIds(id);
    }


}