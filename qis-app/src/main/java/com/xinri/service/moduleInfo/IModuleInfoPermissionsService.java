package com.xinri.service.moduleInfo;

import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.vo.moduleInfo.RoleModuleInFoPerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IModuleInfoPermissionsService extends IBaseService<ModuleInfoPermissions> {
    public int insert(ModuleInfoPermissions moduleInfoPermissions);

    public List<Long> getPermissionIds(Long id);

    public void relate(ModuleInfoPermissions moduleInfoPermission);

    public Long getId(Long moduleId, Long permissionId);

    public Long[] getIdsByModuleId(Long moduleId);

    public Long[] getIdsByPermissionId(Long permissionId);

    public Long[] getIds(Long moduleId, List<Long> ids);

    public void deleteByPermissionId(Long permissionId);

    public void deleteByModuleId(Long moduleId);

    public List<RoleModuleInFoPerVo> getRoleModuleInFoPerVo(RoleModuleInFoPerVo vo);
}

