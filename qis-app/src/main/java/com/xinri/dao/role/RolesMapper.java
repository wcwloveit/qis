package com.xinri.dao.role;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.role.Roles;
import com.xinri.vo.role.RolesVo;
import com.xinri.vo.users.SysUserVo;

import java.util.List;

/**
 * 类名:RolesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RolesMapper extends CrudDao<Roles>{
 List<RolesVo> findListByVo(RolesVo vo);
}

