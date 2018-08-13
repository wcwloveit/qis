package com.xinri.service.production.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.production.ProductionLines;
import com.xinri.dao.production.ProductionLinesMapper;
import com.xinri.service.production.IProductionLinesService;
/**
 * <p></p>
 * 类名:ProductionLinesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("productionLinesService")
public class ProductionLinesServiceImpl extends CrudService<ProductionLinesMapper,ProductionLines>  implements IProductionLinesService{


}