package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.vo.moduleInfo.RoleModuleInFoPerVo;
import com.xinri.vo.redis.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
 @MyBatisDao
public interface ModuleInfoPermissionsMapper extends CrudDao<ModuleInfoPermissions>{
    List<Long> getPermissionIds(Long id);
    int relate(ModuleInfoPermissions moduleInfoPermission);

    Long getId(@Param("moduleId") Long moduleId, @Param("permissionId") Long permissionId);

    Long[] getIdsByModuleId(@Param("moduleId") Long moduleId);

    Long[] getIdsByPermissionId(@Param("permissionId") Long permissionId);

    Long[] getIds(@Param("list")List<Long> ids,@Param("moduleId") Long moduleId);

    void deleteByPermissionId(Long permissionId);

    void deleteByModuleId(Long moduleId);

    List<RoleModuleInFoPerVo> getRoleModuleInFoPerVo(RoleModuleInFoPerVo vo);

    List<Resource> getResource();
}

