package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataLines;
import com.xinri.vo.moduleInfo.RoleModuleInFoColumnDataLineVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名:RoleModuleInfoColumnDataLinesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RoleModuleInfoColumnDataLinesMapper extends CrudDao<RoleModuleInfoColumnDataLines>{
 List<RoleModuleInFoColumnDataLineVo> findListByVo(RoleModuleInFoColumnDataLineVo vo);

 List<Long> getIdsByDiff(@Param("list") List<Long> ids);
}

