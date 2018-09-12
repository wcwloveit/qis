package com.xinri.dao.production;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.production.ProductionLines;
import com.xinri.vo.ProductionLines.ProductionLinesVo;

import java.util.List;

/**
 * 类名:ProductionLinesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface ProductionLinesMapper extends CrudDao<ProductionLines>{

public List<ProductionLinesVo> findListByVo(ProductionLinesVo productionLinesVo);

}

