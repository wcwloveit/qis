package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionLines;
import com.xinri.vo.moduleInfo.RoleModuleInFoPermissionLineVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名:RoleModuleInfoPermissionLinesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RoleModuleInfoPermissionLinesMapper extends CrudDao<RoleModuleInfoPermissionLines>{

    List<RoleModuleInFoPermissionLineVo> findListByVo(RoleModuleInFoPermissionLineVo vo);

    List<Long> getIdsByDiff(@Param("list") List<Long> ids);
}

