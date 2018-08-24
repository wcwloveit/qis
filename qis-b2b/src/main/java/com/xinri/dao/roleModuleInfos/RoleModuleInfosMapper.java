package com.xinri.dao.roleModuleInfos;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.permissionsToModule.ModuleInfoPermissions;
import com.xinri.po.roleModuleInfos.RoleModuleInfos;

import java.util.List;

@MyBatisDao
public interface RoleModuleInfosMapper extends CrudDao<RoleModuleInfos>{
        List<Long> getModuleIds(Long id);
}

