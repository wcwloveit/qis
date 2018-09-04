package com.xinri.dao.user;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.user.UserUserGroups;
import com.xinri.po.user.Users;
import com.xinri.vo.users.UserVo;

import java.util.List;

/**
 * 类名:UserUserGroupsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface UserUserGroupsMapper extends CrudDao<UserUserGroups>{

 public List<Users> getUserByUserGroupsId(UserVo userVo);

 public List<Users> getNotUserByUserGroupsId(UserVo userVo);

}

