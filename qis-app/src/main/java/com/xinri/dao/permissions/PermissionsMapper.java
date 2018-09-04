package com.xinri.dao.permissions;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.permissions.Permissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
 @MyBatisDao
public interface PermissionsMapper extends CrudDao<Permissions>{
     List<Permissions> getPermissionsByModuleIdandRoleId(@Param("list") List<Long> list,@Param("moduleId") Long moduleId);
}

