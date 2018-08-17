package com.xinri.dao.user;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.user.Users;

import java.util.List;

/**
 * 类名:UsersMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface UsersMapper extends CrudDao<Users>{

  public void insertUsersList(List<Users> users);
}

