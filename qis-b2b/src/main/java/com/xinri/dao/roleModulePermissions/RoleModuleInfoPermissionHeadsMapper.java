package com.xinri.dao.roleModulePermissions;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.roleModulePermissions.RoleModuleInfoPermissionHeads;

import java.util.List;

/**
 * 类名:RoleModuleInfoPermissionHeadsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RoleModuleInfoPermissionHeadsMapper extends CrudDao<RoleModuleInfoPermissionHeads>{
    List<Long> getIds(Long id);

    List<Long> getPerIds(Long moduleId,Long roleId);


}

