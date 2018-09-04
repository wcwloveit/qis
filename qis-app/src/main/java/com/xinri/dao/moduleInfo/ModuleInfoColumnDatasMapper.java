package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名:ModuleInfoColumnDatasMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface ModuleInfoColumnDatasMapper extends CrudDao<ModuleInfoColumnDatas>{

 List<Long> getColumnDataIds(Long id);
 int relate(ModuleInfoColumnDatas moduleInfoColumnData);

 Long getId(@Param("moduleId") Long moduleId, @Param("columnDataId") Long columnDataId);

 Long[] getIdsByModuleId(@Param("moduleId") Long moduleId);

 Long[] getIdsByColumnDataId(@Param("columnDataId") Long columnDataId);

 Long[] getIds(@Param("list")List<Long> ids,@Param("moduleId") Long moduleId);

 void deleteByColumnDataId(Long ColumnDataId);

 void deleteByModuleId(Long moduleId);

}

