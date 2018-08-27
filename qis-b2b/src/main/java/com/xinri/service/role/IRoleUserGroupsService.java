package com.xinri.service.role;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.role.RoleUserGroups;
import com.xinri.po.user.UserGroups;
import com.xinri.util.AjaxStatus;

import java.util.Map;

/**
 * <p></p>
 * 类名:RoleUserGroupsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRoleUserGroupsService extends IBaseService<RoleUserGroups>{
    DataTable QueryGroupNotInRoleIdList(DataTable<UserGroups> dt, Map<String,Object> searchParams,String roleId);

    DataTable QueryUserByRoleIdList(DataTable<UserGroups> dt, Map<String,Object> searchParams,String roleId);

    AjaxStatus JoinRole(Long roleId,Long groupId);

    AjaxStatus LeaveRole(Long roleId,Long groupId);
}

