package com.xinri.dao.role;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.role.RoleClasses;
import com.xinri.vo.role.RoleClassesVo;

import java.util.List;

/**
 * 类名:RoleClassesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RoleClassesMapper extends CrudDao<RoleClasses>{

 public List<RoleClassesVo> findListByVo(RoleClassesVo roleClassesVo);

// public int insertSelective(RoleClasses roleClasses);
}

