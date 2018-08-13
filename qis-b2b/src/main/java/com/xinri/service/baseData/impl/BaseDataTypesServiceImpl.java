package com.xinri.service.baseData.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.baseData.BaseDataTypes;
import com.xinri.dao.baseData.BaseDataTypesMapper;
import com.xinri.service.baseData.IBaseDataTypesService;
/**
 * <p></p>
 * 类名:BaseDataTypesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("baseDataTypesService")
public class BaseDataTypesServiceImpl extends CrudService<BaseDataTypesMapper,BaseDataTypes>  implements IBaseDataTypesService{


}