package com.xinri.dao.module;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.module.ModuleInfoes;

import java.util.List;

/**
 * 类名:ModuleInfoesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface ModuleInfoesMapper extends CrudDao<ModuleInfoes>{
  List<ModuleInfoes> getModulesForRole(List<Long> ids);

}

