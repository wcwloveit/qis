package com.xinri.service.production;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.production.ProductionLines;
import com.xinri.vo.ProductionLines.ProductionLinesVo;
import com.xinri.vo.role.RoleClassesVo;

import java.util.Map;
/**
 * <p></p>
 * 类名:ProductionLinesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IProductionLinesService extends IBaseService<ProductionLines>{


    public DataTable<ProductionLinesVo> findListByVo(DataTable<ProductionLinesVo> dt, Map<String, Object> searchParams);
}

