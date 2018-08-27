package com.xinri.service.user;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.departments.Departments;
import com.xinri.po.user.Users;
import com.xinri.vo.users.OAUsersVo;
import com.xinri.vo.users.UserListVo;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:UsersService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IUsersService extends IBaseService<Users>{

    public void syncUsers(List<OAUsersVo> oaUsersVos);

    public DataTable<UserListVo> getUserList(Map<String, Object> searchParams, String id, DataTable<UserListVo> dt);

    public List<Users> findAllDeptUsers(List<Departments> departments);
}

