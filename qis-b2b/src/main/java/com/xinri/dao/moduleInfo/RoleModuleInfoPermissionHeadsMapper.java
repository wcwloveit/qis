package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionHeads;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
 @MyBatisDao
public interface RoleModuleInfoPermissionHeadsMapper extends CrudDao<RoleModuleInfoPermissionHeads>{
    List<Long> getIds(Long id);

    List<Long> getPerIds(Long moduleId,Long roleId);


}

