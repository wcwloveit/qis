package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataHeads;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名:RoleModuleInfoColumnDataHeadsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RoleModuleInfoColumnDataHeadsMapper extends CrudDao<RoleModuleInfoColumnDataHeads>{

 List<Long> getIds(Long id);

 List<Long> getColIds(@Param("moduleId")Long moduleId, @Param("roleId")Long roleId);

 void celar(@Param("moduleId") Long moduleId,@Param("roleId") Long roleId);

 void deleteByRelateId(@Param("list") List<Long> ids);

 void deleteByDiff(@Param("list") List<Long> ids,@Param("moduleId")Long moduleId);

 List<Long> getIdsByDiff(@Param("list") List<Long> ids);

 List<Long> getBeforeIds(@Param("roleId") Long roleId,@Param("moduleId") Long moduleId);

 List<Long> getAfterIds(@Param("roleId") Long roleId,@Param("list") List<Long> ids);

}

