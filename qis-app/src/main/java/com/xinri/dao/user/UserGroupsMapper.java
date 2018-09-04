package com.xinri.dao.user;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.user.UserGroups;
import com.xinri.vo.users.UserGroupsVo;

import java.util.List;

/**
 * 类名:UserGroupsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface UserGroupsMapper extends CrudDao<UserGroups>{

 public List<UserGroupsVo> findListByVo(UserGroupsVo userGroupsVo);

 public int insertSelective(UserGroups userGroups);
}

