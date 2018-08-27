package com.xinri.service.roleModuleInfos.impl;

import com.qis.common.service.CrudService;
import com.xinri.dao.roleModuleInfos.RoleModuleInfosMapper;
import com.xinri.po.roleModuleInfos.RoleModuleInfos;
import com.xinri.service.roleModuleInfos.IRoleModuleInfosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleModuleInfosService")
public class RoleModuleInfosServiceImpl  extends CrudService<RoleModuleInfosMapper,RoleModuleInfos> implements IRoleModuleInfosService {
    @Override
    public List<Long> getModuleIds(Long id) {
        return dao.getModuleIds(id);
    }
}
