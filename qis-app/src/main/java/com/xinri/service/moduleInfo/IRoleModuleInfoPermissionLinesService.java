package com.xinri.service.moduleInfo;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionLines;
import com.xinri.vo.moduleInfo.RoleModuleInFoPermissionLineVo;

import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoPermissionLinesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRoleModuleInfoPermissionLinesService extends IBaseService<RoleModuleInfoPermissionLines>{


    DataTable<RoleModuleInFoPermissionLineVo> getModulesForRole(DataTable<RoleModuleInFoPermissionLineVo> dt, Long roleId, Long infoId);

    List<Long> getIdsByDiff(List<Long> ids);
}

