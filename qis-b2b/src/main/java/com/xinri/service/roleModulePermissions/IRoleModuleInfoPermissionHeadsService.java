package com.xinri.service.roleModulePermissions;
import com.qis.common.service.IBaseService;
import com.xinri.po.roleModulePermissions.RoleModuleInfoPermissionHeads;

import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoPermissionHeadsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRoleModuleInfoPermissionHeadsService extends IBaseService<RoleModuleInfoPermissionHeads>{

    List<Long> getIds(Long id);

    List<Long> getPerIds(Long moduleId,Long roleId);

}

