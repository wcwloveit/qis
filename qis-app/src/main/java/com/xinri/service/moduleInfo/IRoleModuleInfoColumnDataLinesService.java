package com.xinri.service.moduleInfo;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataLines;
import com.xinri.vo.moduleInfo.RoleModuleInFoColumnDataLineVo;
import com.xinri.vo.moduleInfo.RoleModuleInFoPermissionLineVo;

/**
 * <p></p>
 * 类名:RoleModuleInfoColumnDataLinesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRoleModuleInfoColumnDataLinesService extends IBaseService<RoleModuleInfoColumnDataLines>{

    DataTable<RoleModuleInFoColumnDataLineVo> getModulesForRole(DataTable<RoleModuleInFoColumnDataLineVo> dt, Long roleId, Long infoId);

}

