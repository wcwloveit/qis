package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.RoleModuleInfos;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@MyBatisDao
public interface RoleModuleInfosMapper extends CrudDao<RoleModuleInfos>{
        List<Long> getModuleIds(Long id);
}

