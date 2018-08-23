package com.xinri.service.user;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.user.UserGroups;
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
}

