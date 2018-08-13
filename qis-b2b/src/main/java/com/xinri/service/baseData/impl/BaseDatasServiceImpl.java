package com.xinri.service.baseData.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.dao.baseData.BaseDatasMapper;
import com.xinri.service.baseData.IBaseDatasService;
/**
 * <p></p>
 * 类名:BaseDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("baseDatasService")
public class BaseDatasServiceImpl extends CrudService<BaseDatasMapper,BaseDatas>  implements IBaseDatasService{


}