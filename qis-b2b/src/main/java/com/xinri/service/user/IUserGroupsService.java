package com.xinri.service.user;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.user.UserGroups;
import com.xinri.po.user.UserUserGroups;
import com.xinri.po.user.Users;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.users.UserGroupsVo;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:UserGroupsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IUserGroupsService extends IBaseService<UserGroups>{

    public DataTable<UserGroupsVo> findListByVo(DataTable<UserGroupsVo>dt, Map<String, Object> searchParams);


    public DataTable QueryUserNotInRidList(DataTable<Users> dt, Map<String, Object> searchParams, String roleId);

    public AjaxStatus JoinRole(Long roleId, Long empId);

    public AjaxStatus LeaveRole(Long roleId, Long empId);

    public DataTable QueryUserByRidList(DataTable<Users> dt, Map<String, Object> searchParams, String roleId);
}

