package com.xinri.dao.baseData;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.baseData.BaseDatas;

import java.util.List;

/**
 * 类名:BaseDatasMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface BaseDatasMapper extends CrudDao<BaseDatas>{

 public List<BaseDatas> findList(BaseDatas baseDatas);
}

