package com.xinri.service.moduleInfo.impl;

import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionHeads;
import com.xinri.dao.moduleInfo.RoleModuleInfoPermissionHeadsMapper;
import com.xinri.service.moduleInfo.IRoleModuleInfoPermissionHeadsService;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("roleModuleInfoPermissionHeadsService")
public class RoleModuleInfoPermissionHeadsServiceImpl extends CrudService<RoleModuleInfoPermissionHeadsMapper, RoleModuleInfoPermissionHeads> implements IRoleModuleInfoPermissionHeadsService {


    @Override
    public List<Long> getIds(Long id) {
        return dao.getIds(id);
    }

    @Override
    public List<Long> getPerIds(Long moduleId, Long roleId) {
        return dao.getPerIds(moduleId, roleId);
    }

    @Override
    public void celar(Long moduleId, Long roleId) {
        dao.celar(moduleId, roleId);
    }
}