package com.xinri.service.moduleInfo.impl;

import com.qis.common.service.CrudService;
import com.xinri.dao.moduleInfo.RoleModuleInfosMapper;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.moduleInfo.RoleModuleInfos;
import com.xinri.service.moduleInfo.IRoleModuleInfosService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("roleModuleInfosService")
public class RoleModuleInfosServiceImpl extends CrudService<RoleModuleInfosMapper, RoleModuleInfos> implements IRoleModuleInfosService {
    @Override
    public List<Long> getModuleIds(Long id) {
        return dao.getModuleIds(id);
    }


}
