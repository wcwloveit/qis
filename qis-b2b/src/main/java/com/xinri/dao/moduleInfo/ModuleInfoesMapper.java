package com.xinri.dao.moduleInfo;

import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.vo.redis.Redis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@MyBatisDao
public interface ModuleInfoesMapper extends CrudDao<ModuleInfoes> {
    List<ModuleInfoes> getModulesForRole(List<Long> ids);

    Redis getModulesByUserId(@Param("id") Long id);

}

