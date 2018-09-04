package com.xinri.dao.moduleInfo;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.role.RoleClasses;
import com.xinri.vo.columnData.ColumnDataVo;
import com.xinri.vo.role.RoleClassesVo;

import java.util.List;

/**
 * 类名:ColumnDatasMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface ColumnDatasMapper extends CrudDao<ColumnDatas>{

 public List<ColumnDataVo> findListByVo(ColumnDataVo columnDataVo);

}

